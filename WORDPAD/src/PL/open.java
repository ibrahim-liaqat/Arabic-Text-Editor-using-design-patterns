package PL;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import BL.filemaininterface;
import javax.swing.*;
public class open {
	private JButton home;
    private JTextArea create;
    private JLabel openlabel;
    private JTextField openfield;
    private JFrame frame;
    private JButton edit;
    private filemaininterface o;
    open(filemaininterface o ,String name) {
        this.o=o;
        frame = new JFrame();
        home=new JButton("Home");
        edit = new JButton("Edit a file");
        openlabel=new JLabel("File name");
        openfield=new JTextField();
        create = new JTextArea();
        create.setPreferredSize(new Dimension(900,730)); 
        create.setLineWrap(true); 
        create.setWrapStyleWord(true); 
        JScrollPane scrollPane = new JScrollPane(create);
        scrollPane.setPreferredSize(new Dimension(900, 730)); 
        frame.setTitle("Opened File");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        openfield.setColumns(10);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane); 
        frame.add(edit);
        frame.add(openlabel);
        frame.add(openfield);
        frame.add(home);
       o.openfile(name);
        openfield.setText(name);
        create.setText( o.openfile(name));
        openfield.setEditable(false);
        create.setEditable(false);
        frame.setVisible(true);
        edit.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new create(o,create.getText());
				frame.dispose();
			}
        });
        home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new home(o);
				frame.dispose();
			}
        	
        });
        
    }
}