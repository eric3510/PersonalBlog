package com.pole.core.service.impl;

import org.springframework.stereotype.Service;

import com.pole.core.service.UniqueSequenceServiceService;

import java.util.UUID;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/3/29 下午3:57
 * UniqueSequenceService
 */
@Service
public class UniqueSequenceServiceServiceImpl extends BaseServiceImpl implements UniqueSequenceServiceService{

    @Override
    public String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}