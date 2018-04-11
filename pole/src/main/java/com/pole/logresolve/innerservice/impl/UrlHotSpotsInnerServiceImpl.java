package com.pole.logresolve.innerservice.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.utils.BusinessException;
import com.pole.logresolve.dto.UrlHotSpotsDTO;
import com.pole.logresolve.model.UrlHotSpotsDO;
import com.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.pole.logresolve.innerservice.UrlHotSpotsInnerService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/4/3 上午11:13
 * UrlHotSpotsInnerService
 */
@Service
public class UrlHotSpotsInnerServiceImpl extends BaseServiceImpl implements UrlHotSpotsInnerService{
    @Override
    public List<UrlHotSpotsDO> getAllUrlHotSpots(){
        UrlHotSpotsDO param = new UrlHotSpotsDO();
        return this.getDao().list(UrlHotSpotsDO.class, param);
    }

    @Override
    public String saveUrlHotSpots(UrlHotSpotsDO urlHotSpotsDO){
        Date date = new Date();
        urlHotSpotsDO.setCreateTime(date);
        urlHotSpotsDO.setUpdateTime(date);
        String id = this.getUniqueSequenceServiceService().getUUID();
        urlHotSpotsDO.setId(id);
        if(this.getDao().getByKey(UrlHotSpotsDO.class, "url", urlHotSpotsDO.getUrl(), urlHotSpotsDO.tableName()) != null){
            throw new BusinessException(String.format("此url%s已经存在, 不能添加", urlHotSpotsDO.getUrl()));
        }
        int result = this.getDao().save(urlHotSpotsDO);
        if(result <= 0){
            throw new BusinessException("保存urlHotSpotsDO失败:" + JSONObject.toJSONString(urlHotSpotsDO));
        }
        return id;
    }

    @Override
    public void loadUrlHotSpotsDO(UrlHotSpotsDO urlHotSpotsDO, UrlHotSpotsDTO urlHotSpotsDTO){
        urlHotSpotsDO.setUrl(urlHotSpotsDTO.getUrl());
        urlHotSpotsDO.setTableName(urlHotSpotsDTO.getTableName());
        urlHotSpotsDO.setName(urlHotSpotsDTO.getName());
        urlHotSpotsDO.setImmediately(urlHotSpotsDTO.getImmediately() + "");
    }

    public UrlHotSpotsDO getUrlHotSpotsDOByUrl(String url){
        return this.getDao().getByKey(UrlHotSpotsDO.class, "url", url, new UrlHotSpotsDO().tableName());
    }
}
