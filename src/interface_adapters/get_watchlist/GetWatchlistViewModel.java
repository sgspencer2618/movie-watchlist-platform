package interface_adapters.get_watchlist;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetWatchlistViewModel extends ViewModel {

    private GetWatchlistState state = new GetWatchlistState();

    public GetWatchlistViewModel() {
        super("watchlist");
    }


    public GetWatchlistState getState() {
        return state;
    }

    public void setState(GetWatchlistState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
