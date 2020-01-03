package lookiero.tech.tripservicekata.trip;

import lookiero.tech.tripservicekata.exception.UserNotLoggedInException;
import lookiero.tech.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNKNOWN_USER = null;
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_TRESPADERNE = new Trip();
    private static  User loggedInUser;
    private TripService tripService;

    @BeforeEach
    void setUp() {
        tripService = new TestableTripService();;
    }

    @Test
    void
    should_throws_an_exception_when_user_is_not_logged_in() {
        Exception exception = assertThrows(UserNotLoggedInException.class, () -> {

            loggedInUser = GUEST;

            tripService.getTripsByUser(UNKNOWN_USER);
        });
    }

    @Test
    void should_not_return_any_trips_if_users_are_not_friends() {
        loggedInUser = REGISTERED_USER;

        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addTrip(TO_TRESPADERNE);

        List<Trip> friendsTrips = tripService.getTripsByUser(friend);

        assertThat(friendsTrips).isEmpty();
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedInUser() {
            return loggedInUser;
        }
    }

}