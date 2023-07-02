package view.course;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import br.ufc.coop.trabalhobd.Entities.Course;
import br.ufc.coop.trabalhobd.Managers.CourseManager;
import br.ufc.coop.trabalhobd.VOs.CourseClass;
import javax.swing.JComboBox;
import java.awt.Color;

public class ViewCourseClasses extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(Course course) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCourseClasses frame = new ViewCourseClasses(course);
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
	public ViewCourseClasses(Course course) {
		setResizable(false);
		setTitle(course.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		DefaultListModel<String> demoList = new DefaultListModel<String>();

		CourseManager courseManager = new CourseManager();

		for(Course c : courseManager.getCourse()){
			demoList.add(0, c.getName());
		}
		contentPane.setSize(350, 200);

		DefaultComboBoxModel<String> periodsList = new DefaultComboBoxModel<String>();

		periodsList.addElement("Selecione um período");
		for(String p : courseManager.getPeriods(course.getCode())){
			periodsList.addElement(p);
		}	   

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

		tableModel.addColumn("Matrícula");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Nota");
		tableModel.addColumn("Frequência");

		JComboBox<String> comboBox = new JComboBox<String>(periodsList);
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = comboBox.getSelectedItem().toString();

				if (selectedItem == "Selecione um período") {
					tableModel.setRowCount(0);
				}
				else {		    			
					List<CourseClass> courseClasses = courseManager.GetCourseClass(course.getCode(), selectedItem);

					tableModel.setRowCount(0);

					for (CourseClass cc : courseClasses) {
						Object[] row = {
								cc.getStudentRegistration(), cc.getStudentName(), cc.getGrade(), cc.getAttendance() + "%" 
						};
						tableModel.addRow(row);
					}		    

				}

			}
		});
		comboBox.setFont(new Font("Arial", Font.PLAIN, 12));

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setFont(new Font("Arial", Font.PLAIN, 12));

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setToolTipText("Voltar a disciplina");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCourse VC = new ViewCourse(course);
				fecharJanela();
				VC.setVisible(true);

			}
		});


		JLabel lblDisciplina = new JLabel("Matrícula");
		lblDisciplina.setHorizontalAlignment(SwingConstants.LEFT);
		lblDisciplina.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblPeriodo = new JLabel("Nome");
		lblPeriodo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPeriodo.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblNota = new JLabel("Nota");
		lblNota.setHorizontalAlignment(SwingConstants.LEFT);
		lblNota.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblFrequncia = new JLabel("Frequência");
		lblFrequncia.setHorizontalAlignment(SwingConstants.LEFT);
		lblFrequncia.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblNewLabel = new JLabel("Disciplina: " + course.getName());
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));


		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(table, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblDisciplina, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
										.addComponent(lblPeriodo, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblNota, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
										.addGap(30)
										.addComponent(lblFrequncia, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDisciplina, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFrequncia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNota, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPeriodo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(1)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton)
						.addGap(21))
				);
		contentPane.setLayout(gl_contentPane);
		contentPane.setVisible(true);
	}

	private void fecharJanela() {
		setVisible(false);
		dispose();
	}
}
