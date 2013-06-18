package org.craftedsw.tripservicekata.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.trip.Trip;

public class UserBuilder {
	
	private List<User> friends = new ArrayList<User>();
	private List<Trip> trips = new ArrayList<Trip>();

	public static UserBuilder aUser() {
		return new UserBuilder();
	}

	public UserBuilder friendWith(User... friends) {
		Collections.addAll(this.friends, friends);
		return this;
	}

	public UserBuilder withTrips(Trip... trips) {
		Collections.addAll(this.trips, trips);
		return this;
	}

	public User build() {
		User user = new User();
		for (User friend : friends)
			user.addFriend(friend);
		for (Trip trip : trips)
			user.addTrip(trip);
		return user;
	}

}
