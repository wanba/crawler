package com.qinziwanba.crawler.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangzhiguo on 15/6/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeituanPage {
    private String name;
    private String address;
    private String tel;
    private String category;
    private String rating;

    @JsonProperty("consume_count")
    private String consumeCount;

    @JsonProperty("rating_count")
    private String ratingCount;

    @JsonProperty("updated_at")
    private Long updatedAt;

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

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

    public String getConsumeCount() {
        return consumeCount;
    }

    public void setConsumeCount(String consumeCount) {
        this.consumeCount = consumeCount;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }


    public MeituanPage() {
    }

    public MeituanPage(String name, String address, String tel, String category, String rating, String consumeCount, String ratingCount) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.category = category;
        this.rating = rating;
        this.consumeCount = consumeCount;
        this.ratingCount = ratingCount;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("meituanpage ");
        stringBuilder.append("name:").append(name).append(",");
        stringBuilder.append("address:").append(address).append(",");
        stringBuilder.append("tel:").append(tel).append(",");
        stringBuilder.append("category:").append(category).append(",");
        stringBuilder.append("rating:").append(rating).append(",");
        stringBuilder.append("consumeCount:").append(consumeCount).append(",");
        stringBuilder.append("ratingCount:").append(ratingCount);

        return stringBuilder.toString();
    }
}
