package lmx.chef.web.service.impl;

import lmx.chef.web.dbproxy.dao.FeastMapper;
import lmx.chef.web.dbproxy.entity.Feast;
import lmx.chef.web.service.FeastService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lmx on 16/4/22.
 * 宴会service层具体实现
 */
@Service("feastService")
public class FeastServiceImpl implements FeastService {

    @Resource
    private FeastMapper feastMapper;

    @Override
    public List<Feast> getFeastList(Map<String, Object> map){
        return feastMapper.getFeastList(map);
    }

    @Override
    public Long getTotalNum(Map<String, Object> map){
        return feastMapper.getTotalNum(map);
    }

    @Override
    public Feast getById(Long id){
        return feastMapper.getById(id);
    }

    @Override
    public int update(Feast feast){
        return feastMapper.update(feast);
    }
}
