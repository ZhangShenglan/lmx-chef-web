package lmx.chef.web.dbproxy.dao;

import lmx.chef.web.dbproxy.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/5.
 */
@Repository
public interface CommentMapper {

    int create(Comment comment);

    List<Comment> getByMap(Map<String, Object> map);

    int getNumByMap(Map<String, Object> map);
}
