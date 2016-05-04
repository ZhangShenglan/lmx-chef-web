package lmx.chef.web.service.impl;

import lmx.chef.web.dbproxy.dao.FeastLikeMapper;
import lmx.chef.web.dbproxy.entity.FeastLike;
import lmx.chef.web.service.FeastLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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
    public int update(FeastLike feastLike){
        return feastLikeMapper.update(feastLike);
    }

    @Override
    public List<FeastLike> getByUserId(Map<String, Object> map){
        return feastLikeMapper.getByUserId(map);
    }

    @Override
    public int getNumByUserId(Map<String, Object> map){
        return feastLikeMapper.getNumByUserId(map);
    }

    @Override
    public FeastLike getById(Long id){
        return feastLikeMapper.getById(id);
    }
    @Override
    public FeastLike getByUserMap(Map<String, Object> map){
        return feastLikeMapper.getByUserMap(map);
    }


}
