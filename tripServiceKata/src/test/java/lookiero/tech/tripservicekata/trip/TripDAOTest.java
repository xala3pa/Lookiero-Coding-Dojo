package lookiero.tech.tripservicekata.trip;

import lookiero.tech.tripservicekata.exception.CollaboratorCallException;
import lookiero.tech.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TripDAOTest {
    @Test
    void should_throws_an_exception_when_finding_trips_by_user() {
        Exception exception = assertThrows(CollaboratorCallException.class,
                () -> new TripDAO().findTripsBy(new User()));
    }
}