package mmu.mini_project.controller;

import mmu.mini_project.model.Admin;
import mmu.mini_project.view.AdminDashboard;

/**
 * @author WONG JIA XIAN, JORDAN
 */

public class AdminDashboardController {
	
	private Admin model;
	private AdminDashboard view;
	
	public AdminDashboardController(Admin model, AdminDashboard view) {
		this.model = model;
		this.view = view;
	}
	
	// This method is used to update the name in dashboard below Welcome back text
	public void updateName() {
		if(model == null) return; // If there is no model linked into the controller means no one logged in, then skip this method
		
		view.updateLoggedInName(model.getFullName());
	}
	
	public void logout() {
		SessionController.getSession().logout();
		view.logout();
	}

}
