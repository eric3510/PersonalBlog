package com.pole.logresolve.service.impl;

import com.pole.config.innerservice.ConfigInnerService;
import com.pole.config.model.UrlHotSpotsDO;
import com.pole.core.configs.PoleConfig;
import com.pole.core.service.impl.BaseServiceImpl;
import com.pole.core.utils.BaseUtils;
import com.pole.logresolve.model.UrlStatisticsDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pole.logresolve.service.LogResolveService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/4/2 下午8:12
 * AccessStatistics 日志解析
 */
@Service
public class LogResolveServiceImpl extends BaseServiceImpl implements LogResolveService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PoleConfig poleConfig;

    @Autowired
    private ConfigInnerService configInnerService;

    @Override
    public void logParsing(){
        logger.info("hello world!!!!!");
        UrlHotSpotsDO param = new UrlHotSpotsDO();
        this.getDao().list(UrlHotSpotsDO.class, param);
//        List<UrlHotSpotsDO> urlHotSpotsDOList = configInnerService.getAllUrlHotSpots();
//        BufferedReader bufferedReader = BaseUtils.FileUtils.getBufferedReader(poleConfig.getLogPath());
//        String line = "";
//        try{
//            Map<String, Integer> counterMap = new HashMap<>();
//            while((line = bufferedReader.readLine()) != null){
//                for(UrlHotSpotsDO urlHotSpotsDO : urlHotSpotsDOList){
//                    String url = urlHotSpotsDO.getUrl();
//                    if(!line.contains(url)){
//                        continue;
//                    }
//                    Integer counter;
//                    if((counter = counterMap.get(url)) == null){
//                        counter = 0;
//                    }
//                    counter = counter + 1;
//                    counterMap.put(urlHotSpotsDO.getTableName(), counter);
//                }
//            }
//            UrlStatisticsDO urlStatisticsDO = new UrlStatisticsDO();
//            urlStatisticsDO.get
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }
}
