package com.qinziwanba.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String uid;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String gender;

    @JsonProperty("updated_at")
    private Long updatedAt;

    private List<Child> childList = new ArrayList<Child>();

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void addChild(Child child) {
        childList.add(child);
    }

    public void addChildList(List<Child> childList) {
        childList.addAll(childList);
    }

    public User() {}

    public User(String uid,String name,String phone, String gender) {
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.updatedAt = System.currentTimeMillis();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("user ");
        stringBuilder.append("uid:").append(uid).append(",");
        stringBuilder.append("name:").append(name).append(",");
        stringBuilder.append("email:").append(email).append(",");
        stringBuilder.append("phone:").append(phone).append(",");
        stringBuilder.append("gender:").append(gender).append(",");
        stringBuilder.append("updated_at").append(updatedAt);

        return stringBuilder.toString();
    }
}
