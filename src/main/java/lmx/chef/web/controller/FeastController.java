package lmx.chef.web.controller;

import lmx.chef.web.common.Constants;
import lmx.chef.web.common.FoodCategoryEnum;
import lmx.chef.web.common.timeTypeEnum;
import lmx.chef.web.dbproxy.entity.Feast;
import lmx.chef.web.dbproxy.entity.PageBean;
import lmx.chef.web.service.FeastService;
import lmx.chef.web.util.ResponseUtil;
import lmx.chef.web.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lmx on 16/4/23.
 * 宴会控制层
 */
@Controller
@RequestMapping("/feast")
public class FeastController {

    @Resource
    private FeastService feastService;

    /**
     * 获取所有的宴会时间类型
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/time_type", method = RequestMethod.GET)
    public String findTimeType(HttpServletResponse response)throws Exception {
        JSONObject result = new JSONObject();
        Map<String, String> type = new HashMap<>();
        for (timeTypeEnum ds : timeTypeEnum.values()) {
            type.put("name", ds.getValue());
            type.put("code", ds.toString());
        }
        result.put("type", type);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 获得所有的宴会事物类型
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/food_category", method = RequestMethod.GET)
    public String findFoodCategory(HttpServletResponse response)throws Exception {
        JSONObject result = new JSONObject();
        Map<String, String> type = new HashMap<>();
        for (FoodCategoryEnum ds : FoodCategoryEnum.values()) {
            type.put("name", ds.getValue());
            type.put("code", ds.toString());
        }
        result.put("type", type);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }
    /**
     * 获取热门宴会列表
     * @return
     */
    @RequestMapping("/list")
    public String getHOtFeast(@RequestParam(value = "currPage", required = false) Integer currPage,
                              @RequestParam(value = "pageSize", required = false) Integer pageSize,
                              @RequestParam(value = "timeType", required = false) String timeType,
                              @RequestParam(value = "category", required = false) String category,
                              @RequestParam(value = "sort", required = false) String sort,
                              @RequestParam(value = "userId", required = false) Long userId,
                              HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (currPage!=null && currPage > 0 && pageSize != null && pageSize > 0) {
            PageBean pageBean = new PageBean(currPage,pageSize);
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        if(StringUtil.isNotEmpty(timeType)){
            map.put("timeType",timeType);
        }
        if(StringUtil.isNotEmpty(category)){
            map.put("category",category);
        }
        //排序关键字
        if ("price".equals(sort))
            map.put("sort", " ORDER BY price DESC");
        if("liked".equals(sort)){
            map.put("sort", " ORDER BY liked DESC");
        }


            List<Feast> feasts = feastService.getFeastList(map);

            Long total = feastService.getTotalNum(map);
            JSONObject result = new JSONObject();
            if(feasts.isEmpty()){
                result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
                result.put("message","没有对应结果");
                ResponseUtil.write(response, result);
                return null;
            }
            JSONArray jsonArray = JSONArray.fromObject(feasts);
            result.put("rows", jsonArray);
            result.put("total", total);
            result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
            result.put("message","success");
            ResponseUtil.write(response, result);
            return null;



    }

    /**
     * 根据ID获得宴会
     * @param feastId
     * @return
     */
    @RequestMapping("/get")
    public String get(Long feastId,HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(feastId == null || feastId < 1){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","宴会ID不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        Feast feast = feastService.getById(feastId);
        if(feast == null){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(feast);
        result.put("rows", jsonArray);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }
}
