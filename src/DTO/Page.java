package DTO;

public class Page {
    private int pageId;
    private String documentId;
    private String searchResult;

    public Page(int pageId, String documentId, String searchResult) {
        this.pageId = pageId;
        this.documentId = documentId;
        this.searchResult = searchResult;
    }
    public Page(String eachfile) {
    	documentId=eachfile;
    }

    public int getPageId() {
        return pageId;
    }
    public String getDocumentId() {
        return documentId;
    }
    public String getSearchResult() {
        return searchResult;
    }

    
}