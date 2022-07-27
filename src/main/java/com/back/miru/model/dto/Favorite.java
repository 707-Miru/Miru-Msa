package com.back.miru.model.dto;

public class Favorite {
    private String id;
    private int followId;
    private int order;
    private String type;

    public Favorite(String id, int followId, int order, String type) {
        this.id = id;
        this.followId = followId;
        this.order = order;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
