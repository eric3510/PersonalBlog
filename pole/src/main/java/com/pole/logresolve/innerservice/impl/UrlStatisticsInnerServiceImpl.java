package com.pole.logresolve.innerservice.impl;

import com.core.service.impl.BaseServiceImpl;
import com.pole.logresolve.model.UrlStatisticsDO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pole.logresolve.innerservice.UrlStatisticsInnerService;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/4/8 下午2:42
 * UrlStatistics
 */
@Service
public class UrlStatisticsInnerServiceImpl extends BaseServiceImpl implements UrlStatisticsInnerService{
    @Override
    public int save(UrlStatisticsDO urlStatisticsDO, String tableName){
        return this.getDao().save(urlStatisticsDO, tableName);
    }

    @Override
    public UrlStatisticsDO get(UrlStatisticsDO urlStatisticsDO){
        return this.getDao().get(UrlStatisticsDO.class, urlStatisticsDO);
    }

    @Override
    public UrlStatisticsDO get(String sql, Map<String, Object> param){
        List<UrlStatisticsDO> urlStatisticsDOList = this.getDao().list(UrlStatisticsDO.class, sql, param);
        if(urlStatisticsDOList == null){
            return null;
        }
        if(urlStatisticsDOList.size() == 0){
            return null;
        }
        return urlStatisticsDOList.get(0);
    }

    @Override
    public UrlStatisticsDO getByUrl(String url, String tableName){
        Map<String, Object> param = new HashMap<>();
        param.put("url", url);
        UrlStatisticsDO urlStatisticsDO = this.get(String.format("SELECT * FROM %s WHERE url = #{url}", tableName), param);
        return urlStatisticsDO;
    }

    @Override
    public UrlStatisticsDO getStatisticsTime(Integer statisticsTime, String tableName){
        return this.getDao().getByKey(UrlStatisticsDO.class, "new_time", statisticsTime, tableName);
    }
}