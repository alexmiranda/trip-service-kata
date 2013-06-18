package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	private static final ArrayList<Trip> NO_TRIPS = new ArrayList<Trip>();

	public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
		validate(loggedInUser);
		return user.isFriendWith(loggedInUser) ? 
				tripsBy(user)
				: NO_TRIPS;
	}

	private void validate(User loggedUser) {
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
	}

	protected List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}	
}
