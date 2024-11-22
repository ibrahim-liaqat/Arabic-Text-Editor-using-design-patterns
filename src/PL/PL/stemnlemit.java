package PL;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import BL.filemaininterface;

public class stemnlemit {
private JFrame frame ;
private filemaininterface obj;
private String[][] data;
private String[] col = { "Word", "Stemmed "};
stemnlemit(filemaininterface o,String text){
	frame=new JFrame();
	obj=o;
	List<String> words=o.segmentContent(text);
	 List<String[]> setemmer=o.stemming(words);
	 String[][] data = new String[setemmer.size()][2];
     for (int i = 0; i <setemmer .size(); i++) {
         data[i][0] = setemmer.get(i)[0];
         data[i][1] = setemmer.get(i)[1]; 
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