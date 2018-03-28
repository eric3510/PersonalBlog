package com.core.service;

import com.core.timertask.DateToX;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author eric E-mail:
 * @version 创建时间：2017/12/4 下午8:57
 * Timer
 */
public interface TimerService{
    void schedule(final String url, final Map<String, String> params, final RequestMethod requestMethod, DateToX initialDelay);
}
