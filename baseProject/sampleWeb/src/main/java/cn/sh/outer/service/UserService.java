package cn.sh.outer.service;

import cn.sh.outer.model.User;
import cn.sh.outer.model.req.UserInfo;

import java.util.List;

/**
 * Created by HP on 2016/10/23.
 */
public interface UserService {

    /**
     * 新增用户
     * @param user
     */
    public void createUser(UserInfo user);

    /**
     * 查询用户
     * @param queryUser
     * @return
     */
    public List<User> queryUser(User queryUser);
}
