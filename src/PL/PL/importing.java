package PL;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import BL.filemaininterface;
public class importing { 
	JFrame frame;
	private filemaininterface obj;
	public importing( filemaininterface obj) throws SQLException {
		this.obj=obj;
		frame=new JFrame();
		frame.setVisible(true);
		frame.setTitle("import");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(frame); 
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String content = obj.txttostring(selectedFile.getAbsolutePath()); 
            obj.saveFile(selectedFile.getName(), content);
        } else {
       
            JOptionPane.showMessageDialog(frame, "No file selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame.dispose(); 
		}
  
}
