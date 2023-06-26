package view.student;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

import br.ufc.coop.trabalhobd.Entities.Student;
import br.ufc.coop.trabalhobd.Managers.StudentManager;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ListSelectionModel;

public class NewStudent extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField emailField;
	private JTextField cellphoneField;
	private JTextField dayField;
	private JTextField monthField;
	private JTextField yearField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewStudent frame = new NewStudent();
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
	public NewStudent() {
		setResizable(false);
		setTitle("Adicionar estudante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		nameField = new JTextField();
		nameField.setToolTipText("Nome completo");
		//nameField.setText(student.getName());
		nameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		emailField = new JTextField();
		emailField.setToolTipText("Email");
		//emailField.setText(student.getEmail());
		emailField.setColumns(10);
		
		JLabel cellphoneLabel = new JLabel("Telefone:");
		cellphoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		cellphoneField = new JTextField();
		cellphoneField.setToolTipText("Número de telefone");
		cellphoneField.setColumns(10);
		cellphoneField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = cellphoneField.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 20 || ke.getKeyCode() == 8) {
	            	cellphoneField.setEditable(true);
	            } else {
	            	cellphoneField.setEditable(false);
	            }
	         }
	      });
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JList genderList = new JList();
		genderList.setToolTipText("Selecione o gênero");
		genderList.setVisibleRowCount(2);
		genderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		genderList.setFont(new Font("Arial", Font.PLAIN, 12));
		genderList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		genderList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Feminino", "Masculino"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
				
		dayField = new JTextField();
		dayField.setToolTipText("Dia");
		dayField.setFont(new Font("Arial", Font.PLAIN, 12));
		dayField.setColumns(10);
		dayField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = dayField.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 2 || ke.getKeyCode() == 8) {
	            	dayField.setEditable(true);
	            } else {
	            	dayField.setEditable(false);
	            }
	         }
	      });
		
		monthField = new JTextField();
		monthField.setToolTipText("Mês");
		monthField.setFont(new Font("Arial", Font.PLAIN, 12));
		monthField.setColumns(10);
		monthField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = monthField.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 2 || ke.getKeyCode() == 8) {
	            	monthField.setEditable(true);
	            } else {
	            	monthField.setEditable(false);
	            }
	         }
	      });
		
		yearField = new JTextField();
		yearField.setToolTipText("Ano");
		yearField.setFont(new Font("Arial", Font.PLAIN, 12));
		yearField.setColumns(10);
		yearField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = yearField.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 4 || ke.getKeyCode() == 8) {
	            	yearField.setEditable(true);
	            } else {
	            	yearField.setEditable(false);
	            }
	         }
	      });
		
		JLabel barLabel = new JLabel("/");
		barLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel barLabel_1 = new JLabel("/");
		barLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		StudentManager studentManager = new StudentManager();
				
		JButton btnEditStudent = new JButton("Salvar");
		btnEditStudent.setToolTipText("Salvar estudante");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (nameField.getText().isBlank() || emailField.getText().isBlank() || cellphoneField.getText().isBlank()
						|| dayField.getText().isBlank() || monthField.getText().isBlank() || yearField.getText().isBlank() 
						|| genderList.getSelectedIndex() == -1) {
					String mensagem = "Preencha todos os campos!";
				    JOptionPane.showMessageDialog(null, mensagem);
				}
				else if (!emailField.getText().contains("@")) {
					String mensagem = "Insira um email válido!";
					JOptionPane.showMessageDialog(null, mensagem);
				}
				else {
					int dayInt = Integer.parseInt(dayField.getText());
					int monthInt = Integer.parseInt(monthField.getText());
					int yearInt = Integer.parseInt(yearField.getText());
					
			        Calendar cal = Calendar.getInstance();
			        cal.set(Calendar.YEAR, yearInt);
			        cal.set(Calendar.MONTH, monthInt - 1);
			        cal.set(Calendar.DAY_OF_MONTH, dayInt);
			        
					java.util.Date date = cal.getTime();
			        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			        
					boolean gender = false;
					if (genderList.getSelectedIndex() == 0)
						gender = false;
					else if (genderList.getSelectedIndex() == 1)
						gender = true;
							        		        
					Student newStudent = new Student(nameField.getText(), emailField.getText(), cellphoneField.getText(), sqlDate, gender);
										
					studentManager.addStudent(newStudent);
					fecharJanela();
					String mensagem = "Estudante adicionado com sucesso!";
					JOptionPane.showMessageDialog(null, mensagem);
				}											
			}
		});
		btnEditStudent.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnBack = new JButton("Voltar");
		btnBack.setToolTipText("Voltar para a lista");
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
						.addComponent(nameField, 229, 229, 229)
						.addComponent(lblNewLabel))
					.addContainerGap(133, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addContainerGap(133, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(cellphoneLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addComponent(cellphoneField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(133, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(dayField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(barLabel, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(monthField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(barLabel_1, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(yearField, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblDataDeNascimento, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(221, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSexo, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addComponent(genderList, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(221, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(226, Short.MAX_VALUE)
					.addComponent(btnBack)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditStudent)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cellphoneLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cellphoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDataDeNascimento, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(monthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(barLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(dayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(barLabel_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSexo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(genderList)))
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
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
