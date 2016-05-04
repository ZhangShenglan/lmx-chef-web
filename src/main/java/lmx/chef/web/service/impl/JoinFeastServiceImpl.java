package lmx.chef.web.service.impl;

import lmx.chef.web.dbproxy.dao.JoinFeastMapper;
import lmx.chef.web.service.JoinFeastService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/5.
 */
@Service("joinFeastService")
public class JoinFeastServiceImpl implements JoinFeastService {

    @Resource
    private JoinFeastMapper joinFeastMapper;
    @Override
    public int getNumByChef(Map<String, Object> map){
        return joinFeastMapper.getNumByChef(map);
    }
}
