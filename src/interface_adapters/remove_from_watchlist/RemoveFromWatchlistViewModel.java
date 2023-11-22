package interface_adapters.remove_from_watchlist;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RemoveFromWatchlistViewModel extends ViewModel{

    private RemoveFromWatchlistState state;
    private PropertyChangeSupport support;

    public RemoveFromWatchlistViewModel() {
        super("Remove from Watchlist");
    }

    public RemoveFromWatchlistState getState() {
        return state;
    }

    public void setState(RemoveFromWatchlistState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
