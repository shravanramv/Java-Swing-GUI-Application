package mmu.mini_project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mmu.mini_project.controller.ProjectListController;
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
 * @author WONG JIA XIAN, JORDAN
 * @author SHRAVAN, JORDAN (UI)
 */


public class ProjectList extends JFrame {

	private JPanel contentPane;
	private JButton btnAddProject;
	private JLabel lblProjectList;
	private DefaultTableModel tableModel;
	private ProjectListController controller = new ProjectListController(this);
	
	private JTable table;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectList frame = new ProjectList();
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
	public ProjectList() {
		JFrame frame = this;
		
		setTitle("MMU Mini Project Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 809, 595);
		setBounds(100, 100, 809, 467);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblProjectList = new JLabel("Project List");
		lblProjectList.setBounds(20, 26, 289, 27);
		lblProjectList.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblProjectList);
		
		JLabel lblProjects = new JLabel("Projects");
		lblProjects.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProjects.setBounds(20, 75, 159, 27);
		contentPane.add(lblProjects);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 125, 753, 274);
		contentPane.add(scrollPane);
		
		String[] tableColumn = {"ID", "Title"};
		tableModel = new DefaultTableModel(tableColumn, 0){
			@Override
			public boolean isCellEditable(int row, int column) {
			    return false;
			}
		};
		
		table = new JTable(tableModel);
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
				ProjectDetail view = new ProjectDetail("Admin", true);
				view.setVisible(true);
				frame.dispose();
			}
		});
		btnAddProject.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddProject.setBounds(185, 75, 128, 27);
		contentPane.add(btnAddProject);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDashboard view = new AdminDashboard();
				view.setVisible(true);
				frame.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(645, 26, 128, 27);
		contentPane.add(btnBack);
		
		controller.updateProject();
	}
	
	public void updateProjectList(List<Project> projects) {
		tableModel.setRowCount(0); // clear table before populating the table
		for(Project proj : projects) {
			tableModel.addRow(new String[] {String.valueOf(proj.getId()), proj.getName()});
		}
	}
	
	public void selectProjectFromList(Project proj) {
		// Navigate to project details
		ProjectDetail view = new ProjectDetail("Admin", proj);
		view.setVisible(true);
		this.dispose();
	}
}
