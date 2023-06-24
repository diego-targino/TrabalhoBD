package view;

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
import br.ufc.coop.trabalhobd.Entities.Student;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class ViewStudentList extends JFrame {

	private JPanel contentPane;

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
		setTitle("Lista de estudantes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		DefaultListModel<String> demoList = new DefaultListModel<String>();
		
		final StudentManager studentManager = new StudentManager();
		
        for(Student s : studentManager.getStudents()){
        	demoList.add(0, s.getName());
        }

	    JList<String> list = new JList<String>(demoList);
	    list.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), null));
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
		    btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		    btnNewButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		ViewHome VH = new ViewHome();
		    		fecharJanela();
					VH.setVisible(true);
		    	}
		    });
		    
		    JButton btnNew = new JButton();
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
		    GroupLayout gl_contentPane = new GroupLayout(contentPane);
		    gl_contentPane.setHorizontalGroup(
		    	gl_contentPane.createParallelGroup(Alignment.LEADING)
		    		.addGroup(gl_contentPane.createSequentialGroup()
		    			.addContainerGap()
		    			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
		    				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
		    				.addComponent(list, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
		    				.addGroup(gl_contentPane.createSequentialGroup()
		    					.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
		    					.addPreferredGap(ComponentPlacement.RELATED)
		    					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
		    			.addContainerGap())
		    );
		    gl_contentPane.setVerticalGroup(
		    	gl_contentPane.createParallelGroup(Alignment.TRAILING)
		    		.addGroup(gl_contentPane.createSequentialGroup()
		    			.addComponent(lblNewLabel)
		    			.addPreferredGap(ComponentPlacement.RELATED)
		    			.addComponent(list, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
		    			.addGap(11)
		    			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
		    				.addComponent(btnNewButton)
		    				.addComponent(btnNew))
		    			.addGap(9))
		    );
		    contentPane.setLayout(gl_contentPane);
		    contentPane.setVisible(true);
	}

	private void fecharJanela() {
        setVisible(false);
        dispose();
    }
}
