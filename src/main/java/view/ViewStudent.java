package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufc.coop.trabalhobd.Entities.Student;
import br.ufc.coop.trabalhobd.Managers.StudentManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ViewStudent extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField emailField;

	/**
	 * Launch the application.
	 */
	public static void main(final Student student) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStudent frame = new ViewStudent(student);
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
	public ViewStudent(Student student) {
		setTitle("Editar estudante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setText(Long.toString(student.getId()));
		idField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		nameField = new JTextField();
		nameField.setText(student.getName());
		nameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		emailField = new JTextField();
		emailField.setText(student.getEmail());
		emailField.setColumns(10);
		
		
		StudentManager studentManager = new StudentManager();
		
		JButton btnDeleteStudent = new JButton("Excluir");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentManager.deleteStudent(student);
				fecharJanela();
				String mensagem = "Estudante excluído com sucesso!";
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		btnDeleteStudent.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnEditStudent = new JButton("Salvar");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (nameField.getText().isBlank() || emailField.getText().isBlank()) {
					String mensagem = "Preencha todos os campos!";
				    JOptionPane.showMessageDialog(null, mensagem);
				}
				else {					
					Student studentUpdate = new Student(student.getId(), nameField.getText(), emailField.getText());
					
					studentManager.updateStudent(studentUpdate);
					fecharJanela();
					String mensagem = "Estudante atualizado com sucesso!";
					JOptionPane.showMessageDialog(null, mensagem);
				}											
			}
		});
		btnEditStudent.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnBack = new JButton("Voltar");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharJanela();
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 12));
			
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnDeleteStudent)
							.addPreferredGap(ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
							.addComponent(btnBack)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditStudent))
						.addComponent(lblNewLabel_2)
						.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(nameField, 229, 229, 229)
						.addComponent(lblNewLabel)
						.addComponent(idField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDeleteStudent)
						.addComponent(btnEditStudent)
						.addComponent(btnBack))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void fecharJanela() {
        setVisible(false);
        ViewStudentList VSH = new ViewStudentList();
        VSH.setVisible(true);
        dispose();
    }
}
