package lookiero.tech.tripservicekata.trip;

import lookiero.tech.tripservicekata.exception.UserNotLoggedInException;
import lookiero.tech.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNKNOWN_USER = null;
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_TRESPADERNE = new Trip();
    private static final Trip TO_BILBAO = new Trip();
    private TripService tripService;

    TripDAO tripDAOMock = mock(TripDAO.class);

    @BeforeEach
    void setUp() {
        tripService = new TripService(tripDAOMock);
    }

    @Test
    void
    should_throws_an_exception_when_user_is_not_logged_in() {
        Exception exception = assertThrows(UserNotLoggedInException.class,
                () -> tripService.getTripsByUser(UNKNOWN_USER, GUEST));
    }

    @Test
    void should_not_return_any_trips_if_users_are_not_friends() {

        User friend = new User.UserBuilder()
                .friendsWith(ANOTHER_USER)
                .withTrips(TO_TRESPADERNE)
                .build();

        List<Trip> friendsTrips = tripService.getTripsByUser(friend, REGISTERED_USER);

        assertThat(friendsTrips).isEmpty();
    }

    @Test
    void should_return_friend_trips_if_users_are_friends() {

        User friend = new User.UserBuilder()
                .friendsWith(REGISTERED_USER)
                .withTrips(TO_TRESPADERNE, TO_BILBAO)
                .build();

        when(tripDAOMock.findTripsBy(friend)).thenReturn(Arrays.asList(TO_TRESPADERNE, TO_BILBAO));

        List<Trip> friendsTrips = tripService.getTripsByUser(friend, REGISTERED_USER);

        assertThat(friendsTrips).hasSize(2);
    }
}