package lmx.chef.web.controller;

import lmx.chef.web.common.Constants;
import lmx.chef.web.dbproxy.entity.FeastLike;
import lmx.chef.web.dbproxy.entity.UserBean;
import lmx.chef.web.service.FeastLikeService;
import lmx.chef.web.service.UserService;
import lmx.chef.web.util.DateUtil;
import lmx.chef.web.util.ResponseUtil;
import lmx.chef.web.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by zhangshenglan on 16/5/4.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private FeastLikeService feastLikeService;

    @RequestMapping("/bindPhone")
    public String bindPhone(@RequestParam(value = "phone", required = true) String phone,
                             HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(StringUtil.isEmpty(phone)){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","手机号码不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        if(userService.getUserByPhone(phone) != null && userService.getUserByPhone(phone).getUserId()!=null ){
            result.put("userName",userService.getUserByPhone(phone).getName());
            result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
            result.put("message","用户已经注册");
            ResponseUtil.write(response, result);
            return null;
        }
        String letter = "abcdefghijklmnopqrstuvwxyz";
        String num = "0123456789";
        Random random = new Random();
        StringBuilder lettetSb = new StringBuilder();
        StringBuilder numSb = new StringBuilder();

        lettetSb.append(letter.charAt(random.nextInt(letter.length())));

        for (int i = 0; i < 3; i++) {
            numSb.append(num.charAt(random.nextInt(num.length())));
        }

        UserBean userBean = new UserBean();
        userBean.setPhone(phone);
        userBean.setName("FEAST" + "_" + "user" + numSb + lettetSb);

        int flag = userService.createUser(userBean);

        if(flag > 0){
            result.put("userName",userBean.getName());
            result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
            result.put("message","success");
            ResponseUtil.write(response, result);
            return null;
        }
        result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
        result.put("message","手机号绑定失败");
        ResponseUtil.write(response, result);
        return null;
        }

    @RequestMapping("/likeFeast")
    public String likeFeast(@RequestParam(value = "userId", required = true) Long userId,
                            @RequestParam(value = "feastId", required = true) Long feastId,
                            HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(userId == null || userId < 0){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户ID不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        if(feastId == null || feastId < 0){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","饭局ID不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        map.put("feastId",feastId);
        if(feastLikeService.getByUserId(map) != null && feastLikeService.getByUserId(map).getId()!=null ){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户已经收藏过饭局");
            ResponseUtil.write(response, result);
            return null;
        }
        FeastLike feastLike = new FeastLike();
        feastLike.setUserId(userId);
        feastLike.setFeastId(feastId);
        feastLike.setCreateTime(DateUtil.getCurrentDate());
        int flag = feastLikeService.create(feastLike);
        if(flag > 0){
            result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
            result.put("message","success");
            ResponseUtil.write(response, result);
            return null;
        }
        result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
        result.put("message","饭局收藏失败");
        ResponseUtil.write(response, result);
        return null;
    }

}
