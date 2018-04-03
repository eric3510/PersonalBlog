package com.pole.config.innerservice.impl;

import com.pole.config.model.UrlHotSpotsDO;
import com.pole.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.pole.config.innerservice.ConfigInnerSevice;

import java.util.List;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/4/3 上午11:13
 * ConfigInnerSevice
 */
@Service
public class ConfigInnerSeviceImpl extends BaseServiceImpl implements ConfigInnerSevice{
    @Override
    public List<UrlHotSpotsDO> getAllUrlHotSpots(){
        UrlHotSpotsDO param = new UrlHotSpotsDO();
        return this.getDao().list(UrlHotSpotsDO.class, param);
    }

    @Override
    public int saveUrlHotSpots(UrlHotSpotsDO urlHotSpotsDO){
        urlHotSpotsDO.setId(this.getUniqueSequenceServiceService().getUUID());
        return this.getDao().save(urlHotSpotsDO);
    }
}
