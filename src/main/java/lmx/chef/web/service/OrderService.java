package lmx.chef.web.service;

import lmx.chef.web.dbproxy.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/5.
 */
public interface OrderService {

    List<Order> getByMap(Map<String, Object> map);

    int getNumByUserId(Map<String, Object> map);

    int create(Order order);

}
