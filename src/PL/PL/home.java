package PL;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import BL.filemain;
import BL.filemaininterface;
import DAL.sql;
import DAL.sqlinterface;
public class home {
    private JButton create;
    private JButton open;
    private JButton importfile;
    private JButton openbypages;
    private JButton viewall;
    private JButton Search;
    private JTextField textarea;
    private filemain o;
    private JFrame frame;
    filemaininterface obj;
    public home(filemaininterface obj) {
    	importfile=new JButton("Import File");
    	this.obj=obj;
    	
    	viewall =new JButton("View all files");
        create = new JButton("Create a new file");
        open=new JButton("Open existing File");
        openbypages=new JButton("Open By Pages");
        Search=new JButton("Search");
        textarea=new JTextField("                          ");

        frame = new JFrame();
        frame.setTitle("Home Page");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(new FlowLayout());  
        frame.add(create); 
        frame.add(open);
        frame.add(viewall);
        frame.add(importfile);
        frame.add(openbypages);
        frame.add(textarea);
        frame.add(Search);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new create(obj);
				frame.dispose();
			}
        });
        open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		        String fileName = JOptionPane.showInputDialog(frame, "Enter file name:", "Open a File", JOptionPane.PLAIN_MESSAGE);
                new open(obj,fileName);
                frame.dispose();
				// TODO Auto-generated method stub
				//o.op
				//frame.dispose();
			}
        });
        viewall.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new viewall(obj);
				 frame.dispose();
			}
        	
        });
        importfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new importing(obj);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 frame.dispose();
			}
        });
        
        openbypages.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 String fileName = JOptionPane.showInputDialog(frame, "Enter file name:", "Done", JOptionPane.PLAIN_MESSAGE);
				new navigatepages(obj,fileName);
			}
        });
        
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Your search action code goes here
            	String word = textarea.getText();
				new searchResults(obj,word);
            }
        });
        
    }
   
}
