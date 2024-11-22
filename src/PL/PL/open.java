package PL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import BL.filemaininterface;

public class open {
    private JButton home;
    private JTextArea create;
    private JLabel openlabel;
    private JTextField openfield;
    private JFrame frame;
    private JButton edit;
    private JButton delete;
    private JButton transliterate;
    private JButton segmentation;
    private filemaininterface o;
    String name;
    open(filemaininterface o, String name) {
        this.o = o;
        this.name = name;

        frame = new JFrame();
        home = new JButton("Home");
        edit = new JButton("Edit a file");
        openlabel = new JLabel("File name");
        delete = new JButton("Delete");
        transliterate = new JButton("Transliterate");
        segmentation=new JButton("Segmentation");
        openfield = new JTextField();
        create = new JTextArea();
        
        // Text area settings
        create.setPreferredSize(new Dimension(900, 730)); 
        create.setLineWrap(true);
        create.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(create);
        scrollPane.setPreferredSize(new Dimension(900, 730));

        // Set frame properties
        frame.setTitle("Opened File");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top panel for text area
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for buttons and input fields
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(edit);
        buttonPanel.add(openfield);
        buttonPanel.add(home);
        buttonPanel.add(delete);
        buttonPanel.add(transliterate);
        buttonPanel.add(segmentation);


        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Configure text field and load initial data
        openfield.setColumns(8);
        openfield.setText(name);
        openfield.setEditable(false);
        create.setEditable(false);
        create.setText(o.openfile(name));
        
        // Show the frame
        frame.setVisible(true);

        // Button action listeners
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new create(o, create.getText());
                frame.dispose();
            }
        });

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new home(o);
                frame.dispose();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callingDeleteFunctionFromDeleteClass();
            }
        });
        segmentation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new segment(o,create.getText());
            }
        });

    }

    void callingDeleteFunctionFromDeleteClass() {
        new Delete(o, name);
    }

   
}
