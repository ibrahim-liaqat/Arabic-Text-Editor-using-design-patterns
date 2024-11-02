package DAL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.File;
import DTO.Page;
public interface sqlinterface {
boolean savetodb(String a,String b) throws SQLException;
public List <Page> openfilefromdb(String name);
public List<File> viewallfilesdb();
public String txttostring(String name);
public boolean deleteFileFromDb(String fileName);
boolean saveinpages(String name,String page);
ArrayList<Page>navigatefromdb(String name);
List<Page> searchWordfromFiles(String word);
}