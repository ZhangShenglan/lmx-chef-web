package lmx.chef.web.common;

/**
 * Created by zhangshenglan on 16/5/2.
 * 宴会时间类型枚举
 */
public enum  timeTypeEnum {
    BRUNCH(1,"午餐"),AFTER_LUNCH(2,"下午茶"),DINNER(3,"晚餐");

    private Integer num;private String value;
    timeTypeEnum(Integer num,String value){
        this.num = num;
        this.value = value;
    }
    public Integer getNum(){
        return num;
    }
    public String getValue(){
        return value;
    }

    public static void main(String[] args){
        for (timeTypeEnum e : timeTypeEnum.values()) {
            System.out.println(e + "," + e.getValue());
        }
    }

}
