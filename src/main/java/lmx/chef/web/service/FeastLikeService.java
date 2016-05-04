package lmx.chef.web.service;

import lmx.chef.web.dbproxy.entity.FeastLike;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 */
@Repository
public interface FeastLikeService {

    int create(FeastLike feastLike);

    FeastLike getByUserId(Map<String, Object> map);

}
