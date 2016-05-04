package lmx.chef.web.service.impl;

import lmx.chef.web.dbproxy.dao.UserMapper;
import lmx.chef.web.dbproxy.entity.UserBean;
import lmx.chef.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserBean> getUserList(Map<String, Object> map){
        return userMapper.getUserList(map);
    }

    @Override
    public int createUser(UserBean userBean){
        return userMapper.createUser(userBean);
    }

    @Override
    public UserBean getUserByPhone(String phone){
        return userMapper.getUserByPhone(phone);
    }

}
