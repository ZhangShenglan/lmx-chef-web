package lmx.chef.web.dbproxy.entity;

import java.io.Serializable;

/**
 * Created by zhangshenglan on 16/4/22.
 * 宴会对象
 */
public class Feast implements Serializable {
    private Long feastId;       //id
    private String title;       //标题
    private String beginTime;   //开始时间
    private String region;      //区域
    private String place;       //地点
    private Double price;       //价格
    private int    liked;       //被喜欢个数
    private String img;         //主图
    private int applied;        //已报名数
    private int content;        //总容量
    private Long chefId;        //私厨id
    private String menu;        //菜单
    private String menuPreview; //预览图片
    private int timeType;       //时间类别 0 全部 1 brunch 2 午餐 3 下午茶 4 晚餐
    private int category;       //菜系 0全部 1中国菜 2日料 3西餐 4甜品烘焙

    private int flag;           //用户是否收藏

    public Long getFeastId() {
        return feastId;
    }

    public void setFeastId(Long feastId) {
        this.feastId = feastId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getApplied() {
        return applied;
    }

    public void setApplied(int applied) {
        this.applied = applied;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public Long getChefId() {
        return chefId;
    }

    public void setChefId(Long chefId) {
        this.chefId = chefId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getMenuPreview() {
        return menuPreview;
    }

    public void setMenuPreview(String menuPreview) {
        this.menuPreview = menuPreview;
    }

    public int getTimeType() {
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
