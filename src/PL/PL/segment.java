package PL;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import BL.filemaininterface;

public class segment {
	private JFrame frame;
	 private String[][] data;
	    private String[] col = { "Word", "Type of verb"};
	private JTable table;
	List<String>wordseg;
	  List<String[]> finaldata;
	public segment(filemaininterface o, String text) {
		wordseg=o.segmentContent(text);
		finaldata=o.analyzeWordsWithVerb(wordseg);
		frame=new JFrame();
		table=new JTable();
	        String[][] data = new String[finaldata.size()][2];
	        for (int i = 0; i < finaldata.size(); i++) {
	            data[i][0] = finaldata.get(i)[0];
	            data[i][1] = finaldata.get(i)[1]; 
	        }
	        JTable table = new JTable(data, col);
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        frame.setSize(screenSize.width, screenSize.height);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        JScrollPane scrollPane = new JScrollPane(table);
	        frame.add(scrollPane);
	        frame.setVisible(true);
	}
	
}