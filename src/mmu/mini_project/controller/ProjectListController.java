package mmu.mini_project.controller;

import java.util.List;

import mmu.mini_project.model.Project;
import mmu.mini_project.utils.Storage;
import mmu.mini_project.view.ProjectList;

/** 
 * @author WONG JIA XIAN, JORDAN
 */

public class ProjectListController {
	
	private List<Project> model = Storage.projects;
	private ProjectList view;
	
	public ProjectListController(ProjectList view) {
		this.view = view;
	}
	
	// Update project list into the view
	public void updateProject() {
		view.updateProjectList(model);
	}
	
	// Show the project details based on the project ID
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

	public ProjectList getView() {
		return view;
	}

	public void setView(ProjectList view) {
		this.view = view;
	}

}
