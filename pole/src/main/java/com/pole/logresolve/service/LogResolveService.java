package com.pole.logresolve.service;

import com.core.service.BaseService;
import com.core.utils.BusinessException;
import com.pole.logresolve.dto.UrlHotSpotsDTO;
import com.pole.logresolve.model.UrlHotSpotsDO;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/4/2 下午8:12
 * AccessStatistics 日志解析
 */
public interface LogResolveService extends BaseService{
    /***
     * 需要添加并立即统计的url
     * @param urlHotSpotsDTO 需要添加的url
     */
    void saveLogStatistics(final UrlHotSpotsDTO urlHotSpotsDTO) throws BusinessException;

    /***
     * 全量url统计
     * @throws BusinessException
     */
    void logStatistics() throws BusinessException;

    /***
     * 统计一个url
     * @param urlHotSpotsDO
     * @throws BusinessException
     */
    void logStatisticsByUrl(UrlHotSpotsDO urlHotSpotsDO) throws BusinessException;

    /***
     * 统计最后一天的log
     */
    void statisticsLastDay() throws BusinessException;
}
