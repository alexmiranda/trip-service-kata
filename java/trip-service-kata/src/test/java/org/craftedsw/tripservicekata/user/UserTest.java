package org.craftedsw.tripservicekata.user;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private static final User NON_FRIEND = new User();
	private static final User FRIEND = new User();
	
	private User user;
	
	@Before
	public void inititalise() {
		user = UserBuilder.aUser().build();
	}

	@Test
	public void should_inform_when_users_are_not_friends() throws Exception {;
		assertThat(user.isFriendWith(NON_FRIEND), is(false));
	}
	
	@Test
	public void should_inform_when_users_are_friends() throws Exception {
		user.addFriend(FRIEND);
		assertThat(user.isFriendWith(FRIEND), is(true));
	}
	
}
