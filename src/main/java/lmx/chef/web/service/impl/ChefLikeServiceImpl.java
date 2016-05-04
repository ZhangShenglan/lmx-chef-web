package lmx.chef.web.service.impl;

import lmx.chef.web.dbproxy.dao.ChefLikeMapper;
import lmx.chef.web.dbproxy.entity.ChefLike;
import lmx.chef.web.service.ChefLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 */
@Service("chefLikeService")
public class ChefLikeServiceImpl implements ChefLikeService{
    @Resource
    private ChefLikeMapper chefLikeMapper;

    @Override
    public int create(ChefLike chefLike){
        return chefLikeMapper.create(chefLike);
    }

    @Override
    public int update(ChefLike chefLike){
        return chefLikeMapper.update(chefLike);
    }

    @Override
    public ChefLike getById(Long id){
        return chefLikeMapper.getById(id);
    }

    @Override
    public List<ChefLike> getByUserId(Map<String, Object> map){
        return chefLikeMapper.getByUserId(map);
    }

    @Override
    public int getNumByUserId(Map<String, Object> map){
        return chefLikeMapper.getNumByUserId(map);
    }

    @Override
    public int getNumByChefId(Long chefId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("chefId",chefId);
        return chefLikeMapper.getNumByUserId(map);
    }

    @Override
    public ChefLike getByMap(Map<String, Object> map){
        return chefLikeMapper.getByMap(map);
    }
}
