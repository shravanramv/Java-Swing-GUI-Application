package mmu.mini_project.controller;

import mmu.mini_project.model.Lecturer;
import mmu.mini_project.model.Project;
import mmu.mini_project.model.Student;
import mmu.mini_project.model.User;
import mmu.mini_project.utils.Storage;
import mmu.mini_project.view.UserDashboard;

/**
 * @author SHRAVAN, LIM WEI PANG, LOW YONG ZHI
 */

public class UserDashboardController {
	
	private UserDashboard view;
	private User model;
	
	public UserDashboardController(UserDashboard view, User model) {
		this.view = view;
		this.model = model;
	}
	
	// This method is used to update the name in dashboard below Welcome back text
	public void updateName() {
		if(model == null) return; // If there is no model linked into the controller means no one logged in, then skip this method
		
		view.updateLoggedInName(model.getFullName());
	}
	
	public void updateProject() {
		if(model == null) return;
		
		// Update view with their own projects
		view.updateProjectList(model.getProjects());
		
		if(model instanceof Student) {
			// Update view with projects that are within the same specialization
			view.updateOthersProjectList(((Student) model).getAllProjects());
		}
	}
	
	// Update the dashboard view depending on user role
	public void updateView() {
		if(model == null) return;
		
		if(model instanceof Student) {
			view.updateUserView(1);
		}else if(model instanceof Lecturer) {
			view.updateUserView(2);
		}
	}
	
	public void logout() {
		SessionController.getSession().logout();
		view.logout();
	}

	public void showProject(int id) {
		for(Project proj : Storage.projects) {
			if(proj.getId() == id) {
				view.selectProjectFromList(proj);
			}
		}
	}

}
