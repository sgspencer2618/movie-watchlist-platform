package interface_adapters.movie_info;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MovieInfoViewModel {

    private MovieInfoState state;

    public MovieInfoViewModel() { }

    public MovieInfoState getState() {
        return state;
    }
    public void setState(MovieInfoState state) {
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
