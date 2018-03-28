package com.personalblog.core.service.impl;

import com.personalblog.core.configs.TimerConfig;
import com.personalblog.core.service.TimerService;
import com.personalblog.core.timertask.BusinessTask;
import com.personalblog.core.timertask.DateToX;
import com.personalblog.core.timertask.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author eric E-mail:
 * @version 创建时间：2017/12/4 下午8:57
 * Timer
 */
@Service
public class TimerServiceImpl{//implements TimerService
    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Resource
//    private JedisPool jedisPool;

//    public void schedule(final String url, final Map<String, String> params, final RequestMethod requestMethod, DateToX initialDelay){
//        BusinessTask businessTask = new BusinessTask();
//        businessTask.setUrl(url);
//        businessTask.setInitialDelay(initialDelay);
//        businessTask.setParams(params);
//        businessTask.setRequestMethod(requestMethod.name());
//        this.producerTaskMsg(businessTask, jedisPool.getResource());
//    }
//
//    private void producerTaskMsg(final BusinessTask businessTask, final RedisProperties.Jedis jedis){
//        logger.info("TimerConfig.urlAddress = " + TimerConfig.urlAddress);
//        businessTask.setUrl(TimerConfig.urlAddress + businessTask.getUrl());
//        String task = TimerConfig.hashedWheelProducerTask;
//        jedis.lpush(task, MessageUtil.object2String(businessTask));
//        jedis.close();
//    }
}
