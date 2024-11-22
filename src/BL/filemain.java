package BL;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.tartarus.snowball.ext.arabicStemmer;

import com.qcri.farasa.segmenter.Farasa;

import DAL.sqlinterface;
import DTO.File;
import DTO.Page;
import net.oujda_nlp_team.AlKhalil2Analyzer;
import net.oujda_nlp_team.entity.Result;
public class filemain implements  filemaininterface{
	sqlinterface o ;
	Farasa farasaSegmenter;
	public filemain(sqlinterface o){
		this.o=o;
		try {
			farasaSegmenter=new Farasa();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
public List<Page>searchwords(String name) 
{List<Page>files =new ArrayList<>();
	files=o.searchWordfromFiles(name);
	return files;
}
@Override
public List<String> segmentContent(String content) {
    try {
        String cleanedContent = content.replaceAll("[^\\p{IsArabic}\\s]", "").trim();

        List<String> segmentedWords = farasaSegmenter.segmentLine(cleanedContent);

        return segmentedWords.stream()
                             .filter(word -> word != null && !word.trim().isEmpty())
                             .collect(Collectors.toList());
    } catch (Exception e) {
        throw new RuntimeException("Segmentation error occurred", e);
    }
}

@Override
public List<String[]> analyzeWordsWithVerb(List<String> words) {
    List<String[]> wordAnalysisData = new ArrayList<>();
    AlKhalil2Analyzer analyzer = AlKhalil2Analyzer.getInstance();

    for (String word : words) {
        try {
            List<Result> analysisResults = analyzer.processToken(word).getAllResults();
            String partOfSpeechTags = analysisResults.isEmpty() ? "No data" 
                                        : String.join(", ", analysisResults.get(0).getPartOfSpeech().split("\\|"));

            wordAnalysisData.add(new String[]{word, partOfSpeechTags});
        } catch (Exception e) {
            wordAnalysisData.add(new String[]{word, "Error during analysis"});
        }
    }

    List<String[]> finalAnalysisData = new ArrayList<>();
    for (String[] analysis : wordAnalysisData) {
        String analyzedWord = analysis[0];
        String analyzedVerb = analysis[1];
        finalAnalysisData.add(new String[]{analyzedWord, analyzedVerb});
    }

    return finalAnalysisData;
}
public List<String[]> lemmatizeWords(List<String> words) {
	  List<String[]> lemmatizedWords = new ArrayList<>();
    AlKhalil2Analyzer analyzer = AlKhalil2Analyzer.getInstance();
    for (String word : words) {
        try {
            List<Result> results = analyzer.processToken(word).getAllResults();
            String lemma = results.isEmpty() ? "Invalid word" : results.get(0).getLemma();
            lemmatizedWords.add(new String[]{word, lemma});
        } catch (Exception e) {
            lemmatizedWords.add(new String[]{word, "Error"});
        }
    }
    return lemmatizedWords;

}
public List<String[]> stemming(List<String> words) {
  if (words == null || words.isEmpty()) {
      throw new IllegalArgumentException("The input list of words cannot be null or empty.");
  }
  arabicStemmer stemmer = new arabicStemmer();
  List<String[]> stemmedWords = new ArrayList<>();
  for (String word : words) {
      if (word != null && !word.trim().isEmpty()) {
          stemmer.setCurrent(word); 
          String stemmedWord = stemmer.stem() ? stemmer.getCurrent() : word; 
          stemmedWords.add(new String[]{word, stemmedWord}); 
      }
  }

  return stemmedWords;
}


}