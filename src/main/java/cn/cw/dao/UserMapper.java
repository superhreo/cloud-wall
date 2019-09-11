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

    Integer visit(@Param("ip") String ip,
                  @Param("address") String address,
                  @Param("device") String device);

    Integer userRegister(JSONObject user);

    JSONObject userLogin(JSONObject user);

    List<JSONObject> getUserList(JSONObject object);

    Integer userNameCheckIsRegister(JSONObject user);

    Integer userPrize(JSONObject prize);

    Integer anaPrizeUpdate(JSONObject prize);

    Integer userPrizeList(List<JSONObject> prizeList);

    Integer updateHeadImg(@Param("userId") Integer userId,@Param("message") String message);

}
