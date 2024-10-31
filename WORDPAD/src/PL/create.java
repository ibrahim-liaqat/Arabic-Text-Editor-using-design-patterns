package PL;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import BL.filemaininterface;
public class create {
private JTextArea create;
private JFrame frame;
private JButton saving;
private JButton home;
private filemaininterface o;
create(filemaininterface o) {
this.o = o;
frame = new JFrame();
home=new JButton("Home");
saving = new JButton("Save a file");
create = new JTextArea();
create.setLineWrap(true);
create.setWrapStyleWord(true);
create.setPreferredSize(new Dimension(900, 730));
JScrollPane scrollPane = new JScrollPane(create);
scrollPane.setPreferredSize(new Dimension(900, 730));
        frame.setTitle("Create");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.add(saving);
        frame.add(home);
        frame.setVisible(true);
        saving.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog(frame, "Enter file name:", "Save a File", JOptionPane.PLAIN_MESSAGE);
                try {
                    if (o.saveFile(fileName, create.getText())) {
                        JOptionPane.showMessageDialog(null, "File Created ", "Successfully", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "File not created, try again with a different name", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	       	new home(o);
			}
        });
    }
 create(filemaininterface o,String name) {
        this.o = o;
        frame = new JFrame();
        saving = new JButton("Save a file");
        create = new JTextArea();
        create.setLineWrap(true);
        create.setWrapStyleWord(true);
        create.setPreferredSize(new Dimension(900,730));
        JScrollPane scrollPane = new JScrollPane(create);
        scrollPane.setPreferredSize(new Dimension(900,730));
        create.setText(name);
        frame.setTitle("Create");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height); 
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.add(saving);
        frame.setVisible(true);
        saving.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog(frame, "Enter file name:", "Save a File", JOptionPane.PLAIN_MESSAGE);
                try {
                    if (o.saveFile(fileName, create.getText())) {
                        JOptionPane.showMessageDialog(null, "File Created ", "Successfully", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "File not created, try again with a different name", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });
        home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new home(o);
			}
        });
    }
    
}
