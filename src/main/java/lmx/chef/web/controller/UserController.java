package lmx.chef.web.controller;

import lmx.chef.web.common.Constants;
import lmx.chef.web.dbproxy.entity.UserBean;
import lmx.chef.web.service.UserService;
import lmx.chef.web.util.ResponseUtil;
import lmx.chef.web.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * Created by zhangshenglan on 16/5/4.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

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
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","手机号码已被占用");
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


}
