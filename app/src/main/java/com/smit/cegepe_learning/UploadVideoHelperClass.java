package com.smit.cegepe_learning;

public class UploadVideoHelperClass {
    String title;
    String description;
    String category;
    String link;
    String user;
    String approvalStatus;
    String id;

    UploadVideoHelperClass() {
    }

    public UploadVideoHelperClass(String title, String description, String category, String link, String user, String approvalStatus, String id) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.link = link;
        this.user = user;
        this.approvalStatus = approvalStatus;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
