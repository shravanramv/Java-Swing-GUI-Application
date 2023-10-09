package mmu.mini_project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mmu.mini_project.utils.Storage;

/**
 * @author LIM WEI PANG
 */

public class Student extends User{ // inherit user class
	
	// Aggregation
	private Specialization specialization;

	public Student(int id, String username, String password, String fullName, 
			String email, Date dob, Specialization specialization) {
		super(id, username, password, fullName, email, 3, dob);
		this.specialization = specialization;
	}
	
	@Override
	public String toString() {
		return super.toString() + "," + specialization.getId();
	}
	
	public Specialization getSpecialization() {
		return specialization;
	}
	
	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}
	
	public boolean hasAssignedProject() {
		List<Project> projects = getProjects();

		// If there is more than 0 assigned project then it returns true
		return projects.size() > 0;
	}

	// retrieve the project assigned to student
	@Override
	public List<Project> getProjects() {
		List<Project> projects = new ArrayList<Project>();
		for(Project project : Storage.projects) {
			if(!project.isAssigned()) continue; // skip the loop if the project is not unassigned
			
			if(project.getStatus() == 1 && project.getStudent().getId() == this.getId()) {
				projects.add(project);
			}
		}
		return projects;
	}
	
	// retrieve all the project of same specializaion
	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<Project>();
		for(Project project : Storage.projects) {
			if(project.getStatus() == 1 &&
					project.getSpecialization().getId() == this.getSpecialization().getId()) {
				projects.add(project);
			}
		}
		return projects;
	}
}
