package com.pole.core.init;

import com.pole.config.innerservice.ConfigInnerService;
import com.pole.config.model.UrlHotSpotsDO;
import com.pole.core.configs.PoleConfig;
import com.pole.core.service.DaoMysqlService;
import com.pole.core.utils.BusinessException;
import com.pole.logresolve.model.CreateUrlStatisticsTableDO;
import com.pole.logresolve.service.LogResolveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.commonj.TimerManagerTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @author 王强 Email : 
 * @version 创建时间：2018/4/4
 * Init
 */
@Component
public class Init implements ApplicationRunner{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConfigInnerService configInnerService;

    @Autowired
    private PoleConfig poleConfig;

    @Autowired
    private DaoMysqlService daoMysqlService;

    @Autowired
    private LogResolveService logResolveService;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        this.timerCreateStatisticsTable();

    }

    /***
     * 监控是否有新的需要统计的url添加
     */
    private void timerCreateStatisticsTable(){
        Thread thread = new Thread(){
            @Override
            public void run(){
                while(true){
                    try{
                        List<UrlHotSpotsDO> urlHotSpotsDOList = configInnerService.getAllUrlHotSpots();
                        //循环遍历是否需要新建表
                        for(UrlHotSpotsDO urlHotSpotsDO : urlHotSpotsDOList){
                            String createTableName = urlHotSpotsDO.getPrefixTableName();
                            String selectFromName = String.format(
                                    "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='%s' AND TABLE_NAME='%s'",
                                    poleConfig.getDbName(),
                                    createTableName
                            );
                            Map<String, Object> selectFromNameParamMap = new HashMap<>();
                            List<HashMap> tableNameList = daoMysqlService.list(HashMap.class, selectFromName, selectFromNameParamMap);
                            if(tableNameList != null){
                                if(tableNameList.size() != 0){
                                    continue;
                                }
                            }
                            CreateUrlStatisticsTableDO createUrlStatisticsTableDO = new CreateUrlStatisticsTableDO(createTableName);
                            String createTableSql = createUrlStatisticsTableDO.getCreateTableSql();
                            Map<String, Object> paramMap = new HashMap<>();
                            daoMysqlService.update(createTableSql, paramMap);
                            logger.info("成功创建表:" + createTableName);
                        }
                        Thread.sleep(5000);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    /***
     * 每天凌晨三点开始统计url
     */
    @Scheduled(cron="0 0 3 * * ?")
    private void statisticsUrl(){
        logResolveService.logParsing();
    }
}