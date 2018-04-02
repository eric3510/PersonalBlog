package com.pole.demo;

import com.alibaba.fastjson.JSONObject;
import com.pole.core.service.DaoMysqlService;
import com.pole.core.service.UniqueSequenceServiceService;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String home(){
        logger.info("demo");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "hello world");
        DemoDO demoDO = new DemoDO();
        demoDO.setName("张三");
        demoDO.setId(uniqueSequenceServiceService.getUUID());
        daoMysqlService.save(demoDO);
        DemoDO param = new DemoDO();
        return JSONObject.toJSONString(daoMysqlService.list(DemoDO.class, param));
    }
}