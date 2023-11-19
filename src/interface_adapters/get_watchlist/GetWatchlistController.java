package interface_adapters.get_watchlist;

import entity.User;
import use_case.get_watchlist.GetWatchlistInputBoundary;
import use_case.get_watchlist.GetWatchlistInputData;

public class GetWatchlistController {

    final GetWatchlistInputBoundary getWatchlistUseCaseInteractor;

    public GetWatchlistController(GetWatchlistInputBoundary getWatchlistUseCaseInteractor) {
        this.getWatchlistUseCaseInteractor = getWatchlistUseCaseInteractor;
    }

    public void execute(String user){
        GetWatchlistInputData getWatchlistInputData = new GetWatchlistInputData(user);

        getWatchlistUseCaseInteractor.execute(getWatchlistInputData);
    }
}
