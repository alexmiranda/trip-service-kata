package org.craftedsw.tripservicekata.trip;

import static org.junit.Assert.*;

import org.craftedsw.tripservicekata.exception.DependendClassCallDuringUnitTestException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripDAOTest {

	@Test(expected = DependendClassCallDuringUnitTestException.class)
	public void should_throw_an_exception() throws Exception {
		final TripDAO tripDAO = new TripDAO();
		final User user = new User();
		tripDAO.tripsByUser(user);
	}
	
}
