package com.qinziwanba.crawler.domain;

/**
 * Created by wangzhiguo on 15/6/28.
 * 动漫游戏联盟网站数据
 */
public class AmunionPage extends BasePage {
    // 面积
    private String square;

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public AmunionPage() {
    }

    public AmunionPage(String name, String address, String square) {
        this.name = name;
        this.address = address;
        this.square = square;
        this.updatedAt = System.currentTimeMillis();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("amunionPage ");
        stringBuilder.append(super.toString()).append(",");
        stringBuilder.append("square:").append(square);

        return stringBuilder.toString();
    }
}
