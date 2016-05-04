package lmx.chef.web.controller;

import lmx.chef.web.common.Constants;
import lmx.chef.web.dbproxy.entity.Order;
import lmx.chef.web.service.OrderService;
import lmx.chef.web.util.ResponseUtil;
import lmx.chef.web.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lmx on 16/5/5.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/createOrder")
    public String createOrder (@RequestParam(value = "userId", required = true) Long userId,
                               @RequestParam(value = "feastId", required = false) Long feastId,
                               @RequestParam(value = "number", required = false) Integer number,
                               @RequestParam(value = "unitPrice",required = false) String unitPrice,
                               @RequestParam(value = "phone",required = false) String phone,
                               @RequestParam(value = "remark",required = false) String remark,
                               HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(userId == null || userId < 1){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户id不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        Order order = new Order();
        order.setState(3);
        order.setUserId(userId);
        order.setFeastId(feastId != null ? feastId : 0L);
        order.setNumber(number != null ? number : 0);
        order.setUnitPrice(unitPrice != null ? new Double(unitPrice) : 0);
        order.setPhone(StringUtil.isNotEmpty(phone)? phone : "");
        order.setRemark(StringUtil.isNotEmpty(remark) ? remark: "" );
        int flag = orderService.create(order);

        if(flag > 0){
            result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
            result.put("message","success");
            ResponseUtil.write(response, result);
            return null;
        }

        result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
        result.put("message","订单创建失败");
        ResponseUtil.write(response, result);
        return null;
    }
}
