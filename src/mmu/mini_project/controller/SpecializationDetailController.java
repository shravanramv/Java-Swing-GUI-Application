package mmu.mini_project.controller;

import mmu.mini_project.model.Specialization;
import mmu.mini_project.utils.Storage;
import mmu.mini_project.view.SpecializationDetail;

/**
 * @author JORDAN
 */

public class SpecializationDetailController {
	
	private Specialization model;
	private SpecializationDetail view;
	
	public SpecializationDetailController(Specialization model, SpecializationDetail view) {
		this.model = model;
		this.view = view;
	}
	
	public void addSpecialization(String name) {
		// Check if there is name input
		if(name == null || name.length() == 0) {
			view.showNoNameErrorMessage();
			return;
		}
		
		// Check if there is duplicate name
		for(Specialization spec : Storage.specializations) {
			if(spec.getName().equals(name)) {
				view.showDuplicateNameErrorMessage();
				return;
			}
		}
		
		Specialization model = new Specialization(Specialization.getIncrementId(), name);
		this.model = model;
		this.model.addModel();
		this.model.updateModel();
		
		view.showAddSpecializationSuccessMessage();
	}
	
	public Specialization getModel() {
		return model;
	}

	public void setModel(Specialization model) {
		this.model = model;
	}

	public SpecializationDetail getView() {
		return view;
	}

	public void setView(SpecializationDetail view) {
		this.view = view;
	}

	public void updateSpecialization(String name) {
		this.model.setName(name);
		this.model.editModel();
		this.model.updateModel();
		
		view.showEditSpecializationSuccessMessage();
	}

	public void deleteSpecialization() {
		this.model.deleteModel();
		this.model.updateModel();
		
		view.showDeleteSpecializationSuccessMessage();
	}

}
