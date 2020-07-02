package com.smit.cegepe_learning;

public class PostVideos {
    String categoryName;
    String categoryTeachers;
    String categoryImageLink;

    public PostVideos() {
    }

    @Override
    public String toString() {
        return "PostVideos{" +
                "categoryName='" + categoryName + '\'' +
                ", categoryTeachers='" + categoryTeachers + '\'' +
                ", categoryImageLink='" + categoryImageLink + '\'' +
                '}';
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryTeachers() {
        return categoryTeachers;
    }

    public void setCategoryTeachers(String categoryTeachers) {
        this.categoryTeachers = categoryTeachers;
    }

    public String getCategoryImageLink() {
        return categoryImageLink;
    }

    public void setCategoryImageLink(String categoryImageLink) {
        this.categoryImageLink = categoryImageLink;
    }
}
