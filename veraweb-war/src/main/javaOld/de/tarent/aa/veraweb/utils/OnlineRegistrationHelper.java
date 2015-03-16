package de.tarent.aa.veraweb.utils;

import java.io.IOException;
import java.util.Properties;

import de.tarent.aa.veraweb.beans.Person;
import de.tarent.dblayer.helper.ResultList;
import de.tarent.dblayer.sql.clause.Clause;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.dblayer.sql.clause.Limit;
import de.tarent.dblayer.sql.clause.Order;
import de.tarent.dblayer.sql.statement.Select;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.ExecutionContext;
import de.tarent.octopus.server.OctopusContext;
import org.apache.commons.lang.RandomStringUtils;
import org.osiam.client.OsiamConnector;
import org.osiam.client.oauth.AccessToken;
import org.osiam.resources.scim.User;


/**
 * Helper to play with the Online-Application Configuration
 *
 * @Return true = activated
 * @Return false = deactivated
 *
 * @author jnunez
 */
public class OnlineRegistrationHelper {
	
	private static final String VWOR_PARAM = "vwor.activated";
	private static final String VWOR_VALUE_TRUE = "true";
	private static final String PASSWORD_GENERATOR_AUSWAHLMOEGLICHKEITEN =
			"abzdefghijklmnopqrstuvwxyzABZDEFGHIJKLMNOPQRSTUVWXYZ1234567890!$-_#<>@&()+=}|";

	public static Boolean isOnlineregActive(OctopusContext cntx) {

		String active = cntx.moduleConfig().getParam(VWOR_PARAM);

		if (active != null && VWOR_VALUE_TRUE.equals(active)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Username generator
	 * 
	 * @return String username
	 * @throws BeanException 
	 * @throws IOException 
	 */
	public static String generateUsername(final String firstname, 
										  final String lastname,
										  final ExecutionContext context) throws BeanException, IOException {
		
		if (firstname != null && lastname != null) {
			
			StringBuilder sb = new StringBuilder();
			
			String convertedLastname = lastname;
			if (lastname.length() >= 5) {
				convertedLastname = lastname.substring(0, 5);
			}
			
			sb.append(firstname.substring(0, 1).toLowerCase()).append(convertedLastname.toLowerCase());
			
			String username = sb.toString();
	
			// check if a duplicate entry was found
			if (context != null) {
				Database database = context.getDatabase();
				
				Clause whereClause = Expr.like("username", username + "%");
				
				Select selectStatement = database.getSelect("Person").where(whereClause);
				selectStatement.orderBy(Order.desc("pk"));
				selectStatement.Limit(new Limit(new Integer(1), new Integer(0)));
				
				ResultList list = database.getList(selectStatement, context);
				
				if (!list.isEmpty()) {
					Person person = (Person) list.get(0);
					String auxUsername= person.username;
					
					String[] res = auxUsername.split(username);
					
					if (res.length > 1 && isNumeric(res[1])) {
						Integer usernameNumber = Integer.valueOf(res[1]);
						sb.append(usernameNumber++);
					}
				}
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * Generate random password for "Online-Anmeldung".
	 *
	 * @return The password
	 */
	public static String generatePassword() {

		String random = null;
		do {
			random = RandomStringUtils.random(8, PASSWORD_GENERATOR_AUSWAHLMOEGLICHKEITEN.toCharArray());
		} while (!random.matches((".*(?=.*\\d)(?=.*[A-Z])(?=.*[-_$!#<>@&()+=}]).*")));

		return random;
	}
	
	public static void createOsiamUser(AccessToken accessToken, String login,
			String password, OsiamConnector connector) {
		User delegationUser = new User.Builder(login).setActive(true)
				.setPassword(password).build();

		// create User in osiam
		connector.createUser(delegationUser, accessToken);
	}
	
	
	
	/**
	 * TODO move to another class
	 * Checking if an String is numeric
	 * 
	 * @param str String
	 * @return boolean
	 */
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
