package com.pole.config.rest;

import com.pole.config.innerservice.ConfigInnerService;
import com.pole.config.model.UrlHotSpotsDO;
import com.pole.core.pojo.ServerResponse;
import com.pole.core.utils.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author 王强 Email : 
 * @version 创建时间：2018/4/3
 * ConfigController
 */
@RestController
public class ConfigController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConfigInnerService configInnerService;

    @RequestMapping("/save/url")
    public ServerResponse saveUrl(final String name, final String url){
        ServerResponse serverResponse = new ServerResponse();
        try{
            configInnerService.saveUrlHotSpots(new UrlHotSpotsDO(name, url));
            serverResponse.setCode(ServerResponse.SUCCESS);
            serverResponse.setMsg(ServerResponse.SUCCESS_MSG);
        }catch(BusinessException bx){
            serverResponse.setMsg(bx.getMessage());
            serverResponse.setCode(bx.getCode());
        }
        return serverResponse;
    }
}
