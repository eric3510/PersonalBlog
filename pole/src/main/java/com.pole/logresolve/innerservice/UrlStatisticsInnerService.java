package com.pole.logresolve.innerservice;

import com.pole.core.service.BaseService;
import com.pole.logresolve.model.UrlStatisticsDO;

import java.util.Map;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/4/8 下午2:42
 * UrlStatistics
 */
public interface UrlStatisticsInnerService extends BaseService{
    int save(UrlStatisticsDO urlStatisticsDO, String tableName);

    UrlStatisticsDO get(UrlStatisticsDO urlStatisticsDO);

    UrlStatisticsDO get(String sql, Map<String, Object> param);

    UrlStatisticsDO getByUrl(String url, String tableName);

    UrlStatisticsDO getStatisticsTime(Integer statisticsTime, String tableName);
}
