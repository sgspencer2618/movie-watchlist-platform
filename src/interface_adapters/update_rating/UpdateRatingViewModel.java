package interface_adapters.update_rating;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UpdateRatingViewModel extends ViewModel {
    private UpdateRatingState state;

    public UpdateRatingViewModel() {
        super("Update Rating");
    }

    public UpdateRatingState getState() {
        return state;
    }

    public void setState(UpdateRatingState state) {
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
