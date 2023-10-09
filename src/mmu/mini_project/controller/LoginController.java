package mmu.mini_project.controller;

import java.util.List;

import mmu.mini_project.model.User;
import mmu.mini_project.utils.Storage;
import mmu.mini_project.view.Login;

/**
 * @author LOW YONG ZHI, LIM WEI PANG
 */

public class LoginController {
	
	private Login view;
	private List<User> model;
	
	public LoginController(Login view, List<User> model) {
		this.view = view;
		this.model = model;
	}
	
	// Load data from text file
	public void loadFiles() {
		Storage.loadSpecialization();
		Storage.loadUser();
		Storage.loadProject();
	}
	
	public void login(String username, String password) {
		for(User user : model) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				SessionController session = SessionController.getSession();
				session.setLoggedIn(user); // Store the user into a session
				view.loginSuccess();
				return;
			}
		}
		view.loginFailed();
	}

}
