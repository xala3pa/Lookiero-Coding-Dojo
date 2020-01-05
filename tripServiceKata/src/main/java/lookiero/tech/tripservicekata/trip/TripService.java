package lookiero.tech.tripservicekata.trip;

import lookiero.tech.tripservicekata.exception.UserNotLoggedInException;
import lookiero.tech.tripservicekata.user.User;

import java.util.ArrayList;
import java.util.List;

public class TripService {
    private TripDAO tripDAO;

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }

        return user.isFriendOf(loggedInUser)
                ? tripDAO.findTripsBy(user)
                : noTrips();
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<>();
    }

}
