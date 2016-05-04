package lmx.chef.web.service;

import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/5.
 */
public interface JoinFeastService {
    /**
     * 私厨近期饭局数量
     * @param map
     * @return
     */
    int getNumByChef(Map<String, Object> map);
}
