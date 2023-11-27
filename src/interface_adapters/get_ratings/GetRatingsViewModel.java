package interface_adapters.get_ratings;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetRatingsViewModel extends ViewModel{

    private GetRatingsState state = new GetRatingsState();

    public GetRatingsViewModel() {
        super("ratings");
    }

    public GetRatingsState getState() {
        return state;
    }

    public void setState(GetRatingsState state) {
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
