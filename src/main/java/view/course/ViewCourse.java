package view.course;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
import br.ufc.coop.trabalhobd.Managers.CourseManager;

public class ViewCourse extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField creditsField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(Course course) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCourse frame = new ViewCourse(course);
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
	public ViewCourse(Course course) {
		setResizable(false);
		setTitle("Editar disciplina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblCdigo = new JLabel("Código:");
		lblCdigo.setFont(new Font("Arial", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setEditable(false);
		textField.setText(Long.toString(course.getCode()));
		textField.setToolTipText("Código da disciplina");
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		nameField = new JTextField();
		nameField.setFont(new Font("Arial", Font.PLAIN, 12));
		nameField.setToolTipText("Nome da disciplina");
		nameField.setText(course.getName());
		nameField.setColumns(10);
		
		JLabel creditsLabel = new JLabel("Créditos:");
		creditsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		creditsField = new JTextField();
		creditsField.setFont(new Font("Arial", Font.PLAIN, 12));
		creditsField.setText(Integer.toString(course.getCredits()));
		creditsField.setToolTipText("Quantidade de créditos");
		creditsField.setColumns(10);
		creditsField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = creditsField.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 11 || ke.getKeyCode() == 8) {
	            	creditsField.setEditable(true);
	            } else {
	            	creditsField.setEditable(false);
	            }
	         }
	      });
		
		CourseManager courseManager = new CourseManager();
						
		JButton btnEditStudent = new JButton("Salvar");
		btnEditStudent.setToolTipText("Salvar disciplina");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (nameField.getText().isBlank() || creditsField.getText().isBlank()) {
					String mensagem = "Preencha todos os campos!";
				    JOptionPane.showMessageDialog(null, mensagem);
				}
				else {											        		        
					Course courseUpdate = new Course(course.getCode(), nameField.getText(), Integer.parseInt(creditsField.getText()));
										
					courseManager.updateCourse(courseUpdate);
					ViewCourseList VCL = new ViewCourseList();
					fecharJanela();
					VCL.setVisible(true);
					String mensagem = "Disciplina atualizada com sucesso!";
					JOptionPane.showMessageDialog(null, mensagem);
				}											
			}
		});
		btnEditStudent.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnBack = new JButton("Voltar");
		btnBack.setToolTipText("Voltar para a lista");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCourseList VCL = new ViewCourseList();
				fecharJanela();
				VCL.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setToolTipText("Excluir disciplina");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				courseManager.deleteCourse(course.getCode());
				
				ViewCourseList VCL = new ViewCourseList();				
				fecharJanela();
				VCL.setVisible(true);
				String mensagem = "Disciplina excluída com sucesso!";
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnTurmas = new JButton("Ver turmas");
		btnTurmas.setToolTipText("Ver turmas");
		btnTurmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCourseClasses VCC = new ViewCourseClasses(course);
				fecharJanela();
				VCC.setVisible(true);
			}
		});
		btnTurmas.setFont(new Font("Arial", Font.PLAIN, 12));
				
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
							.addComponent(btnBack)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditStudent))
						.addComponent(lblCdigo)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(nameField, 229, 229, 229)
						.addComponent(creditsLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addComponent(creditsField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTurmas))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCdigo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(creditsLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(creditsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnTurmas)
					.addGap(150)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir)
						.addComponent(btnEditStudent)
						.addComponent(btnBack))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void fecharJanela() {
        setVisible(false);
        dispose();
    }
}