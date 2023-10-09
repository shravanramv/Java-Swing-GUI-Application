package mmu.mini_project.controller;

import mmu.mini_project.model.User;

/**
 * @author LOW YONG ZHI, LIM WEI PANG
 */

// A class that controls the login session such as keeping track of the user who logged in.
public class SessionController {
	
	private static User user;
	
	// Singleton design pattern
	private static SessionController session;
	
	public static SessionController getSession() {
		if(session == null) {
			session = new SessionController();
		}
		return session;
	}
	
	public User getLoggedIn() {
		return user;
	}
	
	public boolean hasLoggedIn() {
		// User has assigned to the variable means someone has logged in
		return user != null;
	}
	
	public void setLoggedIn(User user) {
		SessionController.user = user;
	}
	
	public void logout() {
		SessionController.user = null;
	}

}
