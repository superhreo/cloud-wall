package cn.cw.dao;

import cn.cw.entity.User;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author _Cps
 * @create 2019-02-14 10:25
 */
public interface UserMapper {

    Integer userRegister(JSONObject user);

    JSONObject userLogin(JSONObject user);

    List<JSONObject> getUserList(JSONObject object);

    Integer userNameCheckIsRegister(JSONObject user);
}
