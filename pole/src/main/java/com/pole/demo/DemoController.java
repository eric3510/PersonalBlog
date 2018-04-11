package com.pole.demo;

import com.core.service.DaoMysqlService;
import com.core.service.UniqueSequenceServiceService;
import com.core.utils.BaseUtils;
import com.pole.logresolve.model.UrlHotSpotsDO;
import com.pole.logresolve.service.LogResolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/***
 * @author 王强 Email : 
 * @version 创建时间：2018/4/2
 * DemoController
 */
@RestController
public class DemoController{
    @Autowired
    private DaoMysqlService daoMysqlService;

    @Autowired
    private UniqueSequenceServiceService uniqueSequenceServiceService;

    @Autowired
    private LogResolveService logResolveService;

    @RequestMapping("/")
    public String home(){
        UrlHotSpotsDO urlHotSpotsDO = new UrlHotSpotsDO();
        urlHotSpotsDO.setId(uniqueSequenceServiceService.getUUID());
        urlHotSpotsDO.setName("demo");
        urlHotSpotsDO.setTableName("demo");
        urlHotSpotsDO.setImmediately(false + "");
        Date date = new Date();
        urlHotSpotsDO.setCreateTime(date);
        urlHotSpotsDO.setUpdateTime(date);
        urlHotSpotsDO.setUrl("www.jd.com23333");
        return "hello world";
    }
}
