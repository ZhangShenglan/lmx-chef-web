package lmx.chef.web.service.impl;

import lmx.chef.web.dbproxy.dao.FeastLikeMapper;
import lmx.chef.web.dbproxy.entity.FeastLike;
import lmx.chef.web.service.FeastLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 */
@Service("feastLikeService")
public class FeastLikeServiceImpl implements FeastLikeService {

    @Resource
    private FeastLikeMapper feastLikeMapper;

    @Override
    public int create(FeastLike feastLike){
        return feastLikeMapper.create(feastLike);
    }

    @Override
    public FeastLike getByUserId(Map<String, Object> map){
        return feastLikeMapper.getByUserId(map);
    }


}
