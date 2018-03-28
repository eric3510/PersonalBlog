package com.personalblog.passport.controller;

import com.alibaba.fastjson.JSONObject;
import com.personalblog.core.service.DaoMysqlService;
import com.personalblog.core.service.impl.DaoMysqlServiceImpl;
import com.personalblog.model.DemoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author 王强 Email : eric3510@foxmail.com
 * @version 创建时间：2018/3/7
 * DemoController
 */
@RestController
public class DemoController{
    @Autowired
    DaoMysqlServiceImpl daoMysqlServiceImpl;

    @RequestMapping("/")
    public String home(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "hello world");
        DemoDO demoDO = daoMysqlServiceImpl.getById(DemoDO.class, 123);
        return JSONObject.toJSONString(demoDO);
    }
}
