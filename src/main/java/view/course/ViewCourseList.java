package view.course;

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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import br.ufc.coop.trabalhobd.Entities.Course;
import br.ufc.coop.trabalhobd.Managers.CourseManager;
import view.ViewHome;

public class ViewCourseList extends JFrame {

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
					ViewCourseList frame = new ViewCourseList();
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
	public ViewCourseList() {
		setResizable(false);
		setTitle("Lista de disciplinas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		DefaultListModel<String> demoList = new DefaultListModel<String>();

		final CourseManager courseManager = new CourseManager();

		for(Course c : courseManager.getCourse()){
			demoList.add(0, c.getName());
		}

		JList<String> list = new JList<String>(demoList);
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setFont(new Font("Arial", Font.PLAIN, 12));
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				@SuppressWarnings("unchecked")
				JList<String> theList = (JList<String>) mouseEvent.getSource();
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
		btnNew.setToolTipText("Adicionar nova disciplina");
		btnNew.setText("Adicionar");
		btnNew.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewCourse NC = new NewCourse();
				fecharJanela();
				NC.setVisible(true);
			}
		});

		JLabel lblNewLabel = new JLabel("Disciplinas cadastradas");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

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
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(btnNew))
						.addGap(19))
				);
		contentPane.setLayout(gl_contentPane);
		contentPane.setVisible(true);
	}

	private void fecharJanela() {
		setVisible(false);
		dispose();
	}
}
