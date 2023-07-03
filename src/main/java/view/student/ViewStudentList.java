package view.student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import br.ufc.coop.trabalhobd.Managers.StudentManager;
import view.ViewHome;
import br.ufc.coop.trabalhobd.Entities.Student;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import javax.swing.border.EtchedBorder;
import javax.swing.JTable;

public class ViewStudentList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStudentList frame = new ViewStudentList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewStudentList() {
		setResizable(false);
		setTitle("Lista de estudantes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		StudentManager studentManager = new StudentManager();

		DefaultTableModel tableModel = new DefaultTableModel() {;
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
		};

		tableModel.addColumn("Nome");
		tableModel.addColumn("Média");

		List<Student> students = studentManager.getStudents();

		for (Student s : students) {
			Object[] row = {
					s.getName(), s.getMedia()
			};
			tableModel.addRow(row);
		}		

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setFont(new Font("Arial", Font.PLAIN, 12));

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JTable theTable = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = theTable.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && theTable.getSelectedRow() != -1) {
					Object o = theTable.getModel().getValueAt(row, 0);
					List<Student> students = studentManager.getStudents();
					Student student = students.stream().filter(s -> {return s.getName().equals(o.toString());}).findAny().orElse(null);
					ViewStudent VS = new ViewStudent(student);
					fecharJanela();
					VS.setVisible(true);
				}
			}
		});
		contentPane.setSize(350, 200);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setToolTipText("Voltar para o início");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewHome VH = new ViewHome();
				fecharJanela();
				VH.setVisible(true);
			}
		});

		JButton btnNew = new JButton();
		btnNew.setToolTipText("Adicionar novo estudante");
		btnNew.setText("Adicionar");
		btnNew.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewStudent NS = new NewStudent();
				fecharJanela();
				NS.setVisible(true);
			}
		});

		JLabel lblNewLabel = new JLabel("Estudantes cadastrados");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		textField = new JTextField();
		textField.setToolTipText("Pesquisar por nome ou email");
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				String value = textField.getText();
				int l = value.length();	
				tableModel.setRowCount(0);

				if (l <= 1) {	    		    
					for (Student s : studentManager.getStudents()) {
						Object[] row = {
								s.getName(), s.getMedia()
						};
						tableModel.addRow(row);
					}	
				} else {
					for(Student s : studentManager.SearchStudents(value)){		            	
						Object[] row = {
								s.getName(), s.getMedia()
						};
						tableModel.addRow(row);
					}
				}		            	
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Pesquisar:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblNewLabel_1_1 = new JLabel("Nome");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblNewLabel_1_1_1 = new JLabel("Média");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));


		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(table, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblNewLabel_1)
												.addGap(73)))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addGap(115)
										.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblNewLabel_1)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1_1)
								.addComponent(lblNewLabel_1_1))
						.addGap(1)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(btnNew))
						.addGap(16))
				);
		contentPane.setLayout(gl_contentPane);
		contentPane.setVisible(true);
	}

	private void fecharJanela() {
		setVisible(false);
		dispose();
	}
}
