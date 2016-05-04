package lmx.chef.web.controller;

import lmx.chef.web.common.Constants;
import lmx.chef.web.dbproxy.entity.Chef;
import lmx.chef.web.dbproxy.entity.PageBean;
import lmx.chef.web.service.ChefService;
import lmx.chef.web.util.ResponseUtil;
import lmx.chef.web.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lmx on 16/4/23.
 * 私厨控制层
 */
@Controller
@RequestMapping("/chef")
public class ChefController {

    @Resource
    private ChefService chefService;

    /**
     * 获得热门私厨
     * @return
     */
    @RequestMapping("/list")
    public String getHotChef(@RequestParam(value = "currPage", required = false) Integer currPage,
                       @RequestParam(value = "pageSize", required = false) Integer pageSize,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "sort", required = false) String sort,
                       HttpServletResponse response) throws Exception{

        Map<String, Object> map = new HashMap<String, Object>();
        if (currPage!=null && currPage > 0 && pageSize != null && pageSize > 0) {
            PageBean pageBean = new PageBean(currPage,pageSize);
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        if(StringUtil.isNotEmpty(name)){
            map.put("name", name);
        }
        //排序关键字
        if ("price".equals(sort))
            map.put("sort", " ORDER BY price DESC");
        if("likedNum".equals(sort)){
            map.put("sort", " ORDER BY liked DESC");
        }
        List<Chef> chefs = chefService.getChefList(map);
        Long total = chefService.getTotalNum(map);
        JSONObject result = new JSONObject();
        if(chefs.isEmpty()){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(chefs);
        result.put("rows", jsonArray);
        result.put("total", total);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 根据ID获得私厨详情
     * @param chefId
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/get")
    public String get(Long chefId,HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(chefId == null || chefId < 1){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","私厨ID不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        Chef chef = chefService.getById(chefId);
        if(chef == null){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(chef);
        result.put("rows", jsonArray);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }


}
