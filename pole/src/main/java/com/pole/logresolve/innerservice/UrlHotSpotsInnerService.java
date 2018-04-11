package com.pole.logresolve.innerservice;

import com.pole.logresolve.dto.UrlHotSpotsDTO;
import com.pole.logresolve.model.UrlHotSpotsDO;
import com.core.service.BaseService;

import java.util.List;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/4/3 上午11:13
 * UrlHotSpotsInnerService
 */
public interface UrlHotSpotsInnerService extends BaseService{
    /***
     * 获取所有url统计记录
     * @return
     */
    List<UrlHotSpotsDO> getAllUrlHotSpots();

    /***
     * 添加一个url统计记录
     * @param urlHotSpotsDO
     * @return
     */
    String saveUrlHotSpots(UrlHotSpotsDO urlHotSpotsDO);

    /***
     * 将DTO中的数据装载到DO
     * @param urlHotSpotsDO 需要装载的DO
     * @param urlHotSpotsDTO 装有参数的DTO
     */
    void loadUrlHotSpotsDO(UrlHotSpotsDO urlHotSpotsDO, UrlHotSpotsDTO urlHotSpotsDTO);

    /***
     * 根据url查询一条记录
     * @param url url
     * @return
     */
    UrlHotSpotsDO getUrlHotSpotsDOByUrl(String url);
}
