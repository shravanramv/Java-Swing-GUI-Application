package mmu.mini_project.model;

import java.util.Date;
import java.util.List;

import mmu.mini_project.utils.Storage;

/**
 * @author JORDAN, LIM WEI PANG, LOW YONG ZHI
 */
public abstract class User implements Model{
	
	private String username, password, fullName, email;
	private int id;
	private Date dob;
	private int role;
	
	// overloaded constructor
	public User(int id, String username, String password, String fullName, String email, int role, Date dob) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.dob = dob;
		this.role = role;
	}
	
	// retrieve the next id of the new user
	public static int getIncrementId() { 
		// by finding the last user's id and increment by 1
		if(Storage.users.size() == 0) return 1;
		int incremId = Storage.users.get(Storage.users.size()-1).getId() + 1;
		return incremId;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public int getRole() {
		return role;
	}
	
	public void setRole(int role) {
		this.role = role;
	}
	
	// Abstract method which is mandatory to overriden by child classes
	public abstract List<Project> getProjects();
	
	@Override
	public void addModel() {
		Storage.addUser(this);
	}

	@Override
	public void editModel() {
		Storage.editUser(this);
	}

	@Override
	public void deleteModel() {
		Storage.removeUser(this);
	}

	@Override
	public void updateModel() {
		Storage.saveUser();
	}

	@Override
	public String toString() {
		return String.join(",", String.valueOf(id), username, password, 
				fullName, email, String.valueOf(role), String.valueOf(dob.getTime()));
	}

}
