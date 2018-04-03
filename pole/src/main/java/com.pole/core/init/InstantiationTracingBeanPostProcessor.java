package com.pole.core.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

@Component
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent>{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static int number = 0;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent){
        try{
            //只在初始化“根上下文”的时候执行
            if(applicationEvent.getSource() instanceof XmlWebApplicationContext){
                String displayName = ((XmlWebApplicationContext) applicationEvent.getSource()).getDisplayName();
                if(displayName.equals("Root WebApplicationContext")){
                }else if(displayName.equalsIgnoreCase("WebApplicationContext for namespace 'rest-servlet'")){
                    if(number == 0){
                        number++;
                    }else if(number == 1){
                    }
                }
            }
        }catch(Exception e){
            logger.error("((XmlWebApplicationContext) applicationEvent.getSource()).getDisplayName() 执行失败，请检查Spring版本是否支持");
        }
    }
}