package com.smit.cegepe_learning;

public class UserHelperClass {
    String name;
    String dob;
    String city;
    String email;
    String password;
    String usertype;

    UserHelperClass() {
    }

    public UserHelperClass(String name, String dob, String city, String email, String password, String usertype) {
        this.name = name;
        this.dob = dob;
        this.city = city;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
