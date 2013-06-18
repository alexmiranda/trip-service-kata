package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	/**
	 * Get trips by user
	 * @param user 
	 * @return trips by user
	 * @throws UserNotLoggedInException when no user is logged in
	 */
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		// trip list
		List<Trip> tripList = new ArrayList<Trip>();
		
		// gets logged in user
		User loggedUser = UserSession.getInstance().getLoggedUser();
		
		// is friend
		boolean isFriend = false;
		
		// check whether user is logged in
		if (loggedUser != null) {
			// if so, checks whether users are friends
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			
			// if they are, return user trips
			if (isFriend) {
				// get trips from TripDAO
				tripList = TripDAO.findTripsByUser(user);
			}
			
			// return trips
			return tripList;
		} else {
			// throw exception if user is not logged in
			throw new UserNotLoggedInException();
		}
	}	
}
