package org.craftedsw.tripservicekata.trip;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TripServiceTest {
	
	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTRED_USER = new User();
	private static final User A_USER = new User();
	private static final User SOMEONE_ELSE = new User();
	
	private static final Trip TO_BELO_HORIZONTE = new Trip();
	private static final Trip TO_FLORIPA = new Trip();
	
	public User loggedInUser;
	private TestableTripService tripService;
	
	@Before
	public void initialise() {
		tripService = new TestableTripService();
	}

	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_user_is_not_logged_in() throws Exception {
		loggedInUser = GUEST;
		tripService.getTripsByUser(UNUSED_USER);
	}
	
	@Test
	public void should_not_return_any_trips_when_users_are_not_friends() throws Exception {
		loggedInUser = REGISTRED_USER;
		final List<Trip> friendTrips = tripService.getTripsByUser(A_USER);
		assertThat(friendTrips.size(), is(0));
	}
	
	@Test
	public void should_return_friend_trips_when_users_are_friends() throws Exception {
		loggedInUser = REGISTRED_USER;
		
		User user = UserBuilder.aUser()
						.friendWith(loggedInUser, SOMEONE_ELSE)
						.withTrips(TO_BELO_HORIZONTE, TO_FLORIPA)
						.build();
		
		final List<Trip> friendTrips = tripService.getTripsByUser(user);
		assertThat(friendTrips.size(), is(2));
	}
	
	private class TestableTripService extends TripService {
		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}
		
		@Override
		protected List<Trip> tripsBy(User user) {
			return user.trips();
		}
	}
	
}
