package mmu.mini_project.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import mmu.mini_project.model.Project;
import mmu.mini_project.utils.Storage;
import mmu.mini_project.view.ReportList;

/**
 * @author JORDAN, WONG JIA XIAN
 */

public class ReportListController {
	
	private List<Project> model;
	private ReportList view;
	
	public ReportListController(ReportList view) {
		this.model = Storage.projects;
		this.view = view;
	}
	
	public void getAllProjects() {
		view.showProjects(model);
	}
	
	public void getProjectsBySpecialization(int mode) {
		// Sort by specialization
		List<Project> sortList = new ArrayList<Project>();
		for(Project proj : model) {
			sortList.add(proj);
		}
		
		// Sort the new project list by comparing the specialization name
		sortList.sort(new Comparator<Project>() {

			@Override
			public int compare(Project o1, Project o2) {
				// Sort by the order mode (Ascending or descending)
				int order = 1;
				if(mode == 0) {
					order = 1;
				}else if(mode == 1) {
					order = -1;
				}
				
				return order * o1.getSpecialization().getName().compareTo(o2.getSpecialization().getName());
			}
		});
		
		view.showProjects(sortList);
	}
	
	public void getProjectsByLecturerName(int mode) {
		// Sort by lecturer name
		List<Project> sortList = new ArrayList<Project>();
		for(Project proj : model) {
			sortList.add(proj);
		}
		
		// Sort the new project list by comparing the lecturer full name
		sortList.sort(new Comparator<Project>() {

			@Override
			public int compare(Project o1, Project o2) {
				// Sort by the order mode (Ascending or descending)
				int order = 1;
				if(mode == 0) {
					order = 1;
				}else if(mode == 1) {
					order = -1;
				}
				
				return order * o1.getLecturer().getFullName().compareTo(o2.getLecturer().getFullName());
			}
		});
		
		view.showProjects(sortList);
	}
	
	public void getActiveProjects() {
		List<Project> projects = new ArrayList<Project>();
		for(Project proj : model) {
			if(proj.getStatus() == 1) {
				projects.add(proj);
			}
		}
		
		view.showProjects(projects);
	}
	
	public void getInactiveProjects() {
		List<Project> projects = new ArrayList<Project>();
		for(Project proj : model) {
			if(proj.getStatus() == 0) {
				projects.add(proj);
			}
		}
		
		view.showProjects(projects);
	}
	
	public void getAssignedProjects() {
		List<Project> projects = new ArrayList<Project>();
		for(Project proj : model) {
			if(proj.isAssigned()) {
				projects.add(proj);
			}
		}
		
		view.showProjects(projects);
	}
	
	public void getUnassignedProjects() {
		List<Project> projects = new ArrayList<Project>();
		for(Project proj : model) {
			if(!proj.isAssigned()) {
				projects.add(proj);
			}
		}
		
		view.showProjects(projects);
	}
	
	public void getProjectsWithComment() {
		List<Project> projects = new ArrayList<Project>();
		for(Project proj : model) {
			if(proj.getComment() != null && proj.getComment().length() > 0) {
				projects.add(proj);
			}
		}
		
		view.showProjects(projects);
	}
	
	public void showProject(int id) {
		for(Project proj : model) {
			if(proj.getId() == id) {
				view.selectProjectFromList(proj);
			}
		}
	}

	public List<Project> getModel() {
		return model;
	}

	public void setModel(List<Project> model) {
		this.model = model;
	}

	public ReportList getView() {
		return view;
	}

	public void setView(ReportList view) {
		this.view = view;
	}

}
