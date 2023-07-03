package view.student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import br.ufc.coop.trabalhobd.Entities.Student;
import br.ufc.coop.trabalhobd.Managers.StudentManager;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.awt.event.ActionEvent;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class ViewStudent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField emailField;
	private JTextField cellphoneField;
	private JTextField dayField;
	private JTextField monthField;
	private JTextField yearField;
	private JTextField registrationField;

	/**
	 * Launch the application.
	 */
	public static void main(Student student) {
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
		setResizable(false);
		setTitle("Editar estudante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		registrationField = new JTextField();
		registrationField.setToolTipText("Matrícula do estudante");
		registrationField.setEditable(false);
		registrationField.setText(Long.toString(student.getRegistration()));
		registrationField.setFont(new Font("Arial", Font.PLAIN, 12));
		registrationField.setColumns(10);

		JLabel lblMatrcula = new JLabel("Matrícula:");
		lblMatrcula.setFont(new Font("Arial", Font.PLAIN, 14));

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

		JLabel cellphoneLabel = new JLabel("Telefone:");
		cellphoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));

		cellphoneField = new JTextField();
		cellphoneField.setToolTipText("Número de telefone");
		cellphoneField.setText(student.getCellphone());
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

		int gender = 0;
		if (student.isGender())
			gender = 1;

		JList<Object> genderList = new JList<Object>();
		genderList.setToolTipText("Selecione o gênero");
		genderList.setVisibleRowCount(2);
		genderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		genderList.setFont(new Font("Arial", Font.PLAIN, 12));
		genderList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));		
		genderList.setModel(new AbstractListModel<Object>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Feminino", "Masculino"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		genderList.setSelectedIndex(gender);

		String birthDateString = student.getBirth_date().toString();

		dayField = new JTextField();
		dayField.setToolTipText("Dia");
		dayField.setFont(new Font("Arial", Font.PLAIN, 12));
		dayField.setText(birthDateString.substring(8, 10));
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
		monthField.setText(birthDateString.substring(5, 7));
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
		yearField.setText(birthDateString.substring(0, 4));
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

		JButton btnOptions = new JButton("Opções");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPopupMenu popupMenu = new JPopupMenu();
				addPopup(btnOptions, popupMenu);

				JMenuItem mntmNewMenuItem = new JMenuItem("Excluir");
				mntmNewMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
				mntmNewMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							studentManager.deleteStudent(student);
							ViewStudentList VSL = new ViewStudentList();
							VSL.setVisible(true);
							fecharJanela();
							String mensagem = "Estudante excluído com sucesso!";
							JOptionPane.showMessageDialog(null, mensagem);							
						}
						catch(Exception error) {
							System.out.println(error);
						}						
					}
				});
				popupMenu.add(mntmNewMenuItem);

				JMenuItem mntmNewMenuItem_1 = new JMenuItem("Adicionar resultado");
				mntmNewMenuItem_1.setFont(new Font("Arial", Font.PLAIN, 12));
				mntmNewMenuItem_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewStudentCourse NSC = new NewStudentCourse(student);
						fecharJanela();
						NSC.setVisible(true);
					}
				});
				popupMenu.add(mntmNewMenuItem_1);

				JMenuItem mntmNewMenuItem_2 = new JMenuItem("Histórico");
				mntmNewMenuItem_2.setFont(new Font("Arial", Font.PLAIN, 12));
				mntmNewMenuItem_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ViewStudentGrades VSG = new ViewStudentGrades(student);
						fecharJanela();
						VSG.setVisible(true);
					}
				});
				popupMenu.add(mntmNewMenuItem_2);

				popupMenu.show(btnOptions, 1, btnOptions.getHeight());               
			}
		});	
		btnOptions.setFont(new Font("Arial", Font.PLAIN, 12));

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

					Student studentUpdate = new Student(student.getRegistration(), nameField.getText(), emailField.getText(), cellphoneField.getText(), sqlDate, gender);

					studentManager.updateStudent(studentUpdate);
					fecharJanela();
					String mensagem = "Estudante atualizado com sucesso!";
					JOptionPane.showMessageDialog(null, mensagem);
				}											
			}
		});
		btnEditStudent.setFont(new Font("Arial", Font.PLAIN, 12));

		JButton btnBack = new JButton("Voltar");
		btnBack.setToolTipText("Voltar para a lista");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewStudentList VSL = new ViewStudentList(); 
				fecharJanela();
				VSL.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 12));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(nameField, 229, 229, 229)
												.addComponent(lblNewLabel))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(cellphoneLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
												.addComponent(cellphoneField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
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
								.addComponent(lblDataDeNascimento, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSexo, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addComponent(genderList, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
								.addComponent(registrationField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMatrcula)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addComponent(btnOptions)
										.addPreferredGap(ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
										.addComponent(btnBack)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnEditStudent)))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblMatrcula, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(registrationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
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
						.addGap(16)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnEditStudent)
								.addComponent(btnBack)
								.addComponent(btnOptions))
						.addGap(39))
				);

		contentPane.setLayout(gl_contentPane);
	}

	private void fecharJanela() {
		setVisible(false);
		dispose();
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
