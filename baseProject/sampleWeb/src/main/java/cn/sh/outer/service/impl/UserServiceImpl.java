package cn.sh.outer.service.impl;

import cn.sh.outer.dao.UserDao;
import cn.sh.outer.model.User;
import cn.sh.outer.model.req.UserInfo;
import cn.sh.outer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by HP on 2016/10/23.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 新增用户
     * @param user
     */
    public void createUser(UserInfo user){
        userDao.insert(user);
    }

    /**
     * 查询用户
     * @param queryUser
     * @return
     */
    public List<User> queryUser(User queryUser){
        return userDao.query(queryUser);
    }
}
