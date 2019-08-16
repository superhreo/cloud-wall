package cn.cw.service;

import cn.cw.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author _Cps
 * @create 2019-02-14 10:25
 */
public interface UserService{

    Integer addUser(JSONObject user);

    JSONObject userLogin(JSONObject user);

    List<JSONObject> getUserList(JSONObject object);
}
