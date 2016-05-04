package lmx.chef.web.dbproxy.dao;

import lmx.chef.web.dbproxy.entity.Feast;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lmx on 16/4/22.
 * 宴会dao层
 */
@Repository
public interface FeastMapper {
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

    int update(Feast feast);
    
}
