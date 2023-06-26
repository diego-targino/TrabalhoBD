package view.student;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.ufc.coop.trabalhobd.Entities.Course;
import br.ufc.coop.trabalhobd.Entities.Student;
import br.ufc.coop.trabalhobd.Entities.StudentCourse;
import br.ufc.coop.trabalhobd.Managers.CourseManager;
import br.ufc.coop.trabalhobd.Managers.StudentCourseManager;
import view.course.ViewCourseList;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class NewStudentCourse extends JFrame {

	private JPanel contentPane;
	private JTextField periodField;
	private JTextField gradeField;
	private JTextField attendanceField;

	/**
	 * Launch the application.
	 */
	public static void main(Student student) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewStudentCourse frame = new NewStudentCourse(student);
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
	public NewStudentCourse(Student student) {
		setResizable(false);
		setTitle("Adicionar resultado do aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);JLabel courseLabel = new JLabel("Disciplina:");
		courseLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		CourseManager courseManager = new CourseManager();
		StudentCourseManager studentCourseManager = new StudentCourseManager();
		
		DefaultComboBoxModel<String> coursesList = new DefaultComboBoxModel<String>();
		
		coursesList.addElement("Selecione a disciplina");
        for(Course c : courseManager.getCourse()){
        	coursesList.addElement(c.getName());
        }
        
        JComboBox courses = new JComboBox(coursesList);
        courses.setSelectedIndex(0);
        courses.setToolTipText("Selecione a disciplina");
        courses.setBackground(new Color(255, 255, 255));
        courses.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel periodLabel = new JLabel("Período:");
		periodLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		periodField = new JTextField();
		periodField.setToolTipText("Período correspondente");
		periodField.setColumns(10);
				
		
		JLabel lblNota = new JLabel("Nota:");
		lblNota.setToolTipText("Nota");
		lblNota.setFont(new Font("Arial", Font.PLAIN, 14));
		
		gradeField = new JTextField();
		gradeField.setToolTipText("Nota do aluno");
		gradeField.setColumns(10);
		gradeField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = gradeField.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 2 || ke.getKeyCode() == 8) {
	            	gradeField.setEditable(true);
	            } else {
	            	gradeField.setEditable(false);
	            }
	         }
	      });
		
		JLabel lblFrequencia = new JLabel("Frequência:");
		lblFrequencia.setToolTipText("Frequência do aluno");
		lblFrequencia.setFont(new Font("Arial", Font.PLAIN, 14));
		
		attendanceField = new JTextField();
		attendanceField.setToolTipText("Frequência do aluno");
		attendanceField.setColumns(10);
		attendanceField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = attendanceField.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == 8) {
	            	attendanceField.setEditable(true);
	            } else {
	            	attendanceField.setEditable(false);
	            }
	         }
	      });
						
		JButton btnEditStudent = new JButton("Salvar");
		btnEditStudent.setToolTipText("Salvar resultado");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								
				if (periodField.getText().isBlank() || gradeField.getText().isBlank() || attendanceField.getText().isBlank() || courses.getSelectedIndex() == 0) {
					String mensagem = "Preencha todos os campos!";
				    JOptionPane.showMessageDialog(null, mensagem);
				}		
				else {
					List<Course> coursesList = courseManager.getCourse();
					Course course = coursesList.stream().filter(s -> {return s.getName().equals(courses.getSelectedItem().toString());}).findAny().orElse(null);
					StudentCourse studentCourse = new StudentCourse(student.getRegistration(), course.getCode(), periodField.getText(), Float.parseFloat(gradeField.getText()), Integer.parseInt(attendanceField.getText()));
											
					studentCourseManager.addStudentCourse(studentCourse);
					ViewStudent VS = new ViewStudent(student);
					fecharJanela();
					VS.setVisible(true);
					String mensagem = "Resultado adicionado com sucesso!";
					JOptionPane.showMessageDialog(null, mensagem);
				}																
			}
		});
		btnEditStudent.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnBack = new JButton("Voltar");
		btnBack.setToolTipText("Voltar para o estudante");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				ViewStudent VS = new ViewStudent(student);
				fecharJanela();
				VS.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 12));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(courseLabel)
								.addContainerGap(298, Short.MAX_VALUE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(courses, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(periodLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addComponent(periodField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
								.addContainerGap(133, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNota, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addComponent(gradeField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(133, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnBack)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditStudent)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFrequencia)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(attendanceField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(133, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(courseLabel)
					.addGap(4)
					.addComponent(courses, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(periodLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(periodField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNota, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(gradeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblFrequencia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(attendanceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(138)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditStudent)
						.addComponent(btnBack))
					.addGap(231))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void fecharJanela() {
        setVisible(false);
        dispose();
    }
}