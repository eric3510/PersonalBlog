package com.pole.logresolve.service.impl;

import com.pole.config.innerservice.ConfigInnerService;
import com.pole.config.model.UrlHotSpotsDO;
import com.pole.core.configs.PoleConfig;
import com.pole.core.service.impl.BaseServiceImpl;
import com.pole.core.utils.BaseUtils;
import com.pole.core.utils.BusinessException;
import com.pole.logresolve.innerservice.UrlStatisticsInnerService;
import com.pole.logresolve.model.UrlStatisticsDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pole.logresolve.service.LogResolveService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

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

    @Autowired
    private UrlStatisticsInnerService urlStatisticsInnerService;

    @Override
    public void logParsing(){
        UrlHotSpotsDO param = new UrlHotSpotsDO();
        this.getDao().list(UrlHotSpotsDO.class, param);
        List<UrlHotSpotsDO> urlHotSpotsDOList = configInnerService.getAllUrlHotSpots();
        BufferedReader bufferedReader = BaseUtils.FileUtils.getBufferedReader(poleConfig.getLogPath());
        String line;
        try{
            Map<String, Long> counterMap = new HashMap<>();
            while((line = bufferedReader.readLine()) != null){
                for(UrlHotSpotsDO urlHotSpotsDO : urlHotSpotsDOList){
                    String url = urlHotSpotsDO.getUrl();
                    if(!line.contains(url)){
                        continue;
                    }
                    Long counter;
                    if((counter = counterMap.get(url)) == null){
                        counter = 0L;
                    }
                    counter = counter + 1;
                    counterMap.put(urlHotSpotsDO.getUrl(), counter);
                }
            }
            for(UrlHotSpotsDO urlHotSpotsDO : urlHotSpotsDOList){
                String url = urlHotSpotsDO.getUrl();
                Long counter = counterMap.get(url);
                counter = counter == null ? 0 : counter;
                UrlStatisticsDO urlStatisticsDO = new UrlStatisticsDO();
                Date todayDate = new Date();
                String format = "yyyyMMdd";
                Integer todayInt = Integer.parseInt(BaseUtils.formatDate(todayDate, format));
                urlStatisticsDO.setId(this.getUniqueSequenceServiceService().getUUID());
                urlStatisticsDO.setCreateTime(todayDate);
                urlStatisticsDO.setUpdateTime(todayDate);
                urlStatisticsDO.setStatisticsTime(todayInt);
                urlStatisticsDO.setAccessNumber(counter);
                Integer yesterdayInt = Integer.parseInt(BaseUtils.DateUtils.getSpecifiedDate(todayDate, -1, format));
                UrlStatisticsDO yesterdayUrlStatisticsDO = urlStatisticsInnerService.getStatisticsTime(yesterdayInt, urlHotSpotsDO.getPrefixTableName());
                if(yesterdayUrlStatisticsDO == null){
                    urlStatisticsDO.setTotalAccessNumber(counter);
                }else{
                    urlStatisticsDO.setTotalAccessNumber(counter + yesterdayUrlStatisticsDO.getTotalAccessNumber());
                }
                UrlStatisticsDO todayUrlHotSpotsDO = urlStatisticsInnerService.getStatisticsTime(todayInt, urlHotSpotsDO.getPrefixTableName());
                if(todayUrlHotSpotsDO == null){//如果等于空的话则添加
                    int result = urlStatisticsInnerService.save(urlStatisticsDO, urlHotSpotsDO.getPrefixTableName());
                    if(result == 0){
                        String errorMsg = "添加最新统计结果失败";
                        logger.error(errorMsg);
                        throw new BusinessException(errorMsg);
                    }
                    logger.warn(String.format("name:%s, url:%s, tableName:%s 统计结果已添加", urlHotSpotsDO.getName(), urlHotSpotsDO.getUrl(), urlHotSpotsDO.getPrefixTableName()));
                }else{//否则为更新
                    urlStatisticsDO.setId(todayUrlHotSpotsDO.getId());
                    urlStatisticsDO.setCreateTime(null);
                    this.getDao().updateById(urlStatisticsDO, urlHotSpotsDO.getPrefixTableName());
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
