package mmu.mini_project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mmu.mini_project.controller.LoginController;
import mmu.mini_project.controller.SessionController;
import mmu.mini_project.model.User;
import mmu.mini_project.utils.Storage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/**
 * @author LOW YONG ZHI, LIM WEI PANG, JORDAN
 * @author SHRAVAN, LOW YONG ZHI, LIM WEI PANG (UI)
 */

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	LoginController controller = new LoginController(this, Storage.users);
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("MMU Mini Project Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 379);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("MMU Mini Project Management System");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle.setBounds(0, 32, 601, 36);
		contentPane.add(lblTitle);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(90, 114, 78, 23);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername.setBounds(184, 113, 308, 23);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(90, 174, 78, 23);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.login(txtUsername.getText(), new String(txtPassword.getPassword()));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(214, 249, 133, 36);
		contentPane.add(btnNewButton);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setBounds(184, 174, 308, 23);
		contentPane.add(txtPassword);
		
		controller.loadFiles();
	}
	
	public void loginSuccess() {
		SessionController session = SessionController.getSession();
		User user = session.getLoggedIn();
		
		if(session.hasLoggedIn()) {
			// navigate to page
			switch(user.getRole()) {
			case 1:
				AdminDashboard admin = new AdminDashboard();
				admin.setVisible(true);
				this.dispose();
				break;
			case 2:
			case 3:
				UserDashboard dashboard = new UserDashboard();
				dashboard.setVisible(true);
				this.dispose();
				break;
			default:
				JOptionPane.showMessageDialog(null, "An error has occured! Please contact administrator for help.", 
						"Login Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void loginFailed() {
		JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.", 
				"Login Failure", JOptionPane.WARNING_MESSAGE);
	}
}
