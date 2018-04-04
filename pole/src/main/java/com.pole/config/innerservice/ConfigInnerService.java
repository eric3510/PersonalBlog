package com.pole.config.innerservice;

import com.pole.config.model.UrlHotSpotsDO;
import com.pole.core.service.BaseService;

import java.util.List;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/4/3 上午11:13
 * ConfigInnerService
 */
public interface ConfigInnerService extends BaseService{
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
    int saveUrlHotSpots(UrlHotSpotsDO urlHotSpotsDO);
}
