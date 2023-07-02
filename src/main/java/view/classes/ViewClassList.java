package view.classes;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import br.ufc.coop.trabalhobd.Managers.CourseManager;
import br.ufc.coop.trabalhobd.VOs.CoursesPeriod;
import view.ViewHome;

public class ViewClassList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewClassList frame = new ViewClassList();
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
	public ViewClassList() {
		setResizable(false);
		setTitle("Visão geral das turmas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		CourseManager courseManager = new CourseManager();

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
		tableModel.addColumn("Período");
		tableModel.addColumn("Alunos");
		tableModel.addColumn("Maior nota");
		tableModel.addColumn("Menor nota");
		tableModel.addColumn("Média");

		for (CoursesPeriod cp : courseManager.SelectCoursesPeriod()) {
			Object[] row = {
					cp.getCourseName(), cp.getPeriod(), cp.getStudentAmount(), cp.getHighestGrade(), cp.getLowestGrade(), cp.getMedia()
			};
			tableModel.addRow(row);
		}		

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setFont(new Font("Arial", Font.PLAIN, 12));

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

		JLabel lblNewLabel = new JLabel("Turmas");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));

		lblNewLabel_2 = new JLabel("Período");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));

		lblNewLabel_3 = new JLabel("Alunos");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));

		lblNewLabel_4 = new JLabel("Maior nota");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));

		lblNewLabel_5 = new JLabel("Menor nota");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 12));

		lblNewLabel_6 = new JLabel("Média");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 12));


		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(table, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_1)
										.addGap(34)
										.addComponent(lblNewLabel_2)
										.addGap(26)
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
										.addGap(14)
										.addComponent(lblNewLabel_4)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblNewLabel_5)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(1)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton)
						.addGap(38))
				);
		contentPane.setLayout(gl_contentPane);
		contentPane.setVisible(true);
	}

	private void fecharJanela() {
		setVisible(false);
		dispose();
	}
}