package lmx.chef.web.dbproxy.dao;

import lmx.chef.web.dbproxy.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lmx on 16/5/5.
 */
@Repository
public interface OrderMapper {

    List<Order> getByMap(Map<String, Object> map);

    int getNumByUserId(Map<String, Object> map);

    int create(Order order);
}
