package BL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ibm.icu.text.Transliterator;

import DAL.sqlinterface;
import DTO.File;
import DTO.Page;
public class filemain implements  filemaininterface{
	sqlinterface o ;
	public filemain(sqlinterface o){
		this.o=o;
	}
	public boolean saveFile(String name,String content) throws SQLException {
		if (o.savetodb(name,generatehashkey(name)) && saveinpages(name,content) ) 
		{
			
			return true;
		}
		else {
			return false;
		}
	}
@Override
public String openfile(String name) {
	String result="";
	List<Page> files=new ArrayList<>();
	files=o.openfilefromdb(name);
	if (files.isEmpty()) {
		return "No file found"+":"+ "File don,t exist";
	}
	else {
		for(Page f: files){
			result=result+f.getSearchResult();
		}
		return result;
	}
}
public List<String> viewallfiles() {
List<String> filestring=new ArrayList<>();
List<File>files =new ArrayList<>();
files=o.viewallfilesdb();
if (files.isEmpty()) {
	
	filestring.add("No files exist");
}
else {
	for (File f:files) {
		filestring.add(f.getname());
	}
}

return filestring;
}
public String txttostring(String name) {
	String re=o.txttostring(name);
	return re;
	
}
public boolean deleteFile(String fileName) {
    if (o.deleteFileFromDb(fileName)) {
       return true;
    } else {
       return false;
    }
	
}


public  String generatehashkey(String content) {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(content.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();

    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException("SHA-256 algorithm not found", e);
    }
}
@Override
public boolean saveinpages(String filename, String content) {
    StringBuilder line = new StringBuilder();
    boolean result;
    Scanner scanner = new Scanner(content);
    int lineCount = 0; 

    while (scanner.hasNextLine()) {
        line.append(scanner.nextLine()).append("\n"); 
        lineCount++; 
        
        if (lineCount == 5 || !scanner.hasNextLine()) {
            result = o.saveinpages(filename, line.toString().trim()); 
            if (!result) {
                scanner.close(); 
                return false; 
            }
            line.setLength(0); 
            lineCount = 0; 
            }
    }

    scanner.close(); 
    return true;
}

@Override
public ArrayList<String> navigatepages(String name) {
	// TODO Auto-generated method stub
	ArrayList<Page>pages=new ArrayList<>();
	pages=o.navigatefromdb(name);
	ArrayList<String> result = new ArrayList<>();
	
	if (pages.isEmpty()) {
		return null;
	}
	else {
		for(Page f: pages){
			result.add(f.getSearchResult());
		}
		
	}
	return result;
}


}
