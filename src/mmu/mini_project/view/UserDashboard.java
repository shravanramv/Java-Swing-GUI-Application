package mmu.mini_project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mmu.mini_project.controller.SessionController;
import mmu.mini_project.controller.UserDashboardController;
import mmu.mini_project.model.Project;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author SHRAVAN, LIM WEI PANG, LOW YONG ZHI
 * @author SHRAVAN, LOW YONG ZHI, LIM WEI PANG(UI)
 */

public class UserDashboard extends JFrame {

	private JPanel contentPane;
	private JLabel lblName;
	private JButton btnAddProject;
	private JLabel lblUserDashboard;
	private DefaultTableModel ownProjTableModel;
	private DefaultTableModel projTableModel;
	
	UserDashboardController controller =
			new UserDashboardController(this, SessionController.getSession().getLoggedIn());
	private JTable table;
	private JLabel lblProjects;
	private JTable table_1;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDashboard frame = new UserDashboard();
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
	public UserDashboard() {
		JFrame frame = this;
		
		setTitle("MMU Mini Project Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 809, 595);
		setBounds(100, 100, 809, 374);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUserDashboard = new JLabel("Student Dashboard");
		lblUserDashboard.setBounds(20, 26, 289, 27);
		lblUserDashboard.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblUserDashboard);
		
		JLabel lblNewLabel = new JLabel("Welcome back,");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(559, 30, 102, 24);
		contentPane.add(lblNewLabel);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBounds(671, 29, 102, 27);
		contentPane.add(btnLogout);
		
		lblName = new JLabel("");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(559, 53, 214, 18);
		contentPane.add(lblName);
		
		JLabel lblAssignedProjects = new JLabel("Assigned Projects");
		lblAssignedProjects.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAssignedProjects.setBounds(20, 75, 159, 27);
		contentPane.add(lblAssignedProjects);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 125, 753, 187);
		contentPane.add(scrollPane);
		
		String[] tableColumn = {"ID", "Title"};
		ownProjTableModel = new DefaultTableModel(tableColumn, 0){
			@Override
			public boolean isCellEditable(int row, int column) {
			    return false;
			}
		};
		
		table = new JTable(ownProjTableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Ignore any clicks that is not double click
				if(e.getClickCount() != 2) return;
				// Ignore clicks if it doesn't have selected record
				if(table.getSelectedRow() == -1) return;
				
				int id = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
				controller.showProject(id);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		btnAddProject = new JButton("Add Project");
		btnAddProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjectDetail view = new ProjectDetail("User", true);
				view.setVisible(true);
				frame.dispose();
			}
		});
		btnAddProject.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddProject.setBounds(185, 75, 128, 27);
		contentPane.add(btnAddProject);
		
		lblProjects = new JLabel("Projects");
		lblProjects.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProjects.setBounds(20, 335, 753, 27);
		contentPane.add(lblProjects);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 372, 751, 160);
		contentPane.add(scrollPane_1);
		
		projTableModel = new DefaultTableModel(tableColumn, 0){
			@Override
			public boolean isCellEditable(int row, int column) {
			    return false;
			}
		};
		table_1 = new JTable(projTableModel);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Ignore any clicks that is not double click
				if(e.getClickCount() != 2) return;
				// Ignore clicks if it doesn't have selected record
				if(table_1.getSelectedRow() == -1) return;
				
				int id = Integer.parseInt((String) table_1.getValueAt(table_1.getSelectedRow(), 0));
				controller.showProject(id);
			}
		});
		scrollPane_1.setViewportView(table_1);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		controller.updateName();
		controller.updateProject();
		controller.updateView();
	}
	
	public void updateLoggedInName(String name) {
		lblName.setText(name);
	}
	
	public void updateProjectList(List<Project> projects) {
		ownProjTableModel.setRowCount(0);
		for(Project proj : projects) {
			ownProjTableModel.addRow(new String[] {String.valueOf(proj.getId()), proj.getName()});
		}
	}
	
	public void updateOthersProjectList(List<Project> allProjects) {
		projTableModel.setRowCount(0);
		for(Project proj : allProjects) {
			projTableModel.addRow(new String[] {String.valueOf(proj.getId()), proj.getName()});
		}
	}
	
	public void updateUserView(int type) {
		if(type == 1) { // Student
			btnAddProject.setVisible(false);
			lblUserDashboard.setText("Student Dashboard");
			setBounds(100, 100, 809, 595);
		}else { // Lecturer
			btnAddProject.setVisible(true);
			lblUserDashboard.setText("Lecturer Dashboard");
			setBounds(100, 100, 809, 374);
		}
	}
	
	public void selectProjectFromList(Project proj) {
		// Navigate to project details
		ProjectDetail view = new ProjectDetail("User", proj);
		view.setVisible(true);
		this.dispose();
	}
	
	public void logout() {
		Login view = new Login();
		view.setVisible(true);
		this.dispose();
	}
}
