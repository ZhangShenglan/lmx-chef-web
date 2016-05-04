package lmx.chef.web.dbproxy.entity;

import java.io.Serializable;

/**
 * Created by lmx on 16/5/4.
 */
public class ChefLike implements Serializable {
    private Long id;
    private Long userId;
    private Long ChefId;
    private String createTime;
    private Integer isDelete;

    private Integer feastNum;
    private String ChefName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChefId() {
        return ChefId;
    }

    public void setChefId(Long chefId) {
        ChefId = chefId;
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

    public String getChefName() {
        return ChefName;
    }

    public void setChefName(String chefName) {
        ChefName = chefName;
    }
}
