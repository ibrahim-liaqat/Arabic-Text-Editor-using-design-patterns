package PL;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import BL.filemaininterface;
import DTO.File;

public class viewall {
    private filemaininterface obj;
    private JFrame frame;
    private String[][] data;
    private String[] col = { "Sr No", "FileName"};
    
    public viewall(filemaininterface obj) {
        this.obj = obj;
        frame = new JFrame("View All Files");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        List<String> files = obj.viewallfiles();
        int size = files.size();
        data = new String[size][3]; 

        
        int i = 0;
        for (String file : files) {
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = file;
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(data, col) {
          @Override
          public boolean isCellEditable(int row, int column) {
               return column == 2; 
          }      
      };
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                
                if (col == 0 || col == 1) {
                    Object v = table.getValueAt(row, 1); 
                    new open(obj, String.valueOf(v));
                    frame.dispose();
                }
            }
        });
        
        frame.setVisible(true);
    }
}
