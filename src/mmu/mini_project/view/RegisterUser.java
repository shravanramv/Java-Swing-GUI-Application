package mmu.mini_project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import mmu.mini_project.controller.RegisterUserController;
import mmu.mini_project.model.Specialization;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

/**
 * @author LIM WEI PANG, LOW YONG ZHI, JORDAN
 * @author SHRAVAN, LOW YONG ZHI, LIM WEI PANG, JORDAN (UI)
 */

public class RegisterUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtFullName;
	private JTextField txtEmail;
	private JPasswordField txtConfirmPass;
	private JDateChooser dteDob;
	private DefaultComboBoxModel<String> cbxSpec;
	private JComboBox<String> cbxSpecialization;
	private JComboBox<String> cbxType;
	private RegisterUserController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUser frame = new RegisterUser();
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
	public RegisterUser() {
		controller = new RegisterUserController(null, this);
		JFrame frame = this;
		
		setTitle("MMU Mini Project Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 516);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserRegistration = new JLabel("User Registration");
		lblUserRegistration.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblUserRegistration.setBounds(10, 13, 289, 27);
		contentPane.add(lblUserRegistration);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDashboard view = new AdminDashboard();
				view.setVisible(true);
				frame.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(533, 13, 102, 27);
		contentPane.add(btnBack);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(25, 110, 114, 13);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername.setColumns(10);
		txtUsername.setBounds(180, 103, 440, 26);
		contentPane.add(txtUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(25, 284, 114, 13);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setBounds(180, 279, 440, 27);
		contentPane.add(txtPassword);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFullName.setBounds(25, 153, 114, 13);
		contentPane.add(lblFullName);
		
		txtFullName = new JTextField();
		txtFullName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFullName.setColumns(10);
		txtFullName.setBounds(180, 146, 440, 26);
		contentPane.add(txtFullName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(25, 197, 114, 13);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(180, 190, 440, 26);
		contentPane.add(txtEmail);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserType.setBounds(25, 65, 114, 13);
		contentPane.add(lblUserType);
		
		cbxType = new JComboBox<>();
		DefaultComboBoxModel<String> cbxModel = new DefaultComboBoxModel<>();
		cbxModel.addElement("Student");
		cbxModel.addElement("Lecturer");
		cbxModel.addElement("Administrator");
		cbxType.setModel(cbxModel);
		cbxType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxType.setBounds(180, 61, 173, 21);
		contentPane.add(cbxType);
		
		dteDob = new JDateChooser();
		dteDob.setBounds(180, 238, 153, 21);
		contentPane.add(dteDob);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDateOfBirth.setBounds(25, 238, 114, 21);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(25, 331, 145, 13);
		contentPane.add(lblConfirmPassword);
		
		txtConfirmPass = new JPasswordField();
		txtConfirmPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtConfirmPass.setBounds(180, 324, 440, 27);
		contentPane.add(txtConfirmPass);
		
		cbxSpecialization = new JComboBox<String>();
		cbxSpecialization.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxSpecialization.setBounds(180, 374, 440, 21);
		cbxSpec = new DefaultComboBoxModel<>();
		controller.updateSpecialization();
		cbxSpecialization.setModel(cbxSpec);
		contentPane.add(cbxSpecialization);
		
		JLabel lblSpecialization = new JLabel("Specialization");
		lblSpecialization.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSpecialization.setBounds(25, 378, 114, 13);
		contentPane.add(lblSpecialization);
		
		JButton btnRegister = new JButton("Create Account");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = new String(txtPassword.getPassword());
				String confirmPass = new String(txtConfirmPass.getPassword());
				String fullName = txtFullName.getText();
				String email = txtEmail.getText();
				Date date = dteDob.getDate();
				String specialization = (String) cbxSpecialization.getSelectedItem();
				int role = 3;
				
				switch(cbxType.getSelectedIndex()) {
				case 0:
					role = 3;
					break;
				case 1:
					role = 2;
					break;
				case 2:
					role = 1;
					break;
				}
				
				controller.createAccount(username, password, confirmPass, fullName, email, role, date, specialization);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBounds(235, 421, 173, 37);
		contentPane.add(btnRegister);
		
		cbxType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxType.getSelectedItem().equals("Student")) {
					lblSpecialization.setVisible(true);
					cbxSpecialization.setVisible(true);
				}else {
					lblSpecialization.setVisible(false);
					cbxSpecialization.setVisible(false);
				}
			}
		});
	}
	
	public void updateSpecializationList(List<Specialization> specs) {
		for(Specialization spec : specs) {
			cbxSpec.addElement(spec.getName());
		}
	}
	
	public void showRegistrationPasswordErrorMessage() {
		JOptionPane.showMessageDialog(null, "Password and Confirm Password is not the same. Please try again.",
				"Register Failure", JOptionPane.WARNING_MESSAGE);
	}

	public void showRegistrationErrorMessage() {
		JOptionPane.showMessageDialog(null, "An error has occured while registering an account. Please contact administrator.",
				"Register Failure", JOptionPane.ERROR_MESSAGE);
	}

	public void showRegistrationSuccessMessage() {
		JOptionPane.showMessageDialog(null, "You have successfully registered an account",
				"Register Success", JOptionPane.INFORMATION_MESSAGE);
		
		// Clear form - Reset to default
		txtUsername.setText("");
		txtPassword.setText("");
		txtConfirmPass.setText("");
		txtFullName.setText("");
		txtEmail.setText("");
		dteDob.setDate(null);
		cbxSpecialization.setSelectedIndex(0);
		cbxType.setSelectedIndex(0);
	}

	public void showDuplicateUsernameErrorMessage() {
		JOptionPane.showMessageDialog(null, "This username has already existed. Please try again with another username",
				"Duplicate Username", JOptionPane.WARNING_MESSAGE);
	}

	public void showDuplicateEmailErrorMessage() {
		JOptionPane.showMessageDialog(null, "This email has already existed. Please try again with another email",
				"Duplicate Email", JOptionPane.WARNING_MESSAGE);
	}

	public void showDuplicateFullNameErrorMessage() {
		JOptionPane.showMessageDialog(null, "This full name has already existed. Please contact an administrator for help",
				"Duplicate Full Name", JOptionPane.WARNING_MESSAGE);
	}
}
