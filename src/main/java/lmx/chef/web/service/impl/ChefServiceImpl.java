package lmx.chef.web.service.impl;

import lmx.chef.web.dbproxy.dao.ChefMapper;
import lmx.chef.web.dbproxy.entity.Chef;
import lmx.chef.web.service.ChefService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/4/23.
 */
@Service("chefService")
public class ChefServiceImpl implements ChefService{

    @Resource
    private ChefMapper chefMapper;

    @Override
    public List<Chef> getChefList(Map<String, Object> map){
        return chefMapper.getChefList(map);
    }

    @Override
    public Long getTotalNum(Map<String, Object> map){
        return chefMapper.getTotalNum(map);
    }

    @Override
    public Chef getById(Long chefId){
        return chefMapper.getById(chefId);
    }

    @Override
    public int update(Chef chef){
        return chefMapper.update(chef);
    }
}
