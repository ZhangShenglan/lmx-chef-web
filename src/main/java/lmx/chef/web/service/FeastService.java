package lmx.chef.web.service;

import lmx.chef.web.dbproxy.entity.Feast;

import java.util.List;
import java.util.Map;

/**
 * Created by lmx on 16/4/22.
 * 宴会service层
 */
public interface FeastService {

    /**
     * 根据相应的条件返回宴会列表
     * @param map
     * @return
     */
    List<Feast> getFeastList(Map<String, Object> map);


    /**
     * 获得总数
     * @param map
     * @return
     */
    Long getTotalNum(Map<String, Object> map);

    /**
     * 根据ID获得宴会
     * @param id
     * @return
     */
    Feast getById(Long id);


}
