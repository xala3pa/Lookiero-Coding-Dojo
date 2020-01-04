package lookiero.tech.tripservicekata.user;

import lookiero.tech.tripservicekata.trip.Trip;

import java.security.cert.CertPathBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private List<User> friends;
    private List<Trip> trips;

    public User() {
    }

    public User(UserBuilder userBuilder) {
        friends = Arrays.asList(userBuilder.friends);
        trips = Arrays.asList(userBuilder.trips);
    }

    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> trips() {
        return trips;
    }

    public boolean isFriendOf(User anotherUser) {
        return friends.contains(anotherUser);
    }

    public static class UserBuilder {
        private User[] friends = new User[]{};
        private Trip[] trips = new Trip[]{};

        public UserBuilder friendsWith(User... friends) {
            this.friends = friends;
            return this;
        }

        public UserBuilder withTrips(Trip... trips) {
            this.trips = trips;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
