package com.qinziwanba.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Child {

    private String pid;
    private String name;
    private int age;
    private String gender;

    @JsonProperty("updated_at")
    private Long updatedAt;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public Child(String pid, String name, int age, String gender) {
        this.pid = pid;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.updatedAt = System.currentTimeMillis();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("child ");
        stringBuilder.append("name:").append(name).append(",");
        stringBuilder.append("age:").append(age).append(",");
        stringBuilder.append("gender:").append(gender).append(",");
        stringBuilder.append("updatedAt:").append(updatedAt);

        return stringBuilder.toString();
    }

}
