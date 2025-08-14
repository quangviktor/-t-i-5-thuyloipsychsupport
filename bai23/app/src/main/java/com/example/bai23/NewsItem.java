package com.example.bai23;

public class NewsItem {
    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String image;

    // Constructor đã được cập nhật để nhận 5 tham số
    public NewsItem(String title, String description, String link, String pubDate, String image) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.image = image;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getImage() {
        return image;
    }

    // Bạn có thể thêm setters nếu cần, nhưng trong trường hợp này có thể không cần thiết.
}