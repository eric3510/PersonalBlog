package com.pole.demo;

import com.alibaba.fastjson.JSONObject;
import com.pole.core.service.DaoMysqlService;
import com.pole.core.service.UniqueSequenceServiceService;
import com.pole.logresolve.service.LogResolveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author 王强 Email : 
 * @version 创建时间：2018/4/2
 * DemoController
 */
@RestController
public class DemoController{
    @Autowired
    DaoMysqlService daoMysqlService;

    @Autowired
    UniqueSequenceServiceService uniqueSequenceServiceService;

    @Autowired
    private LogResolveService logResolveService;

    @RequestMapping("/")
    public String home(){
        logResolveService.logParsing();
        return "hello world";
    }
}
