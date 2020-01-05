package lookiero.tech.tripservicekata.trip;

import lookiero.tech.tripservicekata.exception.CollaboratorCallException;
import lookiero.tech.tripservicekata.user.User;

import java.util.List;

public class TripDAO {
    public static List<Trip> findTripsByUser(User user) {
        throw new CollaboratorCallException(
                "TripDAO should not be invoked on an unit test.");
    }

    public List<Trip> findTripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
