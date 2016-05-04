package lmx.chef.web.service.impl;

import lmx.chef.web.dbproxy.dao.OrderMapper;
import lmx.chef.web.dbproxy.entity.Order;
import lmx.chef.web.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/5.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> getByMap(Map<String, Object> map){
        return orderMapper.getByMap(map);
    }

    @Override
    public int getNumByUserId(Map<String, Object> map){
        return orderMapper.getNumByUserId(map);
    }

}
