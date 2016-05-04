package lmx.chef.web.common;

/**
 * Created by zhangshenglan on 16/5/2.
 */
public enum  FoodCategoryEnum {
    CHINESE(1,"中国菜"),JAPANESE(2,"日本料理"),WESTERN(3,"西餐"),DESSERT(4,"甜品烘培");

    private Integer num;private String value;
    FoodCategoryEnum(Integer num,String value){
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
        for (FoodCategoryEnum e : FoodCategoryEnum.values()) {
            System.out.println(e + "," + e.getValue());
        }
    }

}
