package cn.sh.outer.service.impl;

import cn.sh.outer.dao.RedisDao;
import cn.sh.outer.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HP on 2016/10/13.
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private RedisDao redisDao;

    @Override
    public void testRedis(String test) {
        redisDao.saveValue("test",test);
    }
}
