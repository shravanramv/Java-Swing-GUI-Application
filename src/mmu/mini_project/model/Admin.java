package mmu.mini_project.model;

import java.util.Date;
import java.util.List;

import mmu.mini_project.utils.Storage;

/**
 * WONG JIA XIAN
 */
public class Admin extends User{  // inherit user class
	
	// overloaded constructor
	public Admin(int id, String username, String password, String fullName, String email, Date dob) {
		super(id, username, password, fullName, email, 1, dob);
	}

	// read the project from database
	@Override
	public List<Project> getProjects() {
		return Storage.projects;
	}

}
