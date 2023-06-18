package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
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
        setFont(new Font("Arial", Font.PLAIN, 12));
        setTitle("Student Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        JButton students = new JButton("Estudantes");
        students.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel lblNewLabel = new JLabel("Student Manager");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(166)
        			.addComponent(students, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
        			.addGap(155))
        		.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        			.addGap(125)
        			.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
        			.addGap(115))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(students)
        			.addContainerGap(178, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
        students.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {             
                ViewStudentList VSL = new ViewStudentList();
                fecharJanela();
                VSL.setVisible(true);
            }
        });
    }

    private void fecharJanela() {
        setVisible(false);
        dispose();
    }

}