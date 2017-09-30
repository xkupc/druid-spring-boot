package com.apec.druid.elasticjob.config;

import com.apec.framework.elasticjob.model.ZookeeperConfigItem;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author xk
 * @createTime 2017/9/29 0029 下午 2:58
 * @description zookeeper配置项初始化
 */
@Configuration
public class ZookeeperConfig extends BaseConfig {
    private static Logger logger = LoggerFactory.getLogger(ZookeeperConfig.class);

    @Value("${ejzk.json}")
    private String ejzkConfigJsonStr;

    @Bean
    public ZookeeperConfigItem createZookeeperConfigItem() {
        try {
            return objectMapper().readValue(ejzkConfigJsonStr, ZookeeperConfigItem.class);
        } catch (JsonParseException e) {
            logger.error(" *** JsonParse Exception e=" + e);
        } catch (JsonMappingException e) {
            logger.error(" *** JsonMapping Exception e=" + e);
        } catch (IOException e) {
            logger.error(" *** Json Exception e=" + e);
        }
        return null;
    }
}
