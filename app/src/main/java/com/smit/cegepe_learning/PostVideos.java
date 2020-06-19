package com.smit.cegepe_learning;

public class PostVideos {
    String categoryName;

    public PostVideos() {
    }

    @Override
    public String toString() {
        return "PostVideos{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
