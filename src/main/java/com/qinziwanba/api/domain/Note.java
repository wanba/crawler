package com.qinziwanba.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Note {

    private String nid;             // note id
    private String outline;         // 概述（外部）
    private String imageUrl;        // 配图

    private String title;           // 标题
    private String address;         // 地点
    private Long updatedAt;         // 更新时间

    private List<Section> sectionList;      // 段落

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void addSection(Section section) {
        sectionList.add(section);
    }

    public void addSections(List<Section> sections) {
        sectionList.addAll(sections);
    }

    public Note(String nid, String title, String address) {
        this.nid = nid;
        this.title = title;
        this.address = address;
        this.updatedAt = System.currentTimeMillis();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("note ");
        stringBuilder.append("nid:").append(nid).append(",");
        stringBuilder.append("title:").append(title).append(",");
        stringBuilder.append("address:").append(address).append(",");

        for (Section section : sectionList) {
            stringBuilder.append(section);
        }
        return stringBuilder.toString();
    }

    /**
     * 为了移动设备的可读性，限制每个section 一张图片+64个文字（3行以内）
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    static public class Section {
        private String sid;         // section id
        private String content;     // 格式化的内容（富文本）

        @JsonProperty("updated_at")
        private Long updatedAt;     // 更新时间

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Long getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Long updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Section() {}

        public Section(String sid, String content) {
            this.sid = sid;
            this.content = content;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("section ");
            stringBuilder.append("sid:").append(sid).append(",");
            stringBuilder.append("content:").append(content);

            return stringBuilder.toString();
        }
    }
}
