package lookiero.tech.tripservicekata.trip;

import lookiero.tech.tripservicekata.exception.UserNotLoggedInException;
import lookiero.tech.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripServiceTest {

    public static final User GUEST = null;
    public static final User UNKNOWN_USER = null;

    @Test
    void
    should_throws_an_exception_when_user_is_not_logged_in() {
        Exception exception = assertThrows(UserNotLoggedInException.class, () -> {
            TripService tripService = new TestableTripService();

            tripService.getTripsByUser(UNKNOWN_USER);
        });
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedInUser() {
            return GUEST;
        }
    }

}