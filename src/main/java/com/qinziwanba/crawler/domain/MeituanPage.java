package com.qinziwanba.crawler.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangzhiguo on 15/6/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeituanPage extends BasePage {


    @JsonProperty("consume_count")
    private String consumeCount;

    @JsonProperty("rating_count")
    private String ratingCount;

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }


    public String getConsumeCount() {
        return consumeCount;
    }

    public void setConsumeCount(String consumeCount) {
        this.consumeCount = consumeCount;
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
        StringBuilder stringBuilder = new StringBuilder("meituanPage ");
        stringBuilder.append(super.toString()).append(",");
        stringBuilder.append("consumeCount:").append(consumeCount).append(",");
        stringBuilder.append("ratingCount:").append(ratingCount);

        return stringBuilder.toString();
    }
}
