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
    public Integer visit(String ip, String address,String device) {
        return userMapper.visit(ip,address,device);
    }

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

    @Override
    public Integer userPrize(JSONObject prize) {
        return userMapper.userPrize(prize);
    }

    @Override
    public Integer anaPrizeUpdate(JSONObject prize) {
        return userMapper.anaPrizeUpdate(prize);
    }

    @Override
    public Integer userPrizeList(List<JSONObject> prizeList) {
        return userMapper.userPrizeList(prizeList);
    }

    @Override
    public Integer updateHeadImg(Integer userId, String userHeadImg) {
        return userMapper.updateHeadImg(userId,userHeadImg);
    }
}
