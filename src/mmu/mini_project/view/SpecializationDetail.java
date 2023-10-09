package mmu.mini_project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import mmu.mini_project.controller.SpecializationDetailController;
import mmu.mini_project.model.Specialization;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author JORDAN
 * @author SHRAVAN, JORDAN (UI)
 */

public class SpecializationDetail extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private DefaultComboBoxModel<String> cbxStudentModel;
	private JPanel lectPanel;
	private JButton btnBack;
	private JButton btnAdd;
	private int type = 0;
	private SpecializationDetailController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpecializationDetail frame = new SpecializationDetail();
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
	private SpecializationDetail() {
		JFrame frame = this;
		
		setTitle("MMU Mini Project Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 204);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSpecialization = new JLabel("Specialization Detail");
		lblSpecialization.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpecialization.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblSpecialization.setBounds(10, 10, 584, 27);
		contentPane.add(lblSpecialization);
		
		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 68, 45, 13);
		contentPane.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtName.setColumns(10);
		txtName.setBounds(144, 61, 440, 26);
		contentPane.add(txtName);
		DefaultComboBoxModel<String> cbxModel = new DefaultComboBoxModel<>();
		cbxModel.addElement("Active");
		cbxModel.addElement("Inactive");
		
		lectPanel = new JPanel();
		lectPanel.setVisible(false);
		lectPanel.setBounds(0, 113, 606, 57);
		contentPane.add(lectPanel);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpecializationList view = new SpecializationList();
				view.setVisible(true);
				frame.dispose();
			}
		});
		btnBack_1.setBounds(386, 10, 112, 37);
		lectPanel.setLayout(null);
		btnBack_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lectPanel.add(btnBack_1);
		
		JButton btnEdit = new JButton("Save");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				
				controller.updateSpecialization(name);
			}
		});
		btnEdit.setBounds(142, 10, 112, 37);
		lectPanel.setLayout(null);
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lectPanel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this specialization?");
				
				if(option == JOptionPane.OK_OPTION) {
					controller.deleteSpecialization();
				}
			}
		});
		btnDelete.setBounds(264, 10, 112, 37);
		lectPanel.setLayout(null);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lectPanel.add(btnDelete);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpecializationList view = new SpecializationList();
				view.setVisible(true);
				frame.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(259, 123, 105, 39);
		contentPane.add(btnBack);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				controller.addSpecialization(name);
			}
		});
		btnAdd.setVisible(false);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(144, 123, 105, 39);
		contentPane.add(btnAdd);
		cbxStudentModel = new DefaultComboBoxModel<>();
		cbxStudentModel.addElement("None");
	}
	
	public SpecializationDetail(boolean add) {
		this();
		controller = new SpecializationDetailController(null, this);

		lectPanel.setVisible(false); // Hide edit buttons
		btnAdd.setVisible(true); // Show the buttons for adding specialization
		btnBack.setVisible(true);
	}
	
	public SpecializationDetail(Specialization spec) {
		this();
		controller = new SpecializationDetailController(spec, this);
		
		lectPanel.setVisible(true); // Show edit buttons
		btnAdd.setVisible(false); // Hide the buttons for adding specialization
		btnBack.setVisible(false);
		
		txtName.setText(spec.getName());
	}

	public void showDuplicateNameErrorMessage() {
		JOptionPane.showMessageDialog(null, "This specialization name has already existed. Please try again with another name",
				"Duplicate Name", JOptionPane.WARNING_MESSAGE);
	}

	public void showAddSpecializationSuccessMessage() {
		JOptionPane.showMessageDialog(null, "You have successfully created a specialization",
				"Success Creation", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showEditSpecializationSuccessMessage() {
		JOptionPane.showMessageDialog(null, "You have successfully edited this specialization",
				"Success Save", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showDeleteSpecializationSuccessMessage() {
		JOptionPane.showMessageDialog(null, "You have successfully deleted this specialization",
				"Success Delete", JOptionPane.INFORMATION_MESSAGE);
		
		SpecializationList view = new SpecializationList();
		view.setVisible(true);
		this.dispose();
	}

	public void showNoNameErrorMessage() {
		JOptionPane.showMessageDialog(null, "Please fill in specialization name and try again",
				"Name Required", JOptionPane.WARNING_MESSAGE);
	}
}
