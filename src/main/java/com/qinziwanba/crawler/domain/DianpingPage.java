package com.qinziwanba.crawler.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangzhiguo on 15/6/28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DianpingPage extends BasePage {

    @JsonProperty(value = "rating_count")
    private String ratingCount;

    @JsonProperty(value = "avg_price")
    private String avgPrice;

    @JsonProperty(value = "opening_time")
    private String openingTime;

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public DianpingPage() {
    }

    public DianpingPage(String name, String address, String tel, String category, String rating, String ratingCount, String avgPrice, String openingTime) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.category = category;
        this.rating = rating;
        this.updatedAt = System.currentTimeMillis();
        this.ratingCount = ratingCount;
        this.avgPrice = avgPrice;
        this.openingTime = openingTime;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("dianpingPage ");
        stringBuilder.append(super.toString()).append(",");

        stringBuilder.append("avgPrice:").append(avgPrice).append(",");
        stringBuilder.append("openingTime:").append(openingTime).append(",");
        stringBuilder.append("ratingCount:").append(ratingCount);

        return stringBuilder.toString();
    }
}
