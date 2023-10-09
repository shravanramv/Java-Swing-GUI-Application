package mmu.mini_project.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import mmu.mini_project.model.Admin;
import mmu.mini_project.model.Lecturer;
import mmu.mini_project.model.Project;
import mmu.mini_project.model.Specialization;
import mmu.mini_project.model.Student;
import mmu.mini_project.model.User;

/**
 * @author LIM WEI PANG, JORDAN, SHRAVAN, LOW YONG ZHI, WONG JIA XIAN
 */

public class Storage {
	
	// Data file paths
	private static File userFile = new File("./data/users.txt");
	private static File projectFile = new File("./data/projects.txt");
	private static File specializationFile = new File("./data/specializations.txt");
	
	// Global data storage
	public static List<User> users = new ArrayList<User>();
	public static List<Project> projects = new ArrayList<Project>();
	public static List<Specialization> specializations = new ArrayList<Specialization>();
	
	// Loading user data from text file and add into ArrayList (memory)
	public static void loadUser() {
		if(users.size() > 0) return;
		
		if(checkUserFile()) {
			try {
				Scanner sc = new Scanner(userFile);
				while(sc.hasNextLine()) {
					String[] data = sc.nextLine().split(",");
					User user;
					int role = Integer.parseInt(data[5]);
					
					switch(role) {
					case 1:
						// Polymorphism is shown as User can take many forms (eg: Student, Lecturer, Admin)
						// User is declared initially and it is later assigned to Student / Lecturer / Admin which are the child classes of User
						user = new Admin(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], new Date(Long.parseLong(data[6])));
						users.add(user);
						break;
					case 2:
						user = new Lecturer(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], new Date(Long.parseLong(data[6])));
						users.add(user);
						break;
					case 3:
						// All students that doesn't have specialization is not loaded into the list as there are invalid student data
						for(Specialization spec : specializations) {
							if(Integer.parseInt(data[7]) == spec.getId()) {
								user = new Student(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], new Date(Long.parseLong(data[6])), spec);
								users.add(user);
								break;
							}
						}
						break;
					}
				}
				
				sc.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveUser() {
		checkUserFile();
		
		try (
				FileWriter fw = new FileWriter(userFile);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw);
				){
			for(User user : users){
				pw.println(user.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addUser(User user) {
		users.add(user);
	}
	
	public static void editUser(User user) {
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getId() == user.getId()) {
				users.set(i, user);
				return;
			}
		}
	}
	
	public static void removeUser(User user) {
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getId() == user.getId()) {
				users.remove(i);
				return;
			}
		}
	}
	
	public static void loadProject() {
		if(projects.size() > 0) return;
		
		if(checkProjectFile()) {
			try {
				Scanner sc = new Scanner(projectFile);
				while(sc.hasNextLine()) {
					String[] data = sc.nextLine().split(",");
					Student assignedStud = null;
					Lecturer assignedLect = null;
					
					// find Assigned Student
					for(User user : users) {
						if(user instanceof Student) {
							if(user.getId() == Integer.valueOf(data[5])) {
								assignedStud = (Student) user;
								break;
							}
						}
					}
					
					// find Assigned Lecturer
					for(User user : users) {
						if(user instanceof Lecturer) {
							if(user.getId() == Integer.valueOf(data[6])) {
								assignedLect = (Lecturer) user;
								break;
							}
						}
					}
					
					// All projects that doesn't have specialization is not loaded into the list as there are invalid project data
					for(Specialization spec : specializations) {
						if(Integer.parseInt(data[7]) == spec.getId()) {
							Project project = new Project(Integer.parseInt(data[0]), Integer.parseInt(data[1]), 
									data[2], data[3], data[4], assignedStud, assignedLect, spec);
							projects.add(project);
							break;
						}
					}
				}
				
				sc.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveProject() {
		checkProjectFile();
		
		try (
				FileWriter fw = new FileWriter(projectFile);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw);
				){
			for(Project proj : projects){
				pw.println(proj.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addProject(Project project) {
		projects.add(project);
	}
	
	public static void editProject(Project project) {
		for(int i = 0; i < projects.size(); i++){
			if(projects.get(i).getId() == project.getId()) {
				projects.set(i, project);
				return;
			}
		}
	}
	
	public static void removeProject(Project project) {
		for(int i = 0; i < projects.size(); i++){
			if(projects.get(i).getId() == project.getId()) {
				
				// Delete all uploaded files for the project
				for(File file : projects.get(i).getUploadedFiles()) {
					file.delete();
				}
				
				// Delete parent directory for this project
				File dir = new File("./uploads/" + projects.get(i).getId() + "/");
				dir.delete();
				
				projects.remove(i);
				return;
			}
		}
	}
	
	public static void loadSpecialization() {
		if(specializations.size() > 0) return;
		
		if(checkSpecializationFile()) {
			try {
				Scanner sc = new Scanner(specializationFile);
				while(sc.hasNextLine()) {
					String[] data = sc.nextLine().split(",");
					Specialization spec = new Specialization(Integer.parseInt(data[0]), data[1]);
					specializations.add(spec);
				}
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveSpecialization() {
		checkSpecializationFile();
		
		try (
				FileWriter fw = new FileWriter(specializationFile);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(fw);
				){
			for(Specialization spec : specializations){
				pw.println(spec.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addSpecialization(Specialization spec) {
		specializations.add(spec);
	}
	
	public static void editSpecialization(Specialization spec) {
		for(int i = 0; i < specializations.size(); i++){
			if(specializations.get(i).getId() == spec.getId()) {
				specializations.set(i, spec);
				return;
			}
		}
	}
	
	public static void removeSpecialization(Specialization spec) {
		for(int i = 0; i < specializations.size(); i++){
			if(specializations.get(i).getId() == spec.getId()) {
				specializations.remove(i);
				return;
			}
		}
	}
	
	// check whether user text file exists
	public static boolean checkUserFile() {
		if(!userFile.exists()) {
			try {
				if(!userFile.getParentFile().exists()) {
					userFile.getParentFile().mkdir();
				}
				userFile.createNewFile();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	// check whether project text file exists
	public static boolean checkProjectFile() {
		if(!projectFile.exists()) {
			try {
				if(!projectFile.getParentFile().exists()) {
					projectFile.getParentFile().mkdir();
				}
				projectFile.createNewFile();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	// check whether specialization text file exists
	public static boolean checkSpecializationFile() {
		if(!specializationFile.exists()) {
			try {
				if(!specializationFile.getParentFile().exists()) {
					specializationFile.getParentFile().mkdir();
				}
				specializationFile.createNewFile();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static String getUploadFilePath(int id) {
		File parent = new File("./uploads/" + id + "/");
		
		if(!parent.getParentFile().exists()) {
			parent.getParentFile().mkdir();
		}
		
		if(!parent.exists()) {
			parent.mkdir();
		}
		
		return parent.getPath();
	}
	
	public static void copyFile(File file, String copyTo) {
		Path copied = Paths.get(copyTo);
	    Path originalPath = file.toPath();
	    try {
			Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
