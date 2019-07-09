package de.tarent.commons.datahandling;
import de.tarent.commons.datahandling.entity.Entity;
import de.tarent.commons.datahandling.entity.WritableEntity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * This is an generic implementation for pojos using the {@link java.lang.reflect.Proxy}.
 * With this implementation it is possible to provide an implementation for a pojo-Object by runtime,
 * using the interface of the pojo.
 *
 * GenericPojos delegeate calls to the pojo to two different members: All property access is
 * controled by a GenericPojoStorage and all other calls are controled by a GenericPojoManager.
 * The last one is optional.
 *
 * TODO: Implement default values depending on the type of the propertie
 *
 * @author Sebastian Mancke, tarent GmbH
 */
public class GenericPojo implements InvocationHandler {
    GenericPojoManager pojoManager = null;
    GenericPojoStorage pojoStorage = null;

    /**
     * Contructe a new GenericPojo with the supplied storage and manager
     */
    public GenericPojo(GenericPojoStorage storage, GenericPojoManager manager) {
        pojoStorage = storage;
        pojoManager = manager;
    }

    /**
     * Contructe a new GenericPojo with the supplied storage and no manager
     */
    public GenericPojo(GenericPojoStorage storage) {
        pojoStorage = storage;
    }

    /**
     * Contructe a new GenericPojo with the default storage and no manager
     */
    public GenericPojo() {
        pojoStorage = new HashMapPojoStorage();
    }

    /**
     * Create a GenericPojo implementation for the supplied pojoInterface with the default storage and no pojoManager
     *
     * @param pojoInterface the interface for the pojo
     * @return the new Pojo object implementing the supplied interface
     */
    public static Object implementPojo(Class pojoInterface) {
        return Proxy.newProxyInstance(pojoInterface.getClassLoader(), new Class[] { pojoInterface }, new GenericPojo());
    }

    /**
     * Create a GenericPojo implementation for the supplied pojoInterface with the supplied storage and no pojoManager
     *
     * @param pojoInterface the interface for the pojo
     * @return the new Pojo object implementing the supplied interface
     */
    public static Object implementPojo(Class pojoInterface, GenericPojoStorage storage) {
        return Proxy.newProxyInstance(pojoInterface.getClassLoader(), new Class[] { pojoInterface }, new GenericPojo(storage));
    }

    /**
     * Create a GenericPojo implementation for the supplied pojoInterface with the supplied storage and pojoManager
     *
     * @param pojoInterface the interface for the pojo
     * @param storage       the storage for the properties
     * @param manager       the manager for handling of arbitary calls to the pojo
     * @return the new Pojo object implementing the supplied interface
     */
    public static Object implementPojo(Class pojoInterface, GenericPojoStorage storage, GenericPojoManager manager) {
        return Proxy.newProxyInstance(pojoInterface.getClassLoader(), new Class[] { pojoInterface },
          new GenericPojo(storage, manager));
    }

    /**
     * The invocation of the proxy. Here we decide,
     * what action we want to perform: get or set an attribute.
     *
     * @param proxy  The proxy instance
     * @param method The called Method
     * @param args   the call arguments
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();

        if (proxy instanceof Entity && name.equals("getAttribute") && args.length == 1) {
            return pojoStorage.get(args[0]);
        }

        if (proxy instanceof WritableEntity && name.equals("setAttribute") && args.length == 2) {
            return pojoStorage.put(args[0], args[1]);
        }

        // get
        if (name.length() > 3 && (args == null || args.length == 0) && name.startsWith("get")) {
            return pojoStorage.get(getKeyByMethodName(name, 3));
        }

        // set
        if (name.length() > 3 && args != null && args.length == 1 && name.startsWith("set")) {
            return pojoStorage.put(getKeyByMethodName(name, 3), args[0]);
        }

        // is
        if (name.length() > 2 && (args == null || args.length == 0) && name.startsWith("is")) {
            return pojoStorage.get(getKeyByMethodName(name, 2));
        }

        // if no property is accessed, we delegate the call to a PojoManager
        if (pojoManager != null) {
            return pojoManager.methodCalled(proxy, this, method, args);
        }

        if (name.equals("toString")) {
            return toString();
        }

        return null;
    }

    protected static String getKeyByMethodName(String methodName, int offset) {
        String name = methodName.substring(offset++, offset).toLowerCase();
        if (methodName.length() > offset) {
            name += methodName.substring(offset);
        }
        return name;
    }
}
