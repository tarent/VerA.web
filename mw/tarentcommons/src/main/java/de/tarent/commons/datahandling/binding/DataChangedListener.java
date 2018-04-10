package de.tarent.commons.datahandling.binding;

/**
 * Listener for data events.
 *
 * <p>For GUI applications: There is no policy, from which thread the event dispatching over this interface should be done.
 * So this event may be generated by an background data loading thread. I this case, swing must not be performed in the listener method directly.
 * You should use {@see java.awt.EventQueue.invokeLater()} for doing this. Or use the BindingManager, who cares about that.</p>
 *
 */
public interface DataChangedListener {

    /**
     * Listener method which is called on DataChang Events.
     */
    public void dataChanged(DataChangedEvent e);

}
