package lmx.chef.web.dbproxy.entity;

import java.io.Serializable;

/**
 * Created by lmx on 16/4/23.
 * 私厨对象
 */
public class Chef implements Serializable {
    private Long chefId;
    private String name;
    private String awatar;
    private String region;
    private String place;
    private int likedNum;
    private String desc;            //自我介绍

    public Long getChefId() {
        return chefId;
    }

    public void setChefId(Long chefId) {
        this.chefId = chefId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAwatar() {
        return awatar;
    }

    public void setAwatar(String awatar) {
        this.awatar = awatar;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(int likedNum) {
        this.likedNum = likedNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
