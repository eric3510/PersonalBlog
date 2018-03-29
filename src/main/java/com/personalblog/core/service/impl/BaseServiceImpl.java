package com.personalblog.core.service.impl;

import com.personalblog.core.service.BaseService;
import com.personalblog.core.service.DaoMongoService;
import com.personalblog.core.service.DaoMysqlService;
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
    private DaoMongoService daoMongoService;

    public DaoMysqlService getDao(){
        return daoMysqlService;
    }

    public DaoMongoService getDaoMongo(){
        return this.daoMongoService;
    }
}
