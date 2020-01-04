package lookiero.tech.tripservicekata.trip;

import lookiero.tech.tripservicekata.exception.UserNotLoggedInException;
import lookiero.tech.tripservicekata.user.User;
import lookiero.tech.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {
    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if (getLoggedInUser() == null) {
            throw new UserNotLoggedInException();
        }

        return user.isFriendOf(getLoggedInUser())
                ? getTripsBy(user)
                : noTrips();
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<>();
    }

    protected List<Trip> getTripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedInUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
