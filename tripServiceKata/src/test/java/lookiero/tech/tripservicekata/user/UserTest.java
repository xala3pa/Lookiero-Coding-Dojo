package lookiero.tech.tripservicekata.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    private static final User MARTIN = new User();
    private static final User BOB = new User();

    @Test
    void should_inform_when_users_are_not_friends() {
        User user = new User.UserBuilder()
                .friendsWith(MARTIN)
                .build();

        assertThat(user.isFriendOf(BOB)).isFalse();
    }

    @Test
    void should_inform_when_users_are_friends() {
        User user = new User.UserBuilder()
                .friendsWith(MARTIN, BOB)
                .build();

        assertThat(user.isFriendOf(BOB)).isTrue();
    }
}