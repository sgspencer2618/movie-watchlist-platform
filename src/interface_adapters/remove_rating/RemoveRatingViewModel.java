package interface_adapters.remove_rating;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class RemoveRatingViewModel extends ViewModel {
    private RemoveRatingState state;

    public RemoveRatingViewModel() {
        super("Remove Rating");
    }

    public RemoveRatingState getState() {
        return state;
    }

    public void setState(RemoveRatingState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // alert MovieInfoView about new movie
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
