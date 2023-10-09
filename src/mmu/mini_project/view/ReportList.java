package mmu.mini_project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mmu.mini_project.controller.ReportListController;
import mmu.mini_project.model.Project;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author JORDAN, WONG JIA XIAN
 * @author SHRAVAN, JORDAN (UI)
 */

public class ReportList extends JFrame {

	private JPanel contentPane;
	private JLabel lblReportList;
	private DefaultTableModel tableModel;
	
	private JTable table;
	private JButton btnBack;
	private JComboBox<String> cbxFilter;
	private JLabel lblOrder;
	private JComboBox<String> cbxOrder;
	private ReportListController controller = new ReportListController(this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportList frame = new ReportList();
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
	public ReportList() {
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
		
		lblReportList = new JLabel("Report List");
		lblReportList.setBounds(20, 26, 289, 27);
		lblReportList.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblReportList);
		
		JLabel lblProjects = new JLabel("Filter");
		lblProjects.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProjects.setBounds(20, 75, 56, 27);
		contentPane.add(lblProjects);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 125, 753, 274);
		contentPane.add(scrollPane);
		
		String[] tableColumn = {"ID", "Title", "Specialization", "Lecturer Name"};
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
		
		cbxFilter = new JComboBox<>();
		cbxFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxFilter.getSelectedIndex() == -1) return;
				
				cbxOrder.setVisible(false);
				switch(cbxFilter.getSelectedIndex()) {
				case 0:
					controller.getAllProjects();
					break;
				case 1:
					cbxOrder.setVisible(true);
					controller.getProjectsBySpecialization(cbxOrder.getSelectedIndex());
					break;
				case 2:
					cbxOrder.setVisible(true);
					controller.getProjectsByLecturerName(cbxOrder.getSelectedIndex());
					break;
				case 3:
					controller.getActiveProjects();
					break;
				case 4:
					controller.getInactiveProjects();
					break;
				case 5:
					controller.getAssignedProjects();
					break;
				case 6:
					controller.getUnassignedProjects();
					break;
				case 7:
					controller.getProjectsWithComment();
					break;
				}
			}
		});
		DefaultComboBoxModel<String> listModel = new DefaultComboBoxModel<>();
		listModel.addElement("All Projects");
		listModel.addElement("Project Specialization");
		listModel.addElement("Lecturer Name");
		listModel.addElement("Active Projects");
		listModel.addElement("Inactive Projects");
		listModel.addElement("Assigned Projects");
		listModel.addElement("Not Assigned Projects");
		listModel.addElement("Projects With Comments");
		cbxFilter.setModel(listModel);
		cbxFilter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxFilter.setBounds(86, 81, 272, 21);
		contentPane.add(cbxFilter);
		
		lblOrder = new JLabel("Order");
		lblOrder.setVisible(false);
		lblOrder.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOrder.setBounds(391, 75, 56, 27);
		contentPane.add(lblOrder);
		
		cbxOrder = new JComboBox<String>();
		cbxOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxOrder.getSelectedIndex() == -1) return;
				if(cbxFilter.getSelectedIndex() == -1) return;
				
				switch(cbxFilter.getSelectedIndex()) {
				case 1:
					controller.getProjectsBySpecialization(cbxOrder.getSelectedIndex());
					break;
				case 2:
					controller.getProjectsByLecturerName(cbxOrder.getSelectedIndex());
					break;
				}
			}
		});
		cbxOrder.setVisible(false);
		cbxOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxOrder.setBounds(457, 81, 195, 21);
		DefaultComboBoxModel<String> orderModel = new DefaultComboBoxModel<>();
		orderModel.addElement("Ascending");
		orderModel.addElement("Descending");
		cbxOrder.setModel(orderModel);
		contentPane.add(cbxOrder);
		
		// Default show all projects
		controller.getAllProjects();
	}
	
	public void selectProjectFromList(Project proj) {
		// Navigate to project details
		ProjectDetail view = new ProjectDetail("Report", proj);
		view.setVisible(true);
		this.dispose();
	}

	public void showProjects(List<Project> model) {
		tableModel.setRowCount(0); // clear table before populating the table
		for(Project proj : model) {
			tableModel.addRow(new String[] {String.valueOf(proj.getId()), proj.getName(), proj.getSpecialization().getName(), proj.getLecturer().getFullName()});
		}
	}
}
