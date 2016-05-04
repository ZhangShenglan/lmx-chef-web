package lmx.chef.web.dbproxy.entity;

import java.io.Serializable;

/**
 * Created by lmx on 16/5/5.
 */
public class Order implements Serializable {
    private Long orderId;
    private Long feastId;
    private Long userId;
    private Integer state; //订单状态 1待支付 2等待开始 3交易完成 4交易关闭
    private Double unitPrice;
    private Integer number;

    private String feastTitle;
    private String images;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getFeastId() {
        return feastId;
    }

    public void setFeastId(Long feastId) {
        this.feastId = feastId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFeastTitle() {
        return feastTitle;
    }

    public void setFeastTitle(String feastTitle) {
        this.feastTitle = feastTitle;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
