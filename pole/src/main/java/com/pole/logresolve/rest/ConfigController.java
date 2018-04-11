package com.pole.logresolve.rest;

import com.pole.logresolve.dto.UrlHotSpotsDTO;
import com.core.pojo.ServerResponse;
import com.core.utils.BusinessException;
import com.pole.logresolve.service.UrlHotSpotsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    UrlHotSpotsService urlHotSpotsService;

    /***
     * 添加一个需要进行统计的url
     * @param urlHotSpotsDTO
     * @return
     */
    @RequestMapping(value = "/save/url", method = RequestMethod.POST)
    public ServerResponse saveUrl(@RequestBody final UrlHotSpotsDTO urlHotSpotsDTO){
        ServerResponse serverResponse = new ServerResponse();
        try{
            serverResponse.setData(urlHotSpotsService.saveUrlHotSpots(urlHotSpotsDTO));
            serverResponse.setCode(ServerResponse.SUCCESS);
            serverResponse.setMsg(ServerResponse.SUCCESS_MSG);
        }catch(BusinessException bx){
            serverResponse.setCode(ServerResponse.ERROR_BBUSINESS);
            serverResponse.setMsg(bx.getMessage());
            serverResponse.setCode(bx.getCode());
        }
        return serverResponse;
    }
}
