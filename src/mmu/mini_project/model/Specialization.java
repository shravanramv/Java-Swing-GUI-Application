package mmu.mini_project.model;

import mmu.mini_project.utils.Storage;

/**
 * @author JORDAN
 */

public class Specialization implements Model{ // interface
	
	private int id;
	private String name;
	
	// overloaded constructor
	public Specialization(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	// retrieve the next id of the new specialization
	public static int getIncrementId() { 
		// by finding the last specialization's id and increment by 1
		if(Storage.specializations.size() == 0) return 1;
		int incremId = Storage.specializations.get(Storage.specializations.size()-1).getId() + 1;
		return incremId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void addModel() {
		Storage.addSpecialization(this);
		
	}

	@Override
	public void editModel() {
		Storage.editSpecialization(this);
	}

	@Override
	public void deleteModel() {
		Storage.removeSpecialization(this);
	}

	@Override
	public void updateModel() {
		Storage.saveSpecialization();
	}
	
	@Override
	public String toString() {
		return this.id + "," + this.name;
	}
}
