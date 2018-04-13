package com.pole.logresolve.service.impl;

import com.core.utils.ParamUtils;
import com.pole.logresolve.dto.UrlHotSpotsDTO;
import com.pole.logresolve.innerservice.UrlHotSpotsInnerService;
import com.pole.logresolve.model.UrlHotSpotsDO;
import com.core.configs.PoleConfig;
import com.core.service.impl.BaseServiceImpl;
import com.core.utils.BaseUtils;
import com.core.utils.BusinessException;
import com.pole.logresolve.innerservice.UrlStatisticsInnerService;
import com.pole.logresolve.model.UrlStatisticsDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pole.logresolve.service.LogResolveService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/4/2 下午8:12
 * AccessStatistics 日志解析
 */
@Service
public class LogResolveServiceImpl extends BaseServiceImpl implements LogResolveService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PoleConfig poleConfig;

    @Autowired
    private UrlHotSpotsInnerService urlHotSpotsInnerService;

    @Autowired
    private UrlStatisticsInnerService urlStatisticsInnerService;

    /***
     * 将按文件中日期排好序的文件名遍历扫描统计
     * @param fileNamePathMap key:文件名称,value 同文件名下的list文件全路径
     * @param fileNames 按日期排序的log文件名称
     * @param urlHotSpotsDOList 需要统计的url list
     */
    private void logParsingTraverse(Map<String, List<String>> fileNamePathMap, String[] fileNames, List<UrlHotSpotsDO> urlHotSpotsDOList){
        for(String fileName : fileNames){
            List<String> fileNamePathList = fileNamePathMap.get(fileName);
            for(String fileNamePath : fileNamePathList){
                this.logParsing(fileNamePath, fileName, urlHotSpotsDOList);
            }
        }
    }

    /***
     * 统计url
     * @param fileNamePath 文件全路径
     * @param fileName 文件名称
     * @param urlHotSpotsDOList 需要统计的url列表
     */
    private void logParsing(String fileNamePath, String fileName, List<UrlHotSpotsDO> urlHotSpotsDOList){
        // TODO: 2018/4/11 向文件中写入一个token来判断此文件是否已经遍历过了
        UrlHotSpotsDO param = new UrlHotSpotsDO();
        this.getDao().list(UrlHotSpotsDO.class, param);
        BufferedReader bufferedReader = BaseUtils.FileUtils.getBufferedReader(fileNamePath);
        String line;
        try{
            Map<String, Long> counterMap = new HashMap<>();
            while((line = bufferedReader.readLine()) != null){
                for(UrlHotSpotsDO urlHotSpotsDO : urlHotSpotsDOList){
                    String url = urlHotSpotsDO.getUrl();
                    if(!line.contains(url)){
                        continue;
                    }
                    Long counter;
                    if((counter = counterMap.get(url)) == null){
                        counter = 0L;
                    }
                    counter = counter + 1;
                    counterMap.put(urlHotSpotsDO.getUrl(), counter);
                }
            }
            for(UrlHotSpotsDO urlHotSpotsDO : urlHotSpotsDOList){
                String url = urlHotSpotsDO.getUrl();
                Long counter = counterMap.get(url);
                counter = counter == null ? 0 : counter;
                UrlStatisticsDO urlStatisticsDO = new UrlStatisticsDO();
                String format = "yyyyMMdd";
                Date todayDate = BaseUtils.DateUtils.stringTodate(format, BaseUtils.StringUtilsSon.getStringToNum(fileName));
                Integer todayInt = Integer.parseInt(BaseUtils.formatDate(todayDate, format));
                urlStatisticsDO.setId(this.getUniqueSequenceServiceService().getUUID());
                Date nowDate = new Date();
                urlStatisticsDO.setCreateTime(nowDate);
                urlStatisticsDO.setUpdateTime(nowDate);
                urlStatisticsDO.setNewTime(todayInt);
                urlStatisticsDO.setIncreaseNum(counter);
                Integer yesterdayInt = Integer.parseInt(BaseUtils.DateUtils.getSpecifiedDate(todayDate, -1, format));
                UrlStatisticsDO yesterdayUrlStatisticsDO = urlStatisticsInnerService.getStatisticsTime(yesterdayInt, urlHotSpotsDO.getPrefixTableName());
                if(yesterdayUrlStatisticsDO == null){
                    urlStatisticsDO.setCurrentNum(counter);
                }else{
                    urlStatisticsDO.setCurrentNum(counter + yesterdayUrlStatisticsDO.getCurrentNum());
                }
                UrlStatisticsDO todayUrlHotSpotsDO = urlStatisticsInnerService.getStatisticsTime(todayInt, urlHotSpotsDO.getPrefixTableName());
                if(todayUrlHotSpotsDO == null){//如果等于空的话则添加
                    int result = urlStatisticsInnerService.save(urlStatisticsDO, urlHotSpotsDO.getPrefixTableName());
                    if(result == 0){
                        String errorMsg = "添加最新统计结果失败";
                        logger.error(errorMsg);
                        throw new BusinessException(errorMsg);
                    }
                    logger.warn(String.format("name:%s, url:%s, tableName:%s 统计结果已添加", urlHotSpotsDO.getName(), urlHotSpotsDO.getUrl(), urlHotSpotsDO.getPrefixTableName()));
                }else{//否则为更新
                    urlStatisticsDO.setUpdateTime(nowDate);
                    urlStatisticsDO.setId(todayUrlHotSpotsDO.getId());
                    urlStatisticsDO.setIncreaseNum(todayUrlHotSpotsDO.getIncreaseNum() + counter);
                    urlStatisticsDO.setCurrentNum(todayUrlHotSpotsDO.getCurrentNum() + counter);
                    urlStatisticsDO.setCreateTime(null);
                    this.getDao().updateById(urlStatisticsDO, urlHotSpotsDO.getPrefixTableName());
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /***
     * 获取一个文件夹下的所有文件名称数组
     * @param path 文件夹路径
     * @return
     */
    private String[] getLogFileNames(String path){
        File file = new File(path);
        File[] logFiles = file.listFiles();
        List<String> logFileList = new ArrayList<>();
        for(File logFile : logFiles){
            String fileName = logFile.getName();
            if(!fileName.contains("log")){
                continue;
            }
            logFileList.add(fileName);
        }
        return logFileList.toArray(new String[logFileList.size()]);
    }

    /***
     * 将log文件名称数组按日期正序排序
     * @param logFileNames 文件名称数组
     */
    private void logFileNamesSort(String[] logFileNames){
        List<Integer> logFileDateIntegerList = new ArrayList<>();
        Map<Integer, String> logFileNameMap = new HashMap<>();
        for(int i = 0; i < logFileNames.length; i++){
            String logFileName = logFileNames[i];
            if(BaseUtils.isBlank(logFileName)){
                continue;
            }
            if(logFileName.length() < 8){
                continue;
            }
            String logFileNameDateStr = BaseUtils.StringUtilsSon.getStringToNum(logFileName);
            if(BaseUtils.isBlank(logFileNameDateStr)){
                continue;
            }
            if(logFileNameDateStr.length() < 8){
                continue;
            }
            Integer logFileDateInteger = Integer.parseInt(logFileNameDateStr);
            logFileDateIntegerList.add(logFileDateInteger);
            logFileNameMap.put(logFileDateInteger, logFileName);
        }
        Integer[] logFileDateIntegers = logFileDateIntegerList.toArray(new Integer[logFileDateIntegerList.size()]);
        Arrays.sort(logFileDateIntegers);
        for(int i = 0; i < logFileDateIntegers.length; i++){
            Integer logFileDateInteger = logFileDateIntegers[i];
            logFileNames[i] = logFileNameMap.get(logFileDateInteger);
        }
    }

    /***
     * 统计一个url列表
     * @param urlHotSpotsDOList
     */
    private void logParsings(List<UrlHotSpotsDO> urlHotSpotsDOList){
        Map<String, List<String>> fileNamePathMap = new HashMap<>();
        Set<String> fileNameList = new HashSet<>();
        this.lastParsingToMap(fileNamePathMap, fileNameList);
        String[] fileNames = fileNameList.toArray(new String[fileNameList.size()]);
        this.logFileNamesSort(fileNames);
        this.logParsingTraverse(fileNamePathMap, fileNames, urlHotSpotsDOList);
    }

    /***
     * 将文件队列装载到map中(key:文件名称,value 同文件名下的list文件全路径)
     * @param fileNamePathMap key:文件名称,value 同文件名下的list文件全路径
     * @param fileNameList 文件名称Set集合
     */
    private void lastParsingToMap(Map<String, List<String>> fileNamePathMap, Set<String> fileNameList){
        String[] paths = poleConfig.getLogPaths();
        List<String> fileNamePathList = new ArrayList<>();
        for(String path : paths){
            String[] fileNamesInner = this.getLogFileNames(path);
            for(String fileName : fileNamesInner){
                String fileNamePath = path + fileName;
                fileNamePathList.add(fileNamePath);
                List<String> fileList = fileNamePathMap.get(fileName);
                if(fileList == null){
                    fileList = new ArrayList<>();
                    fileList.add(fileNamePath);
                    fileNamePathMap.put(fileName, fileList);
                }else{
                    fileList.add(fileNamePath);
                }
                fileNameList.add(fileName);
            }
        }
    }

    /***
     * 保存一个url
     * @param urlHotSpotsDTO 需要添加的url
     * @throws BusinessException
     */
    public void saveLogStatistics(UrlHotSpotsDTO urlHotSpotsDTO) throws BusinessException{
        ParamUtils.isApiDtoBlank(urlHotSpotsDTO);
        UrlHotSpotsDO urlHotSpotsDO = new UrlHotSpotsDO();
        urlHotSpotsInnerService.loadUrlHotSpotsDO(urlHotSpotsDO, urlHotSpotsDTO);
        urlHotSpotsInnerService.saveUrlHotSpots(urlHotSpotsDO);
    }

    /***
     * 根据url进行统计
     * @param urlHotSpotsDO urlHotSpotsDO
     * @throws BusinessException
     */
    public void logStatisticsByUrl(UrlHotSpotsDO urlHotSpotsDO) throws BusinessException{
        List<UrlHotSpotsDO> urlHotSpotsDOList = new ArrayList<>();
        urlHotSpotsDOList.add(urlHotSpotsDO);
        this.logParsings(urlHotSpotsDOList);
    }

    @Override
    public void statisticsLastDay() throws BusinessException{
        Map<String, List<String>> fileNamePathMap = new HashMap<>();
        Set<String> fileNameList = new HashSet<>();
        this.lastParsingToMap(fileNamePathMap, fileNameList);
        String[] fileNames = fileNameList.toArray(new String[fileNameList.size()]);
        this.logFileNamesSort(fileNames);
        fileNames = new String[]{fileNames[fileNames.length - 1]};
        this.logParsingTraverse(fileNamePathMap, fileNames, urlHotSpotsInnerService.getAllUrlHotSpots());
    }

    /***
     * 全量url统计
     * @throws
     */
    public void logStatistics() throws BusinessException{
        this.logParsings(urlHotSpotsInnerService.getAllUrlHotSpots());
    }
}