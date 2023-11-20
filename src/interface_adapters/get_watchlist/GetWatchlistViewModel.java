package interface_adapters.get_watchlist;

import interface_adapters.ViewModel;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetWatchlistViewModel extends ViewModel {

    private GetWatchlistState state;

    public GetWatchlistViewModel(String viewName) {
        super(viewName);
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
