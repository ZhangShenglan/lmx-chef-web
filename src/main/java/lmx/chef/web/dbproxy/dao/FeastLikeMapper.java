package lmx.chef.web.dbproxy.dao;

import lmx.chef.web.dbproxy.entity.FeastLike;

import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 */

public interface FeastLikeMapper {


    int create(FeastLike feastLike);


    FeastLike getByUserId(Map<String, Object> map);


}
