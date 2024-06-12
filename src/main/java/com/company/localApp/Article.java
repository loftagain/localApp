package com.company.localApp;

public class Article {
    private String title;
    private String link;
    private String imageUrl;
    private String summary;

    public Article(String title, String link, String imageUrl, String summary) {
        this.title = title;
        this.link = link;
        this.imageUrl = imageUrl;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
