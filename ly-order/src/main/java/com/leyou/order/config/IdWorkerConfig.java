package com.leyou.order.config;

import com.leyou.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(IdWorkerProperties.class)
public class IdWorkerConfig {

    @Autowired
    private IdWorkerProperties prop;

    @Bean
    public IdWorker getIdWorker(){
        return new IdWorker(prop.getWorkerId(),prop.getDataCenterId());
    }
}
