package com.smit.cegepe_learning;

public class PostVideoFeed {
    String category;
    String description;
    String id;
    String link;
    String title;
    String user;

    public PostVideoFeed() {
    }

    @Override
    public String toString() {
        return "PostVideoFeed{" +
                "category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
