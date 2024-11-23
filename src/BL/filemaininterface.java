package BL;
import DTO.File;
import DTO.Page;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface filemaininterface {
boolean saveFile(String a,String b) throws SQLException;
  String openfile(String name);
List<String> viewallfiles();
String txttostring(String name);
boolean deleteFile(String fileName);
String generatehashkey(String filename);
boolean saveinpages(String filename,String content);
ArrayList<String>navigatepages(String name);
List<Page>searchwords(String name);
public List<String> segmentContent(String content);
public List<String[]> analyzeWordsWithVerb(List<String>words);
public List<String[]> lemmatizeWords(List<String> words);
public List<String[]> stemming(List<String> words);
public List<String> tokenizeAndPreprocess(String document);
public List<String[]> calculateTFIDF(Map<String, Double> tfScores, Map<String, Double> idfScores);
public Map<String, Double> calculateIDF(List<String> documents, List<String> selectedWords);
public Map<String, Double> calculateTF(List<String> documentWords, List<String> selectedWords);
}