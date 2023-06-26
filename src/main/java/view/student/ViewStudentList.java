package view.student;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;

import br.ufc.coop.trabalhobd.Managers.StudentManager;
import view.ViewHome;
import br.ufc.coop.trabalhobd.Entities.Student;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;


public class ViewStudentList extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		
		DefaultListModel<String> demoList = new DefaultListModel<String>();
		
		final StudentManager studentManager = new StudentManager();
		
        for(Student s : studentManager.getStudents()){
        	demoList.add(0, s.getName());
        }

	    JList<String> list = new JList<String>(demoList);
	    list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    list.setFont(new Font("Arial", Font.PLAIN, 12));
	    list.setVisibleRowCount(10);
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		 MouseListener mouseListener = new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		        JList<String> theList = (JList) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) {
		            final Object o = theList.getModel().getElementAt(index);
		            List<Student> students = studentManager.getStudents();
		            Student student = students.stream().filter(s -> {return s.getName().equals(o.toString());}).findAny().orElse(null);
		            ViewStudent VS = new ViewStudent(student);
		            fecharJanela();
		            VS.setVisible(true);
		          }
		        }
		      }
		    };
		    list.addMouseListener(mouseListener);
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
		            demoList.removeAllElements();
		            
		            if (l <= 1) {
		            	for(Student s : studentManager.getStudents()){		            	
				        	demoList.add(0, s.getName());
				        }
		            } else {
			            for(Student s : studentManager.SearchStudents(value)){		            	
			            	demoList.add(0, s.getName());
			            }
		            }		            	
		         }
		      });
		    
		    JLabel lblNewLabel_1 = new JLabel("Pesquisar:");
		    lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		    
		    GroupLayout gl_contentPane = new GroupLayout(contentPane);
		    gl_contentPane.setHorizontalGroup(
		    	gl_contentPane.createParallelGroup(Alignment.LEADING)
		    		.addGroup(gl_contentPane.createSequentialGroup()
		    			.addContainerGap()
		    			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
		    				.addComponent(list, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
		    				.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
		    				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
		    					.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
		    					.addPreferredGap(ComponentPlacement.RELATED)
		    					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
		    				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
		    					.addComponent(textField, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
		    					.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
		    						.addComponent(lblNewLabel_1)
		    						.addGap(73))))
		    			.addContainerGap())
		    );
		    gl_contentPane.setVerticalGroup(
		    	gl_contentPane.createParallelGroup(Alignment.TRAILING)
		    		.addGroup(gl_contentPane.createSequentialGroup()
		    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		    			.addComponent(lblNewLabel)
		    			.addGap(11)
		    			.addComponent(lblNewLabel_1)
		    			.addPreferredGap(ComponentPlacement.RELATED)
		    			.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    			.addPreferredGap(ComponentPlacement.RELATED)
		    			.addComponent(list, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
		    			.addPreferredGap(ComponentPlacement.RELATED)
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
