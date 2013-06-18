package org.craftedsw.tripservicekata.user;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.craftedsw.tripservicekata.trip.Trip;
import org.junit.Test;

public class UserBuilderTest {

	private static final User SOMEONE = new User();
	private static final User SOMEONE_ELSE = new User();
	
	private static final Trip TO_BRAZIL = new Trip();
	private static final Trip TO_GERMANY = new Trip();

	@Test
	public void should_create_a_user() throws Exception {
		User user = UserBuilder.aUser().build();
		assertThat(user, is(notNullValue()));
	}
	
	@Test
	public void should_create_a_user_with_friends() throws Exception {
		User user = UserBuilder.aUser()
				.friendWith(SOMEONE, SOMEONE_ELSE)
				.build();
		assertThat(user.getFriends().size(), is(2));
	}
	
	@Test
	public void should_create_a_user_with_trips() throws Exception {
		User user = UserBuilder.aUser()
				.withTrips(TO_BRAZIL, TO_GERMANY)
				.build();
		assertThat(user.trips().size(), is(2));
	}
}
