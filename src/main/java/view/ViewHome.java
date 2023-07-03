package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.classes.ViewClassList;
import view.course.ViewCourseList;
import view.student.ViewStudentList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class ViewHome extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHome frame = new ViewHome();
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
	public ViewHome() {
		setResizable(false);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Student Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Student Manager");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JButton students = new JButton("Estudantes");
		students.setToolTipText("Ver lista de estudantes");
		students.setFont(new Font("Arial", Font.PLAIN, 12));
		students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {             
				ViewStudentList VSL = new ViewStudentList();
				fecharJanela();
				VSL.setVisible(true);
			}
		});

		JButton disciplinas = new JButton("Disciplinas");
		disciplinas.setToolTipText("Ver lista de disciplinas");
		disciplinas.setFont(new Font("Arial", Font.PLAIN, 12));
		disciplinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {             
				ViewCourseList VCL = new ViewCourseList();
				fecharJanela();
				VCL.setVisible(true);
			}
		});

		JButton btnTurmas = new JButton("Turmas");
		btnTurmas.setToolTipText("Ver visão geral das turmas");
		btnTurmas.setFont(new Font("Arial", Font.PLAIN, 12));
		btnTurmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {             
				ViewClassList VCL = new ViewClassList();
				fecharJanela();
				VCL.setVisible(true);
			}
		});

		JButton btnClose = new JButton("Fechar");
		btnClose.setToolTipText("Finalizar a execução e sair");
		btnClose.setFont(new Font("Arial", Font.PLAIN, 12));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {             
				fecharJanela();
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(139)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnClose, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
								.addComponent(disciplinas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
								.addComponent(students, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnTurmas, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
						.addGap(138))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(students)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(disciplinas)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnTurmas)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnClose)
						.addContainerGap(212, Short.MAX_VALUE))
				);
		contentPane.setLayout(gl_contentPane);
	}

	private void fecharJanela() {
		setVisible(false);
		dispose();
	}
}