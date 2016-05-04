package lmx.chef.web.dbproxy.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by lmx on 16/5/5.
 */
@Repository
public interface JoinFeastMapper {
    /**
     * 私厨近期饭局数量
     * @param map
     * @return
     */
    int getNumByChef(Map<String, Object> map);
}
