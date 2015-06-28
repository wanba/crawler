package com.qinziwanba.crawler.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangzhiguo on 15/6/28.
 */
public class BasePage {
    protected String name;
    protected String address;
    protected String tel;
    protected String category;
    protected String rating;

    @JsonProperty("updated_at")
    protected Long updatedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name:").append(name).append(",");
        stringBuilder.append("address:").append(address).append(",");
        stringBuilder.append("tel:").append(tel).append(",");
        stringBuilder.append("category:").append(category).append(",");
        stringBuilder.append("rating:").append(rating);
        return stringBuilder.toString();
    }
}
