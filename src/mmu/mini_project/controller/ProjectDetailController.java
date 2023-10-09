package mmu.mini_project.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mmu.mini_project.model.Lecturer;
import mmu.mini_project.model.Project;
import mmu.mini_project.model.Specialization;
import mmu.mini_project.model.Student;
import mmu.mini_project.model.User;
import mmu.mini_project.utils.Storage;
import mmu.mini_project.view.ProjectDetail;

/** 
 * @author WONG JIA XIAN, JORDAN
 */

public class ProjectDetailController {
	
	private Project model;
	private ProjectDetail view;
	
	public ProjectDetailController(Project model, ProjectDetail view){
		this.model = model;
		this.view = view;
	}
	
	public String getUploadFilePath() {
		return Storage.getUploadFilePath(model.getId());
	}
	
	public void uploadFile(File[] files) {
		int id = 0;
		
		List<String> uploadedFiles = new ArrayList<String>();
		
		// If there is no model means this is during add project, therefore, it uses the future project Id.
		if(model == null) {
			id = Project.getIncrementId();
		}else {
			id = model.getId();
		}
		
		for(File file : files) {
			String copyTo = Storage.getUploadFilePath(id) + "/" + file.getName();
			
			File copyFile = new File(copyTo);
			if(copyFile.exists()) {
				view.showFileDuplicateErrorMessage();
				return;
			}
			
			Storage.copyFile(file, copyTo);
			uploadedFiles.add(copyTo);
		}
		
		// Update the file list in view
		view.addFileToList(uploadedFiles);
	}
	
	public boolean deleteFile(File file) {
		return file.delete();
	}
	
	public void updateSpecialization() {
		view.updateSpecializationList(Storage.specializations);
	}
	
	// Update the student list in the same specialization into the combo box
	public void updateStudentList(String specName) {
		if(specName == null) return;
		
		List<Student> studs = new ArrayList<Student>();
		for(User user : Storage.users) {
			if(user instanceof Student) {
				Student student = (Student) user;  // Downcasting
				if(student.getSpecialization().getName().equals(specName)) {
					studs.add(student);
				}
			}
		}
		
		view.updateStudents(studs);
	}
	
	// Update the lecturer list into the combo box
	public void updateLecturerList() {
		User loggedIn = SessionController.getSession().getLoggedIn();
		
		if(loggedIn instanceof Lecturer) {
			// Update lecturer mode of accessing project details
			view.updateLecturer((Lecturer) loggedIn);
		}else{
			List<Lecturer> lecturers = new ArrayList<Lecturer>();
			for(User user : Storage.users) {
				if(user instanceof Lecturer) {
					lecturers.add((Lecturer) user);
				}
			}
			view.updateLecturers(lecturers);
		}
		
		// Update student view project mode
		if(loggedIn instanceof Student) {
			view.updateStudentView();
		}
	}
	
	public Project getModel() {
		return model;
	}

	public void setModel(Project model) {
		this.model = model;
	}

	public ProjectDetail getView() {
		return view;
	}

	public void setView(ProjectDetail view) {
		this.view = view;
	}
	
	private Project getProject(int id, String title, int status, String specialization, String student, String lecturer,
			String desc, String comment) {
		Student stud = null;
		Lecturer lect = null;
		Specialization spec = null;
		
		if(title == null || title.length() == 0) {
			view.showNoTitleErrorMessage();
			return null;
		}
		
		// Find specialization from the name given by user input
		for(Specialization sp : Storage.specializations) {
			if(sp.getName().equals(specialization)) {
				spec = sp;
				break;
			}
		}
		
		if(spec == null) {
			view.showNoSpecializationErrorMessage();
			return null;
		}
		
		// Find student and lecturer
		for(User user : Storage.users) {
			// Break the loop if the students and lecturers are found
			if(stud != null && lect != null) break;
			if(user.getFullName().equals(student) && !student.equals("None")) {
				stud = (Student) user;
			}else if(user.getFullName().equals(lecturer)) {
				lect = (Lecturer) user;
			}
		}
		
		if(lect == null) {
			view.showNoLecturerErrorMessage();
			return null;
		}
		
		// MVC adding the project object into the project list. Then, write it into the file with updateModel
		Project proj = new Project(id, status, title, desc, comment, stud, lect, spec);
		return proj;
	}

	public void addProject(String title, int status, String specialization, String student, String lecturer,
			String desc, String comment) {
		this.model = getProject(Project.getIncrementId(), title, status, specialization, student, lecturer, desc, comment);
		this.model.addModel();
		this.model.updateModel();
		
		view.showAddProjectSuccessMessage();
	}

	public void editProject(String title, int status, String specialization, String student, String lecturer,
			String desc, String comment) {
		this.model = getProject(model.getId(), title, status, specialization, student, lecturer, desc, comment);
		this.model.editModel();
		this.model.updateModel();
		
		view.showEditProjectSuccessMessage();
	}
	
	public void deleteProject() {
		this.model.deleteModel();
		this.model.updateModel();
		view.showDeleteProjectSuccessMessage();
	}

}
