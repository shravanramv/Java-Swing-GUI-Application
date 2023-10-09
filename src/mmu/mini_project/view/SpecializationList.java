package mmu.mini_project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mmu.mini_project.controller.SpecializationListController;
import mmu.mini_project.model.Specialization;
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
 * @author JORDAN
 * @author SHRAVAN, JORDAN (UI)
 */

public class SpecializationList extends JFrame {

	private JPanel contentPane;
	private JButton btnAddSpec;
	private JLabel lblSpecialization;
	private DefaultTableModel tableModel;
	
	private JTable table;
	private JButton btnBack;
	private SpecializationListController controller = new SpecializationListController(null, this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpecializationList frame = new SpecializationList();
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
	public SpecializationList() {
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
		
		lblSpecialization = new JLabel("Specialization List");
		lblSpecialization.setBounds(20, 26, 289, 27);
		lblSpecialization.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblSpecialization);
		
		JLabel lblSpec = new JLabel("Specializations");
		lblSpec.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSpec.setBounds(20, 75, 159, 27);
		contentPane.add(lblSpec);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 125, 753, 274);
		contentPane.add(scrollPane);
		
		String[] tableColumn = {"ID", "Name"};
		tableModel = new DefaultTableModel(tableColumn, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
			    return false;
			}
		};
		
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() != 2) return;
				if(table.getSelectedRow() == -1) return;
				
				int id = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
				controller.showSpecialization(id);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		btnAddSpec = new JButton("Add Specialization");
		btnAddSpec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpecializationDetail view = new SpecializationDetail(true);
				view.setVisible(true);
				frame.dispose();
			}
		});
		btnAddSpec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddSpec.setBounds(185, 75, 159, 27);
		contentPane.add(btnAddSpec);
		
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
		
		controller.updateSpecializationList();
		
//		controller.updateName();
//		controller.updateProject();
	}
	
	public void updateSpecializationList(List<Specialization> specs) {
		for(Specialization spec : specs) {
			tableModel.addRow(new String[] {String.valueOf(spec.getId()), spec.getName()});
		}
	}

	public void showSpecializationView(Specialization spec) {
		SpecializationDetail view = new SpecializationDetail(spec);
		view.setVisible(true);
		this.dispose();
	}
}
