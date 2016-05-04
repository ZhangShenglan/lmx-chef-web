package lmx.chef.web.dbproxy.dao;

import lmx.chef.web.dbproxy.entity.ChefLike;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 */
@Repository
public interface ChefLikeMapper {
    int create(ChefLike chefLike);

    int update(ChefLike chefLike);

    ChefLike getById(Long id);

    List<ChefLike> getByUserId(Map<String, Object> map);

    int getNumByUserId(Map<String, Object> map);

    ChefLike getByMap(Map<String, Object> map);
}
