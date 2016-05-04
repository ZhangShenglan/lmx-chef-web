package lmx.chef.web.service;

import lmx.chef.web.dbproxy.entity.FeastLike;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 */
public interface FeastLikeService {

    int create(FeastLike feastLike);

    int update(FeastLike feastLike);

    FeastLike getById(Long id);

    List<FeastLike> getByUserId(Map<String, Object> map);

    int getNumByUserId(Map<String, Object> map);

    FeastLike getByUserMap(Map<String, Object> map);

}
