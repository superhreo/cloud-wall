package cn.cw.service.impl;

import cn.cw.dao.UserMapper;
import cn.cw.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author _Cps
 * @create 2019-02-14 10:25
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;

    @Override
    public Integer userRegister(JSONObject user) {
        return userMapper.userRegister(user);
    }

    @Override
    public JSONObject userLogin(JSONObject user) {
        return userMapper.userLogin(user);
    }

    @Override
    public List<JSONObject> getUserList(JSONObject object) {
        return userMapper.getUserList(object);
    }

    @Override
    public Integer userNameCheckIsRegister(JSONObject user) {
        return userMapper.userNameCheckIsRegister(user);
    }
}
