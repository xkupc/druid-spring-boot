package com.apec.druid.elasticjob.config;

import com.apec.framework.elasticjob.model.APECJobCoreConfigItem;
import com.apec.framework.elasticjob.model.ZookeeperConfigItem;
import com.apec.framework.elasticjob.proxy.EJZkRegistryCenterProxy;
import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;


/**
 * @author xk
 * @createTime 2017/9/29 0029 下午 3:35
 * @description elastic-job 配置初始化
 */
@Configuration
@AutoConfigureAfter(ZookeeperConfig.class)
@Import(ZookeeperConfig.class)
public class APECElasticJobConfig extends BaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(APECElasticJobConfig.class);

    /**
     * 注册中心初始化
     *
     * @param configItem
     * @return
     */
    @Bean
    public EJZkRegistryCenterProxy createEJZkRegistryCenterProxy(ZookeeperConfigItem configItem) {
        ZookeeperConfiguration config = new ZookeeperConfiguration(configItem.getServerLists(), configItem.getNamespace());
        config.setSessionTimeoutMilliseconds(configItem.getSessionTimeoutMilliseconds());
        config.setConnectionTimeoutMilliseconds(configItem.getConnectionTimeoutMilliseconds());
        EJZkRegistryCenterProxy proxy = new EJZkRegistryCenterProxy(config);
        return proxy;
    }

    /**
     * 作业核心配置
     *
     * @return
     */
    public JobCoreConfiguration createEJJobCoreConfiguration(String taskJobJson) {
        if (Strings.isNullOrEmpty(taskJobJson)) {
            logger.error(" taskJobJson is empty ");
            return null;
        }
        JobCoreConfiguration jobCoreConfiguration = null;
        try {
            APECJobCoreConfigItem jobCoreConfigItem = objectMapper().readValue(taskJobJson, APECJobCoreConfigItem.class);
            jobCoreConfiguration = JobCoreConfiguration.newBuilder(jobCoreConfigItem.getJobName(),
                    jobCoreConfigItem.getCron(), jobCoreConfigItem.getShardingTotalCount())
                    .shardingItemParameters(jobCoreConfigItem.getShardingItemParameters())
                    .jobParameter(jobCoreConfigItem.getJobParameter())
                    .failover(jobCoreConfigItem.isFailover())
                    .misfire(jobCoreConfigItem.isMisfire())
                    .description(jobCoreConfigItem.getDescription())
                    .build();
        } catch (JsonParseException e) {
            logger.error("JsonParse Exception:{}", e);
            return null;
        } catch (JsonMappingException e) {
            logger.error("JsonMapping Exception:{}", e);
            return null;
        } catch (IOException e) {
            logger.error("Json Exception:{}", e);
            return null;
        }
        return jobCoreConfiguration;
    }

    /**
     * 根据传入作业实体对象，判断是什么类型作业，生成LiteJobConfiguration
     *
     * @param jobObject
     * @param jobShardingStrategyClass
     * @return
     */
    public LiteJobConfiguration createEJLiteJobConfiguration(ElasticJob jobObject, JobCoreConfiguration jobCoreConfiguration,
                                                             String jobShardingStrategyClass, Boolean streamingProcess, String scrptCommandLine) {
        Class<?> clazz = jobObject.getClass();
        Class<?> interfaces[] = clazz.getInterfaces();
        JobTypeConfiguration jobTypeConfiguration = null;
        for (Class<?> jobInterface : interfaces) {
            if (jobInterface.getSimpleName().equals("DataflowJob")) {
                //默认非流式处理
                jobTypeConfiguration = new DataflowJobConfiguration(jobCoreConfiguration,
                        jobObject.getClass().getCanonicalName(), streamingProcess);
            }
            if (jobInterface.getSimpleName().equals("SimpleJob")) {
                jobTypeConfiguration = new SimpleJobConfiguration(jobCoreConfiguration,
                        jobObject.getClass().getCanonicalName());
            }
            if (jobInterface.getSimpleName().equals("ScriptJob")) {
                jobTypeConfiguration = new ScriptJobConfiguration(jobCoreConfiguration, scrptCommandLine);
            }
        }
        return LiteJobConfiguration.newBuilder(jobTypeConfiguration).jobShardingStrategyClass(jobShardingStrategyClass).overwrite(true).build();
    }

    /**
     * 简单任务
     *
     * @param proxy
     * @param taskJobJson
     * @param jobObject
     * @param jobShardingStrategyClass
     * @return
     */
    public JobScheduler createEJSimpleJobScheduler(EJZkRegistryCenterProxy proxy, String taskJobJson, ElasticJob jobObject, String jobShardingStrategyClass) {
        return new JobScheduler(proxy, createEJLiteJobConfiguration(jobObject, createEJJobCoreConfiguration(taskJobJson), jobShardingStrategyClass, null, null));
    }

    /**
     * 数据流任务
     *
     * @param proxy
     * @param taskJobJson
     * @param jobObject
     * @param jobShardingStrategyClass
     * @param streamingProcess
     * @return
     */
    public JobScheduler createEJDataflowJobScheduler(EJZkRegistryCenterProxy proxy, String taskJobJson, ElasticJob jobObject, String jobShardingStrategyClass, Boolean streamingProcess) {
        return new JobScheduler(proxy, createEJLiteJobConfiguration(jobObject, createEJJobCoreConfiguration(taskJobJson), jobShardingStrategyClass, streamingProcess, null));
    }

    /**
     * 脚本任务
     *
     * @param proxy
     * @param taskJobJson
     * @param jobObject
     * @param jobShardingStrategyClass
     * @param scrptCommandLine
     * @return
     */
    public JobScheduler createEJScriptJobJobScheduler(EJZkRegistryCenterProxy proxy, String taskJobJson, ElasticJob jobObject, String jobShardingStrategyClass, String scrptCommandLine) {
        return new JobScheduler(proxy, createEJLiteJobConfiguration(jobObject, createEJJobCoreConfiguration(taskJobJson), jobShardingStrategyClass, null, scrptCommandLine));
    }
}
