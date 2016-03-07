package com.fun.sb.demo.lucene;

public class Item {
    
    private String id;
    private String title;
    private String content;
    
    public Item() {
    }
    
    public Item(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[id=").append(id).append(",title=").append(title)
            .append(",content=").append(content).append("]");
        return sb.toString();
    }
}