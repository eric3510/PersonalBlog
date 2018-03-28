package com.core.service.impl;

import com.core.service.BaseService;
import com.core.service.DaoMongoService;
import com.core.service.DaoMysqlService;
import com.core.service.TimerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 王强
 * @version 创建时间：2017/08/17 12:14
 * BaseServiceImpl
 **/
@Service
public class BaseServiceImpl implements BaseService{

    @Resource
    private DaoMysqlService daoMysqlService;

    @Resource
    private TimerService timerService;

    @Resource
    private DaoMongoService daoMongoService;

    public DaoMysqlService getDao(){
        return daoMysqlService;
    }

    public DaoMongoService getDaoMongo(){
        return this.daoMongoService;
    }

    public TimerService getTimer(){
        return this.timerService;
    }


}
