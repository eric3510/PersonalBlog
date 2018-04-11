package com.pole.logresolve.service.impl;

import com.core.service.impl.BaseServiceImpl;
import com.core.utils.BusinessException;
import com.core.utils.ParamUtils;
import com.pole.logresolve.dto.UrlHotSpotsDTO;
import com.pole.logresolve.innerservice.UrlHotSpotsInnerService;
import com.pole.logresolve.model.UrlHotSpotsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pole.logresolve.service.UrlHotSpotsService;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/4/9 下午5:48
 * UrlHotSpots
 */
@Service
public class UrlHotSpotsServiceImpl extends BaseServiceImpl implements UrlHotSpotsService{
    @Autowired
    private UrlHotSpotsInnerService urlHotSpotsInnerService;

    @Override
    public String saveUrlHotSpots(UrlHotSpotsDTO urlHotSpotsDTO) throws BusinessException{
        ParamUtils.isApiDtoBlank(urlHotSpotsDTO);
        UrlHotSpotsDO urlHotSpotsDO = new UrlHotSpotsDO();
        urlHotSpotsInnerService.loadUrlHotSpotsDO(urlHotSpotsDO, urlHotSpotsDTO);
        return urlHotSpotsInnerService.saveUrlHotSpots(urlHotSpotsDO);
    }


}
