package cn.cw.web;

import cn.cw.core.Result;
import cn.cw.core.ResultGenerator;
import cn.cw.core.ResultPages;
import cn.cw.service.AnaService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Cps
 * Date: 2019.08.02 13:12
 */
@Controller
@ResponseBody
@RequestMapping("/ana")
public class AnaController {

    @Resource
    AnaService anaService;

    /**
     * 添加语录
     * 参数 ana
     */
    @PostMapping("/addAna")
    Result addAna(@RequestBody JSONObject ana){
        Result result = null;
        Integer num = anaService.addAna(ana);
        if(num == 1){
            result = ResultGenerator.genSuccessResult("发表成功!");
        }else{
            result = ResultGenerator.genFailResult("发表失败!");
        }
        return result;
    }

    /**
     * 根据语录ID 获取语录详情信息
     * 参数:anaId
     */
    @PostMapping("/getAnaInfo")
    Result getAnaInfo(@RequestBody JSONObject object){
        return ResultGenerator.genSuccessResult(anaService.getAnaInfo(object));
    }

    /**
     * 根据语录ID 获取上条语录
     * 参数:anaId
     */
    @PostMapping("/getAnaUp")
    Result getAnaUp(@RequestBody JSONObject object){
        return ResultGenerator.genSuccessResult(anaService.getAnaUp(object));
    }

    /**
     * 根据语录ID 获取下条语录
     * 参数:anaId
     */
    @PostMapping("/getAnaDown")
    Result getAnaDown(@RequestBody JSONObject object){
        return ResultGenerator.genSuccessResult(anaService.getAnaDown(object));
    }

    /**
     * 根据分类ID、搜索 获取语录
     * 参数:anaTypeId searchName pageNo pageSize
     */
    @PostMapping("/getAnaList")
    Result getAnaList(@RequestBody JSONObject object){
        Integer pageNo = object.getInteger("pageNo");
        Integer pageSize = object.getInteger("pageSize");
        if(pageNo == null || pageSize == null){
            pageNo = 1;
            pageSize = 10;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<JSONObject> list = anaService.getAnaList(object);
        PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list);
        System.err.println(JSONArray.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss"));
        System.err.println(ResultGenerator.genSuccessResult(new ResultPages<JSONObject>(pageInfo)));
        return ResultGenerator.genSuccessResult(new ResultPages<JSONObject>(pageInfo));
    }

}
