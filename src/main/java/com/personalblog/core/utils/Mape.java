package com.personalblog.core.utils;

import java.util.HashMap;

/***
 * @author 王强 Email : wangqiang@hushijie.com.cn
 * @version 创建时间：2017/11/16
 * Mape
 */
public class Mape<K, V> extends HashMap{
    public Mape<K, V> pute(K key, V value){
        this.put(key, value);
        return this;
    }
}
