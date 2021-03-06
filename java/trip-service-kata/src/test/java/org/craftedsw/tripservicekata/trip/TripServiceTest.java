package org.craftedsw.tripservicekata.trip;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnit44Runner.class)
public class TripServiceTest {
	
	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTRED_USER = new User();
	private static final User A_USER = new User();
	private static final User SOMEONE_ELSE = new User();
	
	private static final Trip TO_BELO_HORIZONTE = new Trip();
	private static final Trip TO_FLORIPA = new Trip();
	
	public User loggedInUser;
	
	@Mock private TripDAO tripDAO;
	@InjectMocks @Spy private TripService tripService = new TripService();

	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_user_is_not_logged_in() throws Exception {
		loggedInUser = GUEST;
		tripService.getTripsByUser(UNUSED_USER, GUEST);
	}
	
	@Test
	public void should_not_return_any_trips_when_users_are_not_friends() throws Exception {
		loggedInUser = REGISTRED_USER;
		final List<Trip> friendTrips = tripService.getTripsByUser(A_USER, REGISTRED_USER);
		assertThat(friendTrips.size(), is(0));
	}
	
	@Test
	public void should_return_friend_trips_when_users_are_friends() throws Exception {
		loggedInUser = REGISTRED_USER;
		
		User user = UserBuilder.aUser()
						.friendWith(loggedInUser, SOMEONE_ELSE)
						.withTrips(TO_BELO_HORIZONTE, TO_FLORIPA)
						.build();
		
		when(tripDAO.tripsByUser(user)).thenReturn(user.trips());
		
		final List<Trip> friendTrips = tripService.getTripsByUser(user, REGISTRED_USER);
		assertThat(friendTrips.size(), is(2));
	}
}
