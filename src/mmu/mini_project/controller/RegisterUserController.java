package mmu.mini_project.controller;

import java.util.Date;

import mmu.mini_project.model.Admin;
import mmu.mini_project.model.Lecturer;
import mmu.mini_project.model.Specialization;
import mmu.mini_project.model.Student;
import mmu.mini_project.model.User;
import mmu.mini_project.utils.Storage;
import mmu.mini_project.view.RegisterUser;

/**
 * @author LIM WEI PANG, LOW YONG ZHI
 */

public class RegisterUserController {
	
	private User model;
	private RegisterUser view;
	
	public RegisterUserController(User model, RegisterUser view) {
		this.model = model;
		this.view = view;
	}
	
	public void updateSpecialization() {
		view.updateSpecializationList(Storage.specializations);
	}

	public User getModel() {
		return model;
	}

	public void setModel(User model) {
		this.model = model;
	}

	public RegisterUser getView() {
		return view;
	}

	public void setView(RegisterUser view) {
		this.view = view;
	}
	
	public void createAccount(String username, String password, String confirmPass, String fullName, String email, int role,
			Date dob, String specialization) {
		if(!password.equals(confirmPass)) {
			view.showRegistrationPasswordErrorMessage();
			return;
		}
		
		// Check if there is duplicate username or email or full name
		for(User user : Storage.users) {
			if(user.getUsername().equals(username)) {
				view.showDuplicateUsernameErrorMessage();
				return;
			}else if(user.getEmail().equals(email)) {
				view.showDuplicateEmailErrorMessage();
				return;
			}else if(user.getFullName().equals(fullName)) {
				view.showDuplicateFullNameErrorMessage();
				return;
			}
		}
		
		User model = null;
		
		switch(role) {
		case 1:
			model = new Admin(User.getIncrementId(), username, password, fullName, email, dob); 
			break;
		case 2:
			model = new Lecturer(User.getIncrementId(), username, password, fullName, email, dob);
			break;
		case 3:
			for(Specialization spec : Storage.specializations) {
				if(spec.getName().equals(specialization)) {
					model = new Student(User.getIncrementId(), username, password, fullName, email, dob, spec);
					break;
				}
			}
			break;
		}
		
		if(model != null) {
			this.model = model;
			
			this.model.addModel();
			this.model.updateModel();
			view.showRegistrationSuccessMessage();
			return;
		}
		view.showRegistrationErrorMessage();
	}

}
