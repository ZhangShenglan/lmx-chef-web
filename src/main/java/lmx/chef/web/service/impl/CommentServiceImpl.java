package lmx.chef.web.service.impl;

import lmx.chef.web.dbproxy.dao.CommentMapper;
import lmx.chef.web.dbproxy.entity.Comment;
import lmx.chef.web.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/5.
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    public int create(Comment comment){
        return commentMapper.create(comment);
    }

    @Override
    public List<Comment> getByChefId(Long chefId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("chefId",chefId);
        return commentMapper.getByMap(map);
    }

    @Override
    public int getNumByChefId(Long chefId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("chefId",chefId);
        return commentMapper.getNumByMap(map);
    }
}
