package mmu.mini_project.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mmu.mini_project.utils.Storage;

/**
 * @author WONG JIA XIAN, LIM WEI PANG, JORDAN, LOW YONG ZHI
 */

public class Project implements Model{ // interface
	
	private Student student;   
	private Lecturer lecturer;
	private int id, status;
	private String name, description, comment;
	private Specialization specialization;
	
	// overloaded constructor
	public Project(int id, int status, String name, String description, String comment,
			Student student, Lecturer lecturer, Specialization specialization) {
		this.id = id;
		this.status = status;
		this.name = name;
		this.description = description;
		this.comment = comment;
		this.student = student;
		this.lecturer = lecturer;
		this.setSpecialization(specialization);
	}
	
	// retrieve the next id of the new project
	public static int getIncrementId() { 
		// by finding the last project's id and increment by 1
		if(Storage.projects.size() == 0) return 1;
		
		int incremId = Storage.projects.get(Storage.projects.size()-1).getId() + 1;
		return incremId;
	}
	
	// Project is assigned to student
	public boolean isAssigned() {
		return student != null;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	// project has been assigned to student
	public boolean hasStudentAssigned() {
		return this.student != null;
	}
	
	public Lecturer getLecturer() {
		return lecturer;
	}
	
	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	
	public int getId() {
		return id;
	}
	
	// Status = 0 Inactive, Status = 1 Active
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}
	
	// upload the content of project
	public List<File> getUploadedFiles() {
		List<File> files = new ArrayList<File>();
		File dir = new File(Storage.getUploadFilePath(id));
		if(dir.exists()) {
			for(File file : dir.listFiles()) {
				files.add(file);
			}
		}	
		return files;
	}
	
	@Override
	public String toString() {
		int id = 0;
		if(student == null) {
			id = 0;
		}else {
			id = student.getId();
		}
		return String.join(",", String.valueOf(this.id), String.valueOf(status), name, description, comment, 
				String.valueOf(id), String.valueOf(lecturer.getId()), String.valueOf(specialization.getId()));
	}

	@Override
	public void addModel() {
		Storage.addProject(this);
	}

	@Override
	public void editModel() {
		Storage.editProject(this);
	}

	@Override
	public void deleteModel() {
		Storage.removeProject(this);
	}

	@Override
	public void updateModel() {
		Storage.saveProject();
		
	}

}
