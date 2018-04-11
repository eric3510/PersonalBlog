package com.pole.logresolve.rest;

import com.core.pojo.ServerResponse;
import com.core.utils.BusinessException;
import com.pole.logresolve.dto.UrlHotSpotsDTO;
import com.pole.logresolve.service.LogResolveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 * @author 王强 Email : 
 * @version 创建时间：2018/4/9
 * LogResolveController
 */
@RestController
@RequestMapping("/log/resolve")
public class LogResolveController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogResolveService logResolveService;

    /***
     * 添加一个需要统计的url，并立即或下次进行统计
     * @param urlHotSpotsDTO 需要添加的url dto业务传输对象
//     * @param immediately 是否立即进行统计[true:是][false:否]
     * @return
     */
    @RequestMapping(value = "/save/and/statistics", method = RequestMethod.POST)
    public ServerResponse statisticsOneUrl(@RequestBody final UrlHotSpotsDTO urlHotSpotsDTO){
        ServerResponse serverResponse = new ServerResponse();
        try{
            logResolveService.saveLogStatistics(urlHotSpotsDTO);
            serverResponse.setCode(ServerResponse.SUCCESS);
            serverResponse.setMsg(ServerResponse.SUCCESS_MSG);
        }catch(BusinessException bx){
            serverResponse.setCode(bx.getCode());
            serverResponse.setMsg(bx.getMessage());
            serverResponse.setData(bx.getData());
        }
        return serverResponse;
    }
}
