package view.student;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import br.ufc.coop.trabalhobd.Entities.Course;
import br.ufc.coop.trabalhobd.Entities.Student;
import br.ufc.coop.trabalhobd.Managers.CourseManager;
import br.ufc.coop.trabalhobd.Managers.StudentManager;
import br.ufc.coop.trabalhobd.VOs.SchoolRecord;
import view.ViewHome;
import view.course.NewCourse;
import view.course.ViewCourse;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.ScrollPane;

public class ViewStudentGrades extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(Student student) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStudentGrades frame = new ViewStudentGrades(student);
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
	public ViewStudentGrades(Student student) {
		setResizable(false);
		setTitle("Histórico do aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		DefaultListModel<String> demoList = new DefaultListModel<String>();
		
		CourseManager courseManager = new CourseManager();
		StudentManager studentManager = new StudentManager();
		
        for(Course c : courseManager.getCourse()){
        	demoList.add(0, c.getName());
        }
		
		 MouseListener mouseListener = new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		        JList<String> theList = (JList) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) {
		            final Object o = theList.getModel().getElementAt(index);
		            List<Course> courses = courseManager.getCourse();
		            Course course = courses.stream().filter(c -> {return c.getName().equals(o.toString());}).findAny().orElse(null);
		            ViewCourse VC = new ViewCourse(course);
		            fecharJanela();
		            VC.setVisible(true);
		          }
		        }
		      }
		    };
		    contentPane.setSize(350, 200);
		    		    
		    JButton btnNewButton = new JButton("Voltar");
		    btnNewButton.setToolTipText("Voltar para o estudante");
		    btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		    btnNewButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		ViewStudent VS = new ViewStudent(student);
		    		fecharJanela();
					VS.setVisible(true);
		    	}
		    });
		    
		    JLabel lblNewLabel = new JLabel("Histórico");
		    lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		    
		    List<SchoolRecord> schoolRecords = studentManager.GetSchoolRecord(student.getRegistration());
		    DefaultTableModel tableModel = new DefaultTableModel() {;
				public boolean isCellEditable(int row, int column)
			    {
			      return false;
			    }
		    };
		    
		    tableModel.addColumn("Disciplina");
		    tableModel.addColumn("Período");
		    tableModel.addColumn("Nota");
		    tableModel.addColumn("Frequência");
		    
		    for (SchoolRecord sr : schoolRecords) {
		    	Object[] row = {
		    			sr.getCourseName(), sr.getPeriod(), sr.getGrade(), sr.getAttendance() + "%" 
		    	};
		    	tableModel.addRow(row);
		    }		    
		    		    
		    table = new JTable();
		    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		    table.setModel(tableModel);
		    table.setFont(new Font("Arial", Font.PLAIN, 12));
		    
		    JLabel lblDisciplina = new JLabel("Disciplina");
		    lblDisciplina.setHorizontalAlignment(SwingConstants.LEFT);
		    lblDisciplina.setFont(new Font("Arial", Font.PLAIN, 12));
		    
		    JLabel lblPeriodo = new JLabel("Período");
		    lblPeriodo.setHorizontalAlignment(SwingConstants.LEFT);
		    lblPeriodo.setFont(new Font("Arial", Font.PLAIN, 12));
		    
		    JLabel lblNota = new JLabel("Nota");
		    lblNota.setHorizontalAlignment(SwingConstants.LEFT);
		    lblNota.setFont(new Font("Arial", Font.PLAIN, 12));
		    
		    JLabel lblFrequncia = new JLabel("Frequência");
		    lblFrequncia.setHorizontalAlignment(SwingConstants.LEFT);
		    lblFrequncia.setFont(new Font("Arial", Font.PLAIN, 12));
		    
		    		    
		    GroupLayout gl_contentPane = new GroupLayout(contentPane);
		    gl_contentPane.setHorizontalGroup(
		    	gl_contentPane.createParallelGroup(Alignment.TRAILING)
		    		.addGroup(gl_contentPane.createSequentialGroup()
		    			.addContainerGap()
		    			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
		    				.addComponent(table, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
		    				.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
		    				.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
		    				.addGroup(gl_contentPane.createSequentialGroup()
		    					.addComponent(lblDisciplina, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
		    					.addPreferredGap(ComponentPlacement.RELATED)
		    					.addComponent(lblPeriodo, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
		    					.addPreferredGap(ComponentPlacement.RELATED)
		    					.addComponent(lblNota, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
		    					.addPreferredGap(ComponentPlacement.UNRELATED)
		    					.addComponent(lblFrequncia, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
		    			.addContainerGap())
		    );
		    gl_contentPane.setVerticalGroup(
		    	gl_contentPane.createParallelGroup(Alignment.TRAILING)
		    		.addGroup(gl_contentPane.createSequentialGroup()
		    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		    			.addComponent(lblNewLabel)
		    			.addPreferredGap(ComponentPlacement.UNRELATED)
		    			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
		    				.addComponent(lblDisciplina, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
		    				.addComponent(lblPeriodo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
		    				.addComponent(lblNota, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
		    				.addComponent(lblFrequncia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
		    			.addGap(1)
		    			.addComponent(table, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
		    			.addGap(18)
		    			.addComponent(btnNewButton)
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
