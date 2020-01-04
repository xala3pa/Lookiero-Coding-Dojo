package lookiero.tech.tripservicekata.trip;

import lookiero.tech.tripservicekata.exception.UserNotLoggedInException;
import lookiero.tech.tripservicekata.user.User;
import lookiero.tech.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {
    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedInUser = getLoggedInUser();
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }

        List<Trip> tripList = new ArrayList<>();
        if (user.isFriendOf(loggedInUser)) {
            return getTripsBy(user);
        }
        return tripList;
    }

    protected List<Trip> getTripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedInUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
