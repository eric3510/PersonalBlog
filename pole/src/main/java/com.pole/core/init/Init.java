package com.pole.core.init;

import com.pole.config.innerservice.ConfigInnerService;
import com.pole.config.model.UrlHotSpotsDO;
import com.pole.core.configs.PoleConfig;
import com.pole.core.service.DaoMysqlService;
import com.pole.logresolve.model.CreateUrlStatisticsTableDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
    private static String lock = "0";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConfigInnerService configInnerService;

    @Autowired
    private PoleConfig poleConfig;

    @Autowired
    private DaoMysqlService daoMysqlService;

    @Override
    public void run(ApplicationArguments args) throws Exception{
//        this.timerCreateStatisticsTable();
    }

    public void timerCreateStatisticsTable(){
        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    while(true){
                        List<UrlHotSpotsDO> urlHotSpotsDOList = configInnerService.getAllUrlHotSpots();
                        //循环遍历是否需要新建表
                        for(UrlHotSpotsDO urlHotSpotsDO : urlHotSpotsDOList){
                            String tableName = urlHotSpotsDO.getTableName();
                            String createTime = UrlHotSpotsDO.URL_STATISTICS + tableName;
                            String selectFromName = String.format(
                                    "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='%s' AND TABLE_NAME='%s'",
                                    poleConfig.getDbName(),
                                    createTime
                            );
                            Map<String, Object> selectFromNameParamMap = new HashMap<>();
                            List<HashMap> tableNameList = daoMysqlService.list(HashMap.class, selectFromName, selectFromNameParamMap);
                            if(tableNameList != null){
                                if(tableNameList.size() != 0){
                                    continue;
                                }
                            }
                            CreateUrlStatisticsTableDO createUrlStatisticsTableDO = new CreateUrlStatisticsTableDO(tableName);
                            String createTableSql = createUrlStatisticsTableDO.getCreateTableSql();
                            Map<String, Object> paramMap = new HashMap<>();
                            daoMysqlService.update(createTableSql, paramMap);
                            logger.info("成功创建表:" + tableName);
                        }
                        Thread.sleep(5000);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
