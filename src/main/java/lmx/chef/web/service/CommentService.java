package lmx.chef.web.service;

import lmx.chef.web.dbproxy.entity.Comment;

import java.util.List;

/**
 * Created by zhangshenglan on 16/5/5.
 */
public interface CommentService {

    int create(Comment comment);

    List<Comment> getByChefId(Long chefId);

    int getNumByChefId(Long chefId);
}
