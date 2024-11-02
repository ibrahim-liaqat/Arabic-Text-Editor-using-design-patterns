package DAL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.File;
import DTO.Page;
import DTO.File;
public class sql implements sqlinterface {
    public static final String URL = "jdbc:mysql://localhost:3306/ibrahim";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public boolean savetodb(String name, String content) throws SQLException {
    	 String check = "SELECT COUNT(*) FROM document WHERE FileName = ?";   
    	 String query = "INSERT INTO document (FileName, hashkey) VALUES (?, ?)";
        String update="UPDATE document SET DocumentText = ? WHERE FileName= ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(check)) {
        	stmt.setString(1, name);
        	ResultSet rs=stmt.executeQuery();
        	  rs.next();
              int count = rs.getInt(1);
              if (count > 0) {
                  PreparedStatement updatePstmt = conn.prepareStatement(update);
                  updatePstmt.setString(1,content);
                  updatePstmt.setString(2,name);
                  updatePstmt.executeUpdate();
              } else {  
                  PreparedStatement insertPstmt = conn.prepareStatement(query);
                  insertPstmt.setString(1, name);
                  insertPstmt.setString(2, content);
                  insertPstmt.executeUpdate();
              }
              return true;
          } catch (SQLException e) {
              e.printStackTrace();
              return false;
          }
    }
    public List <Page> openfilefromdb(String name){
    	 List<Page>files = new ArrayList<>();
    	String query="SELECT * FROM page WHERE FileName = ?";
    	int i;
    	String file,con;
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)) {
               stmt.setString(1, name);
               ResultSet rs = stmt.executeQuery();
               while(rs.next()) {
            	   i=rs.getInt("page_id");
            	   file=rs.getString("FileName");
            	   con=rs.getString("content");
            	   files.add(new Page(i,file,con));
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
    	return files;	
    }

    public List<File> viewallfilesdb(){
    	List<File>filenames = new ArrayList<>();
    	String query="SELECT * FROM document ";
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)) {
               ResultSet rs = stmt.executeQuery();
               while(rs.next()) {
         	   String fname=rs.getString("FileName");
            	   filenames.add(new File(fname));
               }
           } catch (SQLException e) {
               e.printStackTrace();
               
           }
    return filenames;
    }	
    
    public String txttostring(String name) {
        StringBuilder content = new StringBuilder();  
        String line;  
        try (BufferedReader reader = new BufferedReader(new FileReader(name))) {
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator()); 
            }
        } catch (IOException e) {
        	return "No file found";   
        }
        return content.toString(); 
    }
    public boolean deleteFileFromDb(String fileName) {
    	int r1 = 0,r2=0;
        String queryPage = "DELETE FROM page WHERE FileName = ?";
        String queryDocument = "DELETE FROM document WHERE FileName = ?";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            try (PreparedStatement stmtPage = conn.prepareStatement(queryPage)) {
                stmtPage.setString(1, fileName);
                r1=stmtPage.executeUpdate();
            }
            try (PreparedStatement stmtDocument = conn.prepareStatement(queryDocument)) {
                stmtDocument.setString(1, fileName);
                r2 = stmtDocument.executeUpdate();   
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } 
        if (r1>0 && r2>0) {
        	return true;
        }
        else {
        	return false;
        }
        
    }

   
    public boolean saveinpages(String name, String pages) {
        String insertQuery = "INSERT INTO Page (FileName, content) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            
            
            preparedStatement.setString(1, name);  
            preparedStatement.setString(2, pages);

            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; 

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return false; 
    }
	@Override
	public ArrayList<Page> navigatefromdb(String name) {
		// TODO Auto-generatedmethod stub
		ArrayList<Page> pageData = new ArrayList<>();
		String searchQuery = "SELECT * FROM page WHERE FileName = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                int pageId = rs.getInt("page_id");
                String documentId = rs.getString("FileName");
                String content = rs.getString("content");
                pageData.add(new Page(pageId, documentId, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pageData;
		
	}


}

