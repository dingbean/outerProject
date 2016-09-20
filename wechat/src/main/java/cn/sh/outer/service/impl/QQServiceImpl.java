package cn.sh.outer.service.impl;

import cn.sh.outer.dao.QQDao;
import cn.sh.outer.model.QQBean;
import cn.sh.outer.service.QQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/9/20.
 */
@Service("qqService")
public class QQServiceImpl implements QQService {

    @Autowired
    private QQDao qqDao;

    @Transactional
    public void insert(QQBean qqBean) {
        qqDao.insert(qqBean);
//        int i = 1/0;
    }
}
