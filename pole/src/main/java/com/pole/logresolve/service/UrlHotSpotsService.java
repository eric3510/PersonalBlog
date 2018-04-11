package com.pole.logresolve.service;

import com.core.service.BaseService;
import com.core.utils.BusinessException;
import com.pole.logresolve.dto.UrlHotSpotsDTO;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/4/9 下午5:48
 * UrlHotSpots
 */
public interface UrlHotSpotsService extends BaseService{
    /***
     * 保存一个需要统计的url
     * @param urlHotSpotsDTO urlHotSpotsDTO
     * @return id
     * @throws BusinessException
     */
    String saveUrlHotSpots(UrlHotSpotsDTO urlHotSpotsDTO) throws BusinessException;
}
