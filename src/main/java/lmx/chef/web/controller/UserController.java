package lmx.chef.web.controller;

import lmx.chef.web.common.Constants;
import lmx.chef.web.dbproxy.dao.JoinFeastMapper;
import lmx.chef.web.dbproxy.entity.*;
import lmx.chef.web.service.*;
import lmx.chef.web.util.DateUtil;
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

    @Resource
    private ChefLikeService chefLikeService;

    @Resource
    private ChefService chefService;

    @Resource
    private JoinFeastService joinFeastService;

    @Resource
    private FeastService feastService;

    @Resource
    private OrderService orderService;

    @Resource
    private CommentService commentService;

    @RequestMapping("/bindPhone")
    public String bindPhone(@RequestParam(value = "phone", required = true) String phone,
                            @RequestParam(value = "avatar", required = true) String avatar,
                             HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(StringUtil.isEmpty(phone)){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","手机号码不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        if(StringUtil.isEmpty(avatar)){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","头像不能为空");
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
        userBean.setAvatar(avatar);

        int flag = userService.createUser(userBean);

        if(flag > 0){
            result.put("userId",userBean.getUserId());
            result.put("userName",userBean.getName());
            result.put("phone",userBean.getPhone());
            result.put("avatar",userBean.getAvatar());
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
        if(feastLikeService.getByUserMap(map) != null && feastLikeService.getByUserMap(map).getId()!=null ){
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
            Feast feast = feastService.getById(feastId);
            feast.setLiked(feast.getLiked()+1);
            feastService.update(feast);

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

    @RequestMapping("/unLikeFeast")
    public String unLikeFeast(@RequestParam(value = "id", required = true) Long id,
                              HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(id == null || id < 1){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","id不能为空");
            ResponseUtil.write(response, result);
            return null;
        }

        FeastLike feastLike = feastLikeService.getById(id);
        feastLike.setIsDelete(-1);
        int flag = feastLikeService.update(feastLike);
        if(flag > 0){
            Feast feast = feastService.getById(feastLike.getFeastId());
            feast.setLiked(feast.getLiked()+1);
            feastService.update(feast);
            result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
            result.put("message","success");
            ResponseUtil.write(response, result);
            return null;
        }

        result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
        result.put("message","饭局取消收藏失败");
        ResponseUtil.write(response, result);
        return null;
    }


    @RequestMapping("/likeChef")
    public String likeChef(@RequestParam(value = "userId", required = true) Long userId,
                            @RequestParam(value = "chefId", required = true) Long chefId,
                            HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(userId == null || userId < 0){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户ID不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        if(chefId == null || chefId < 0){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","私厨ID不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        map.put("chefId",chefId);
        if(chefLikeService.getByMap(map) != null && chefLikeService.getByMap(map).getId()!=null ){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户已经收藏过私厨");
            ResponseUtil.write(response, result);
            return null;
        }
        ChefLike chefLike = new ChefLike();
        chefLike.setUserId(userId);
        chefLike.setChefId(chefId);
        chefLike.setCreateTime(DateUtil.getCurrentDate());
        int flag = chefLikeService.create(chefLike);
        if(flag > 0){
            Chef chef = chefService.getById(chefId);
            chef.setLikedNum(chef.getLikedNum()+1);
            chefService.update(chef);
            result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
            result.put("message","success");
            ResponseUtil.write(response, result);
            return null;
        }
        result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
        result.put("message","私厨收藏失败");
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/unLikeChef")
    public String unLikeChef(@RequestParam(value = "id", required = true) Long id,
                              HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(id == null || id < 1){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","id不能为空");
            ResponseUtil.write(response, result);
            return null;
        }

        ChefLike chefLike = chefLikeService.getById(id);
        chefLike.setIsDelete(-1);
        int flag = chefLikeService.update(chefLike);
        if(flag > 0){
            Chef chef = chefService.getById(chefLike.getChefId());
            chef.setLikedNum(chef.getLikedNum()-1);
            chefService.update(chef);
            result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
            result.put("message","success");
            ResponseUtil.write(response, result);
            return null;
        }

        result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
        result.put("message","私厨取消收藏失败");
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/likeChefList")
    public String likeChefList(@RequestParam(value = "userId", required = true) Long userId,
                               @RequestParam(value = "currPage", required = false) Integer currPage,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize,
                               HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(userId == null || userId < 1){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户id不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (currPage!=null && currPage > 0 && pageSize != null && pageSize > 0) {
            PageBean pageBean = new PageBean(currPage,pageSize);
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        map.put("userId",userId);
        List<ChefLike> chefLikes = chefLikeService.getByUserId(map);
        if(chefLikes.isEmpty()){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        for(ChefLike chefLike : chefLikes){
            //添加私厨姓名
            String name = "";
            Chef chef = chefService.getById(chefLike.getChefId());
            if(chef != null && StringUtil.isNotEmpty(chef.getName())){
                name = chef.getName();
            }
            chefLike.setChefName(name);

            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("chefId",chefLike.getChefId());
            //添加近期饭局数量
            int feastNum = joinFeastService.getNumByChef(map1);
            chefLike.setFeastNum(feastNum);
        }
        int total = chefLikeService.getNumByUserId(map);
        JSONArray jsonArray = JSONArray.fromObject(chefLikes);
        result.put("rows", jsonArray);
        result.put("total", total);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/likeFeastList")
    public String likeFeastList(@RequestParam(value = "userId", required = true) Long userId,
                               @RequestParam(value = "currPage", required = false) Integer currPage,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize,
                               HttpServletResponse response) throws Exception{

        JSONObject result = new JSONObject();
        if(userId == null || userId < 1){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户id不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (currPage!=null && currPage > 0 && pageSize != null && pageSize > 0) {
            PageBean pageBean = new PageBean(currPage,pageSize);
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        map.put("userId",userId);
        List<FeastLike> feastLikes = feastLikeService.getByUserId(map);
        if(feastLikes.isEmpty()){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        for(FeastLike feastLike : feastLikes){
            Feast feast = feastService.getById(feastLike.getFeastId());
            if(feast != null){
                feastLike.setTitle(StringUtil.isNotEmpty(feast.getTitle()) ? feast.getTitle() : "" );
                feastLike.setBeginTime(StringUtil.isNotEmpty(feast.getBeginTime()) ? feast.getBeginTime() : "");
                feastLike.setRegion(StringUtil.isNotEmpty(feast.getRegion())? feast.getRegion() : "");
                feastLike.setPrice(feast.getPrice() != null ? feast.getPrice() : 0);
            }
        }
        int total = feastLikeService.getNumByUserId(map);
        JSONArray jsonArray = JSONArray.fromObject(feastLikes);
        result.put("rows", jsonArray);
        result.put("total", total);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/orderList")
    public String orderList(@RequestParam(value = "userId", required = true) Long userId,
                                @RequestParam(value = "currPage", required = false) Integer currPage,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(userId == null || userId < 1){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户id不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (currPage!=null && currPage > 0 && pageSize != null && pageSize > 0) {
            PageBean pageBean = new PageBean(currPage,pageSize);
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        map.put("userId",userId);
        List<Order> orders = orderService.getByMap(map);
        if(orders.isEmpty()){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        for(Order order : orders){
            Feast feast = feastService.getById(order.getFeastId());
            if(feast != null){
                order.setFeastTitle(StringUtil.isNotEmpty(feast.getTitle()) ? feast.getTitle() : "");
                order.setImages(StringUtil.isNotEmpty(feast.getImg()) ? feast.getImg() : "");
            }
        }
        int total = orderService.getNumByUserId(map);
        JSONArray jsonArray = JSONArray.fromObject(orders);
        result.put("rows", jsonArray);
        result.put("total", total);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/postComment")
    public String postComment (@RequestParam(value = "userId", required = true) Long userId,
                               @RequestParam(value = "chefId", required = true) Long chefId,
                               @RequestParam(value = "comment", required = false) String comment,
                               @RequestParam(value = "images",required = false) String images,
                               HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(userId == null || userId < 1){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户id不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        if(chefId == null || chefId < 1){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","私厨id不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        Comment commentBean = new Comment();
        commentBean.setUserId(userId);
        commentBean.setChefId(chefId);
        commentBean.setComment(StringUtil.isNotEmpty(comment) ? comment : "");
        commentBean.setImages(StringUtil.isNotEmpty(images) ? images : "");
        commentBean.setCreateTime(DateUtil.getCurrentDate());

        int flag = commentService.create(commentBean);

        if(flag > 0){
            result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
            result.put("message","success");
            ResponseUtil.write(response, result);
            return null;
        }

        result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
        result.put("message"," 评论发表失败");
        ResponseUtil.write(response, result);
        return null;
    }
}
