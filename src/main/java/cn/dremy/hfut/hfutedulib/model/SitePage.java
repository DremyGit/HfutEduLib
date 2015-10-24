package cn.dremy.hfut.hfutedulib.model;

public class SitePage {
    
    private String id;
    
    private String note;
    
    private String url;
    
    private String method;
    
    public SitePage(String id, String note, String url, String method) {
        this.id = id;
        this.note = note;
        this.url = url;
        this.method = method.toUpperCase();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    
    

}
