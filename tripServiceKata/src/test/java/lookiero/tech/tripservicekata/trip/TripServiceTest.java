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
    private static final Trip TO_BILBAO = new Trip();
    private User loggedInUser;
    private TripService tripService;

    @BeforeEach
    void setUp() {
        tripService = new TestableTripService();
        loggedInUser = REGISTERED_USER;
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

        User friend = new User.UserBuilder()
                .friendsWith(ANOTHER_USER)
                .withTrips(TO_TRESPADERNE)
                .build();

        List<Trip> friendsTrips = tripService.getTripsByUser(friend);

        assertThat(friendsTrips).isEmpty();
    }

    @Test
    void should_return_friend_trips_if_users_are_friends() {

        User friend = new User.UserBuilder()
                .friendsWith(loggedInUser)
                .withTrips(TO_TRESPADERNE, TO_BILBAO)
                .build();

        List<Trip> friendsTrips = tripService.getTripsByUser(friend);

        assertThat(friendsTrips).hasSize(2);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedInUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> getTripsBy(User user) {
            return user.trips();
        }
    }
}