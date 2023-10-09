package mmu.mini_project.controller;

import mmu.mini_project.model.Model;
import mmu.mini_project.model.Specialization;
import mmu.mini_project.utils.Storage;
import mmu.mini_project.view.SpecializationList;

/**
 * @author JORDAN
 */

public class SpecializationListController {
	
	private Model model;
	private SpecializationList view;
	
	public SpecializationListController(Model model, SpecializationList view) {
		this.model = model;
		this.view = view;
	}
	
	public void updateSpecializationList() {
		view.updateSpecializationList(Storage.specializations);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public SpecializationList getView() {
		return view;
	}

	public void setView(SpecializationList view) {
		this.view = view;
	}

	public void showSpecialization(int id) {
		for(Specialization spec : Storage.specializations) {
			if(spec.getId() == id) {
				view.showSpecializationView(spec);
				break;
			}
		}
	}
	
}
