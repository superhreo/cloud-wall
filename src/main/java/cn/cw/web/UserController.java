package cn.cw.web;

import cn.cw.core.Result;
import cn.cw.core.ResultGenerator;
import cn.cw.core.ResultPages;
import cn.cw.service.UserService;
import cn.cw.util.*;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author _Cps
 * @create 2019-02-14 10:24
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController{

    @Resource
    public UserService userService;

    /**
     * 访问记录
     * 参数 user
     */
    @RequestMapping("/visit")
    public Result visit(HttpServletRequest request){
        String ip = IpUtil.getIpAddr(request);
        String address = IpUtil.getRealAddressByIP(ip);
        String device = DeviceUtil.deviceInfo(request);
        Result result = null;
        Integer num = userService.visit(ip,address,device);
        if(num == 1){
            result = ResultGenerator.genSuccessResult(address);
        }else{
            result = ResultGenerator.genFailResult("访问失败!");
        }
        return result;
    }

    /**
     * 验证账号是否被注册
     * @param user
     * @return
     */
    @PostMapping("/userNameCheckIsRegister")
    Result userNameCheckIsRegister(@RequestBody JSONObject user){
        Result result = null;
        Integer count = userService.userNameCheckIsRegister(user);
        if(count == 0){
            result = ResultGenerator.genSuccessResult("账号可以使用!");
        }else{
            result = ResultGenerator.genFailResult("账号已被使用!");
        }
        return result;
    }

    /**
     * 添加用户
     * 参数 user
     */
    @PostMapping("/userRegister")
    public Result userRegister(@RequestBody JSONObject user){
        //加密密码
        user.put("userPassword",MD5Util.getMD5String(user.getString("userPassword")));
        Result result = null;
        Integer num = userService.userRegister(user);
        if(num == 1){
            result = ResultGenerator.genSuccessResult("注册成功!");
        }else{
            result = ResultGenerator.genFailResult("注册失败!");
        }
        return result;
    }

    /**
     * 用户登录
     * 参数 user
     */
    @PostMapping("/userLogin")
    public Result userLogin(@RequestBody JSONObject user, HttpSession session){
        user.put("userPassword",MD5Util.getMD5String(user.getString("userPassword")));
        Result result = null;
        JSONObject u = userService.userLogin(user);
        if(u != null){
            if(0 == u.getInteger("userDisabled")){
                session.setAttribute("userSession",u);//放入session中
                result = ResultGenerator.genSuccessResult(u);
            }else{
                result = ResultGenerator.genFailResult("您的账号已被限制登陆，请联系管理员!");
            }
        }else{
            result = ResultGenerator.genFailResult("账号或密码错误,请重新输入!");
        }
        return result;
    }

    /**
     * 查询用户信息
     * 参数: pageNo pageSize searchName userSex userDisabled
     * @return
     */
    @PostMapping("/getUserList")
    Result getUserList(@RequestBody JSONObject object){
        Result result = null;
        Integer pageNo = object.getInteger("pageNo");
        Integer pageSize = object.getInteger("pageSize");
        if(pageNo == null){
            pageNo = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<JSONObject> list = userService.getUserList(object);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(new ResultPages(pageInfo));
    }

    /**
     * 添加用户
     * 参数 user
     */
    @PostMapping("/userPrize")
    public Result userPrize(@RequestBody JSONObject prize){
        //加密密码
        Result result = null;
        Integer num = userService.userPrize(prize);
        if(num == 1){
            result = ResultGenerator.genSuccessResult("注册成功!");
        }else{
            result = ResultGenerator.genFailResult("注册失败!");
        }
        return result;
    }

//
//    @PostMapping("/doLoginTest")
//    public String doLoginTest(User user, HttpSession session) {
//        //在User中添加了个验证码字段
//        if(user.getCheckCode()!=null && !user.getCheckCode().equals("")){
//            String inputImageCode = user.getCheckCode();
//            String sessionImageCode = (String) session.getAttribute(CheckCodeUtil.CHECK_CODE);
//            session.removeAttribute(CheckCodeUtil.CHECK_CODE);//清除Session中的imageCode
//            if(inputImageCode.equalsIgnoreCase(sessionImageCode)){
//                User u = userService.doLogin(user);
//                if (u != null) {
//                    session.setAttribute("userSession",u);
//                    return "redirect:/user/userList";
//                }
//            }else{
//                System.err.println("验证码错误");
//            }
//        }
//        System.err.println("登陆失败");
//        return "login";
//    }
//
//    /**
//     * 以流的方式导出报表(支持多表头)，使用说明参考doc目录下的ExcelPlus导出报表.pdf
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/export")
//    public void export(HttpServletResponse response) throws Exception {
//
//        //需要的参数：ExcelMake对象、JSONOject对象、list数据
//
//        //sheet名
//        String sheetName = "用户信息";
//
//        //属性配置
//        String props = "['id', 'userName', 'genderName', 'createDate']";
//
//        //表头配置
//        String row1 = "[{width:'1',height:'3',name:'结算部门'},{width:'1',height:'3',name:'类型'},{width:'6',height:'1',name:'公司账户'}]";
//        String row2 = "[{width:'3',height:'1',name:'点心'},{width:'3',height:'1',name:'套餐'}]";
//        String row3 = "[{width:'1',height:'1',name:'刷卡次数'},{width:'1',height:'1',name:'单价'},{width:'1',height:'1',name:'总金额'},{width:'1',height:'1',name:'次数'},{width:'1',height:'1',name:'单价'},{width:'1',height:'1',name:'总金额'}]";
//
//        //封装成JSONObject对象
//        JSONObject data = new JSONObject(){};
//        data.put("1",row1);
//        data.put("2",row2);
//        data.put("3",row3);
//        data.put("props",props);
//        data.put("sheetName",sheetName);
//
//        //获取数据---为什么是JSONObject对象，看Mapper.xml就理解了
//        List<JSONObject> list = userService.getUserListJSONObject();
//
//        //excel文件名
//        String fileName = sheetName + System.currentTimeMillis() + ".xls";
//
//        //绘制单元格对象
//        ExcelMake make = new ExcelMake(sheetName);
//
//        //创建HSSFWorkbook
//        HSSFWorkbook wb = new ExcelUtil().getHSSFWorkbook(make, data , list);
//
//        //响应到客户端
//        try {
//            FileUtil.setResponseHeader(fileName,response);
//            OutputStream os = response.getOutputStream();
//            wb.write(os);
//            os.flush();
//            os.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    /**
//     * 生成前端的图片验证码
//     */
//    @RequestMapping(value = "/checkCode")
//    public void getCheckCode(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            FileUtil.setResponseHeader("验证码",response);
//            CheckCodeUtil randomValidateCode = new CheckCodeUtil();
//            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
//        } catch (Exception e) {
//            System.err.println("将内存中的图片通过流动形式输出到客户端失败>>>>");
//        }
//    }




}
