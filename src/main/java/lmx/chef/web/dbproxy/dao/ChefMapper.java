package lmx.chef.web.dbproxy.dao;

import lmx.chef.web.dbproxy.entity.Chef;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lmx on 16/4/23.
 * 私厨相关
 */
@Repository
public interface ChefMapper {
    /**
     * 根据相应的条件返回宴会列表
     * @param map
     * @return
     */
    List<Chef> getChefList(Map<String, Object> map);

    /**
     * 获得总数
     * @param map
     * @return
     */
    Long getTotalNum(Map<String, Object> map);

    /**
     * 根据ID获得私厨
     * @param chefId
     * @return
     */
    Chef getById(Long chefId);

    int update(Chef chef);

}
