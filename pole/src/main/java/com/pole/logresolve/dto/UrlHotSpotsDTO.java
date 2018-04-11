package com.pole.logresolve.dto;

import com.alibaba.fastjson.JSONArray;
import com.core.annotation.ApiModelProperty;
import com.core.utils.BaseUtils;
import com.pole.logresolve.model.UrlHotSpotsDO;
import lombok.Data;

import java.util.*;

/***
 * @author 王强 Email : 
 * @version 创建时间：2018/4/9
 * UrlHotSpotsDTO
 */
@Data
public class UrlHotSpotsDTO{
    /***
     * url
     */
    @ApiModelProperty(required = true)
    private String url;

    /***
     * 名称
     */
    @ApiModelProperty(required = true)
    private String name;

    /***
     * 表名
     */
    @ApiModelProperty(required = true)
    private String tableName;

    /***
     * 是否立即进行统计[true:是][false:否]
     */
    @ApiModelProperty(required = true)
    private Boolean immediately;

    public void logParsings(String[] logFileNames, List<UrlHotSpotsDO> urlHotSpotsDOList){
        Integer[] logFileDateIntegers = new Integer[logFileNames.length];
        Map<Integer, String> logFileNameMap = new HashMap<>();
        for(int i = 0; i < logFileNames.length; i++){
            String logFileName = logFileNames[i];
            Integer logFileDateInteger = Integer.parseInt(BaseUtils.StringUtilsSon.getStringToNum(logFileName));
            logFileDateIntegers[i] = logFileDateInteger;
            logFileNameMap.put(logFileDateInteger, logFileName);
        }
        Arrays.sort(logFileDateIntegers);
        for(int i = 0; i < logFileDateIntegers.length; i++){
            Integer logFileDateInteger = logFileDateIntegers[i];
            logFileNames[i] = logFileNameMap.get(logFileDateInteger);
        }
        for(String logFileName : logFileNames){
            System.out.println(logFileName + "  " + BaseUtils.StringUtilsSon.getStringToNum(logFileName));
        }
    }


}
