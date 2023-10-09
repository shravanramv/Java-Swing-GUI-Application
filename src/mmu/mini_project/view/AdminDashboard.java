package mmu.mini_project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mmu.mini_project.controller.AdminDashboardController;
import mmu.mini_project.controller.SessionController;
import mmu.mini_project.model.Admin;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author SHRAVAN, JORDAN
 */

public class AdminDashboard extends JFrame {

	private JPanel contentPane;
	private JLabel lblName;
	private AdminDashboardController controller = 
			new AdminDashboardController((Admin) SessionController.getSession().getLoggedIn(), this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard frame = new AdminDashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @author SHRAVAN
	 * Create the frame.
	 */
	public AdminDashboard() {
		JFrame frame = this;
		
		setTitle("MMU Mini Project Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 264);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminDashboard = new JLabel("Admin Dashboard");
		lblAdminDashboard.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAdminDashboard.setBounds(10, 10, 289, 27);
		contentPane.add(lblAdminDashboard);
		
		JLabel lblNewLabel = new JLabel("Welcome back,");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(243, 10, 102, 24);
		contentPane.add(lblNewLabel);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBounds(355, 9, 102, 27);
		contentPane.add(btnLogout);
		
		lblName = new JLabel("");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(243, 33, 214, 18);
		contentPane.add(lblName);
		
		JButton btnAccountRegister = new JButton("Account Registration");
		btnAccountRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUser view = new RegisterUser();
				view.setVisible(true);
				frame.dispose();
			}
		});
		btnAccountRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAccountRegister.setBounds(10, 82, 204, 54);
		contentPane.add(btnAccountRegister);
		
		JButton btnProjectList = new JButton("Project List");
		btnProjectList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjectList view = new ProjectList();
				view.setVisible(true);
				frame.dispose();
			}
		});
		btnProjectList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnProjectList.setBounds(253, 82, 204, 54);
		contentPane.add(btnProjectList);
		
		JButton btnReportList = new JButton("Report List");
		btnReportList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportList view = new ReportList();
				view.setVisible(true);
				frame.dispose();
			}
		});
		btnReportList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReportList.setBounds(10, 156, 204, 54);
		contentPane.add(btnReportList);
		
		JButton btnSpecialization = new JButton("Specialization List");
		btnSpecialization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpecializationList view = new SpecializationList();
				view.setVisible(true);
				frame.dispose();
			}
		});
		btnSpecialization.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSpecialization.setBounds(253, 156, 204, 54);
		contentPane.add(btnSpecialization);
		
		controller.updateName();
	}
	
	public void logout() {
		Login view = new Login();
		view.setVisible(true);
		this.dispose();
	}
	
	public void updateLoggedInName(String name) {
		lblName.setText(name);
	}

}
