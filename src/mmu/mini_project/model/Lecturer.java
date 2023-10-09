package mmu.mini_project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mmu.mini_project.utils.Storage;

/**
 * @author LOW YONG ZHI
 */
public class Lecturer extends User{  // inherit user class

	public Lecturer(int id, String username, String password, String fullName, String email, Date dob) {
		super(id, username, password, fullName, email, 2, dob);
	}

	// read project from project database
	@Override
	public List<Project> getProjects() {
		// create new project (composition)
		List<Project> projects = new ArrayList<Project>();
		for(Project project : Storage.projects) {
			if(project.getLecturer().getId() == this.getId()) {
				projects.add(project);
			}
		}
		return projects;
	}

}
