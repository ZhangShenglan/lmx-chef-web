package lmx.chef.web.service;

import lmx.chef.web.dbproxy.entity.ChefLike;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 */
public interface ChefLikeService {
    int create(ChefLike chefLike);

    int update(ChefLike chefLike);

    ChefLike getById(Long id);

    List<ChefLike> getByUserId(Map<String, Object> map);

    int getNumByUserId(Map<String, Object> map);

    int getNumByChefId(Long chefId);

    ChefLike getByMap(Map<String, Object> map);
}
