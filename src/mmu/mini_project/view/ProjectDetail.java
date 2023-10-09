package mmu.mini_project.view;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import mmu.mini_project.controller.ProjectDetailController;
import mmu.mini_project.model.Lecturer;
import mmu.mini_project.model.Project;
import mmu.mini_project.model.Specialization;
import mmu.mini_project.model.Student;
import mmu.mini_project.utils.Storage;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** 
 * @author WONG JIA XIAN, JORDAN
 * @author SHRAVAN, JORDAN (UI)
 */


public class ProjectDetail extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextArea txtDesc;
	private JTextArea txtComment;
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private JButton btnBack_1;
	private JButton btnBack;
	private JButton btnAdd;
	private JButton btnUpload;
	private JButton btnRemove;
	private JPanel lectPanel;
	private DefaultComboBoxModel<String> cbxSpecModel = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<String> cbxStudentModel = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<String> cbxLecturerModel = new DefaultComboBoxModel<>();
	private JComboBox<String> cbxSpecialization;
	private JComboBox<String> cbxStudent;
	private JComboBox<String> cbxLecturer;
	private JComboBox<String> cbxStatus;
	
	private String prev;
	private ProjectDetailController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectDetail frame = new ProjectDetail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @author SHRAVAN, JORDAN
	 * Create the frame.
	 */
	// this constructor is put as private to hide the constructor from public.
	// So, only the other new constructor can instantiate this new form.
	private ProjectDetail() {
		JFrame frame = this;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				removeUnnecessaryUploadedFile();
			}
		});
		setTitle("MMU Mini Project Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 782);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProjectDetail = new JLabel("Project Detail");
		lblProjectDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectDetail.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblProjectDetail.setBounds(10, 10, 584, 27);
		contentPane.add(lblProjectDetail);
		
		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 68, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescription.setBounds(20, 293, 89, 13);
		contentPane.add(lblDescription);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTitle.setColumns(10);
		txtTitle.setBounds(144, 61, 440, 26);
		contentPane.add(txtTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(144, 288, 440, 91);
		contentPane.add(scrollPane);
		
		txtDesc = new JTextArea();
		scrollPane.setViewportView(txtDesc);
		txtDesc.setFont(new Font("Monospaced", Font.PLAIN, 16));
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStatus.setBounds(20, 113, 45, 13);
		contentPane.add(lblStatus);
		
		cbxStatus = new JComboBox<>();
		cbxStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxStatus.setBounds(144, 103, 184, 27);
		DefaultComboBoxModel<String> cbxModel = new DefaultComboBoxModel<>();
		cbxModel.addElement("Active");
		cbxModel.addElement("Inactive");
		cbxStatus.setModel(cbxModel);
		contentPane.add(cbxStatus);
		
		JLabel lblComment = new JLabel("Comment");
		lblComment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblComment.setBounds(20, 407, 89, 13);
		contentPane.add(lblComment);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(144, 402, 440, 91);
		contentPane.add(scrollPane_1);
		
		txtComment = new JTextArea();
		scrollPane_1.setViewportView(txtComment);
		txtComment.setFont(new Font("Monospaced", Font.PLAIN, 16));
		
		lectPanel = new JPanel();
		lectPanel.setVisible(false);
		lectPanel.setBounds(0, 688, 606, 57);
		contentPane.add(lectPanel);
		
		btnBack_1 = new JButton("Back");
		btnBack_1.setBounds(386, 10, 112, 37);
		lectPanel.setLayout(null);
		btnBack_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lectPanel.add(btnBack_1);
		
		JButton btnEdit = new JButton("Save");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = txtTitle.getText();
				int status = 0;
				if(cbxStatus.getSelectedIndex() == 0) {
					status = 1;
				}else if(cbxStatus.getSelectedIndex() == 1) {
					status = 0;
				}
				String specialization = (String) cbxSpecialization.getSelectedItem();
				String student = (String) cbxStudent.getSelectedItem();
				String lecturer = (String) cbxLecturer.getSelectedItem();
				String desc = txtDesc.getText();
				String comment = txtComment.getText();
				
				controller.editProject(title, status, specialization, student, lecturer, desc, comment);
			}
		});
		btnEdit.setBounds(142, 10, 112, 37);
		lectPanel.setLayout(null);
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lectPanel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this project?");
				
				if(status == JOptionPane.OK_OPTION) {
					controller.deleteProject();
					
					JFrame view = null;
					
					if(prev.equals("Admin")) {
						view = new ProjectList();
					}else if(prev.equals("Report")) {
						view = new ReportList();
					}else if(prev.equals("User")) {
						view = new UserDashboard();
					}else {
						// If there is no previous window then dont close the window
						return;
					}
					
					view.setVisible(true);
					frame.dispose();
				}
			}
		});
		btnDelete.setBounds(264, 10, 112, 37);
		lectPanel.setLayout(null);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lectPanel.add(btnDelete);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(259, 698, 105, 39);
		contentPane.add(btnBack);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = txtTitle.getText();
				int status = 0;
				if(cbxStatus.getSelectedIndex() == 0) {
					status = 1;
				}else if(cbxStatus.getSelectedIndex() == 1) {
					status = 0;
				}
				String specialization = (String) cbxSpecialization.getSelectedItem();
				String student = (String) cbxStudent.getSelectedItem();
				String lecturer = (String) cbxLecturer.getSelectedItem();
				String desc = txtDesc.getText();
				String comment = txtComment.getText();
				
				controller.addProject(title, status, specialization, student, lecturer, desc, comment);
			}
		});
		btnAdd.setVisible(false);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(144, 698, 105, 39);
		contentPane.add(btnAdd);
		
		cbxSpecialization = new JComboBox<>();
		cbxSpecialization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.updateStudentList((String) cbxSpecialization.getSelectedItem());
				
				if(cbxStudent.getModel().getSize() == 0) return;
				cbxStudent.setSelectedIndex(0);
			}
		});
		cbxSpecialization.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxSpecialization.setBounds(144, 150, 321, 27);
		contentPane.add(cbxSpecialization);
		cbxSpecialization.setModel(cbxSpecModel);
		
		JLabel lblStudent = new JLabel("Student");
		lblStudent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStudent.setBounds(20, 204, 71, 13);
		contentPane.add(lblStudent);
		
		cbxStudent = new JComboBox<>();
		cbxStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxStudent.setBounds(144, 194, 321, 27);
		cbxStudent.setModel(cbxStudentModel);
		contentPane.add(cbxStudent);
		
		JLabel lblFiles = new JLabel("Files");
		lblFiles.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFiles.setBounds(20, 526, 89, 13);
		contentPane.add(lblFiles);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(144, 556, 440, 122);
		contentPane.add(scrollPane_2);
		
		JList<String> listFile = new JList<>();
		listFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(listFile.getSelectedIndex() == -1) return;
				
				if(e.getClickCount() == 2) {
					File fileToOpen = new File(listFile.getSelectedValue());
				    try {
						Desktop.getDesktop().open(fileToOpen);
					} catch (IOException e1) {}
				}
			}
		});
		scrollPane_2.setViewportView(listFile);
		listFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		listFile.setModel(listModel);
		listFile.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				Component frame = null;

				// Show the dialog; wait until dialog is closed
				chooser.showOpenDialog(frame);

				// Retrieve the selected files.
				File[] files = chooser.getSelectedFiles();
				
				// Move files
				controller.uploadFile(files);
			}
		});
		btnUpload.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpload.setBounds(144, 514, 89, 32);
		contentPane.add(btnUpload);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listFile.getSelectedIndex() == -1) return;
				
				String filePath = listModel.get(listFile.getSelectedIndex());
				
				// Remove
				File removeFile = new File(filePath);
				int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this file?");
				
				if(confirmDelete == JOptionPane.OK_OPTION) {
					boolean deleteStatus = controller.deleteFile(removeFile);
					if(deleteStatus) {
						listModel.remove(listFile.getSelectedIndex());
					}else {
						JOptionPane.showMessageDialog(null, 
								"The selected file has failed to be deleted! Please contact the administrator.", 
								"File Delete Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemove.setBounds(243, 514, 89, 32);
		contentPane.add(btnRemove);
		
		JLabel lblStatus_1 = new JLabel("Specialization");
		lblStatus_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStatus_1.setBounds(20, 153, 105, 17);
		contentPane.add(lblStatus_1);
		
		JLabel lblLecturer = new JLabel("Lecturer");
		lblLecturer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLecturer.setBounds(20, 251, 71, 13);
		contentPane.add(lblLecturer);
		
		cbxLecturer = new JComboBox<String>();
		cbxLecturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxLecturer.setBounds(144, 241, 321, 27);
		contentPane.add(cbxLecturer);
		cbxLecturer.setModel(cbxLecturerModel);
	}
	
	public ProjectDetail(String prev) {
		this(); // calling the default constructor to load all GUI
		this.prev = prev;
		
		JFrame frame = this;
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame view = null;
				
				if(prev.equals("Admin")) {
					view = new ProjectList();
				}else if(prev.equals("Report")) {
					view = new ReportList();
				}else if(prev.equals("User")) {
					view = new UserDashboard();
				}else {
					// If there is no previous window then dont close the window
					return;
				}
				
				view.setVisible(true);
				frame.dispose();
				removeUnnecessaryUploadedFile();
			}
		});
		
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame view = null;
				
				if(prev.equals("Admin")) {
					view = new ProjectList();
				}else if(prev.equals("Report")) {
					view = new ReportList();
				}else if(prev.equals("User")) {
					view = new UserDashboard();
				}else {
					// If there is no previous window then dont close the window
					return;
				}
				
				view.setVisible(true);
				frame.dispose();
			}
		});
	}
	
	public ProjectDetail(String prev, boolean add) {
		this(prev); // calling the second constructor to setup back button that navigate back to previous screen
		
		btnBack.setVisible(true);
		btnAdd.setVisible(true);
		lectPanel.setVisible(false);
		
		controller = new ProjectDetailController(null, this);
		controller.updateLecturerList();
		controller.updateSpecialization();
		controller.updateStudentList((String) cbxSpecialization.getSelectedItem());
	}
	
	public ProjectDetail(String prev, Project proj) {
		this(prev);
		
		btnBack.setVisible(false);
		btnAdd.setVisible(false);
		lectPanel.setVisible(true);
		
		// Load project details
		txtTitle.setText(proj.getName());
		txtDesc.setText(proj.getDescription());
		txtComment.setText(proj.getComment());
		
		int status = 0;
		
		if(proj.getStatus() == 0) {
			status = 1;
		}else {
			status = 0;
		}
		cbxStatus.setSelectedIndex(status);
		
		controller = new ProjectDetailController(proj, this);
		controller.updateLecturerList();
		
		for(int i = 0; i < cbxLecturerModel.getSize(); i++) {
			String data = cbxLecturerModel.getElementAt(i);
			
			if(proj.getLecturer().getFullName().equals(data)) {
				cbxLecturer.setSelectedIndex(i);
				break;
			}
		}
		
		// Must load specialization in order to load student names because student names option will only appear
		// after loading specialization		
		controller.updateSpecialization();
		cbxSpecialization.setSelectedItem(proj.getSpecialization().getName());
		controller.updateStudentList((String) cbxSpecialization.getSelectedItem());
		
		// Reset to default "None" option for student before finding assigned student
		cbxStudent.setSelectedIndex(0);
		// Update to the specific student for the existing project
		for(int i = 0; i < cbxStudentModel.getSize(); i++) {
			String data = cbxStudentModel.getElementAt(i);
			
			// Break the loop when the project is not assigned to anyone because update is not needed at this point
			if(!proj.isAssigned()) break;
			
			if(data.equals("None")) continue;
			
			if(proj.getStudent().getFullName().equals(data)) {
				cbxStudent.setSelectedIndex(i);
				break;
			}
		}
		
		// Load files
		for(File file : proj.getUploadedFiles()) {
			listModel.addElement(file.getPath());
		}
	}

	public void updateSpecializationList(List<Specialization> specializations) {
		for(Specialization spec : specializations) {
			cbxSpecModel.addElement(spec.getName());
		}
	}

	public void updateStudents(List<Student> students) {
		cbxStudentModel.removeAllElements();
		cbxStudentModel.addElement("None");
		for(Student stud : students) {
			cbxStudentModel.addElement(stud.getFullName());
		}
	}

	public void updateLecturers(List<Lecturer> lecturers) {
		for(Lecturer lect : lecturers) {
			cbxLecturerModel.addElement(lect.getFullName());
		}
	}

	public void updateLecturer(Lecturer lecturer) {
		cbxLecturerModel.addElement(lecturer.getFullName());
		cbxLecturer.setEnabled(false);
		txtComment.setEnabled(false);
	}

	public void showAddProjectSuccessMessage() {
		JOptionPane.showMessageDialog(null, "You have successfully created a project",
				"Project Added", JOptionPane.INFORMATION_MESSAGE);
		
		// Clear form - Reset to default
		txtTitle.setText("");
		txtDesc.setText("");
		txtComment.setText("");
		listModel.removeAllElements();
		controller = new ProjectDetailController(null, this);
		controller.updateLecturerList();
		controller.updateSpecialization();
		controller.updateStudentList((String) cbxSpecialization.getSelectedItem());
	}
	
	public void removeUnnecessaryUploadedFile() {
		// Handle removing uploaded file if the project is not added
		File dir = new File(Storage.getUploadFilePath(Project.getIncrementId()));
		if(dir.exists()) {
			for(File file : dir.listFiles()) {
				file.delete();
			}
		}
	}

	public void showFileDuplicateErrorMessage() {
		JOptionPane.showMessageDialog(null, "A file with this file name has already existed."
				+ " Please try again with another file",
				"Duplicate File", JOptionPane.WARNING_MESSAGE);
	}

	public void addFileToList(List<String> uploadedFiles) {
		for(String uploadedPath : uploadedFiles) {
			listModel.addElement(uploadedPath);
		}
	}

	public void showNoSpecializationErrorMessage() {
		JOptionPane.showMessageDialog(null, "Please select a specialization to continue.",
				"No Specialization", JOptionPane.WARNING_MESSAGE);
	}

	public void showNoLecturerErrorMessage() {
		JOptionPane.showMessageDialog(null, "Please select a lecturer to continue.",
				"No Lecturer", JOptionPane.WARNING_MESSAGE);
	}

	public void showNoTitleErrorMessage() {
		JOptionPane.showMessageDialog(null, "Please fill in project title and try again",
				"Title Required", JOptionPane.WARNING_MESSAGE);
	}

	public void showEditProjectSuccessMessage() {
		JOptionPane.showMessageDialog(null, "You have successfully saved this project",
				"Project Saved", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showDeleteProjectSuccessMessage() {
		JOptionPane.showMessageDialog(null, "You have successfully deleted this project",
				"Project Deleted", JOptionPane.INFORMATION_MESSAGE);
	}

	public void updateStudentView() {
		txtTitle.setEnabled(false);
		txtDesc.setEnabled(false);
		txtComment.setEnabled(false);
		btnBack.setVisible(true);
		btnAdd.setVisible(false);
		lectPanel.setVisible(false);
		btnUpload.setVisible(false);
		btnRemove.setVisible(false);
		cbxSpecialization.setEnabled(false);
		cbxStatus.setEnabled(false);
		cbxStudent.setEnabled(false);
		cbxLecturer.setEnabled(false);
	}
}
