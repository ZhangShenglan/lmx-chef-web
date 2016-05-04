package lmx.chef.web.dbproxy.dao;

import lmx.chef.web.dbproxy.entity.UserBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 */
@Repository
public interface UserMapper {
    /**
     * 获取用户列表
     * @param map       map
     * @return
     */
    List<UserBean> getUserList(Map<String, Object> map);

    int createUser(UserBean userBean);

    UserBean getUserByPhone(String phone);

}
