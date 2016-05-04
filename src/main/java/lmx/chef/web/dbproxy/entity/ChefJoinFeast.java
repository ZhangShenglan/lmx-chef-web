package lmx.chef.web.dbproxy.entity;

import java.io.Serializable;

/**
 * Created by zhangshenglan on 16/5/4.
 */
public class ChefJoinFeast implements Serializable {
    private Long id;
    private Long chefId;
    private Long feastId;
    private String joinTime;
    private String createTime;
    private Integer isDelete;

    private Integer feastNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeastId() {
        return feastId;
    }

    public void setFeastId(Long feastId) {
        this.feastId = feastId;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public Long getChefId() {
        return chefId;
    }

    public void setChefId(Long chefId) {
        this.chefId = chefId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getFeastNum() {
        return feastNum;
    }

    public void setFeastNum(Integer feastNum) {
        this.feastNum = feastNum;
    }
}
