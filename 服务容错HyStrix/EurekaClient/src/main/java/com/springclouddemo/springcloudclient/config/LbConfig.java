package com.springclouddemo.springcloudclient.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RandomRule;
import com.springclouddemo.springcloudclient.common.MyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

/*
配置类，设置规则仅仅对ServerProvide服务有效，
 */
/*
@RibbonClient(name="ServerProvide", configuration=LbConfig.class)
public class LbConfig {
    @Bean
    public IRule getRule(){
        return new MyRule();
    }
}
 */

public class LbConfig
{
    @Autowired
    private IClientConfig clientConfig;

    @Bean
    public IRule myRule(IClientConfig config) {
        return new RandomRule();
        // return new RoundRobinRule();
        // return new RetryRule();
        // return new WeightedResponseTimeRule();
        // return new BestAvailableRule();
        // return new AvailabilityFilteringRule();
        // return new ZoneAvoidanceRule();
    }

    @Bean
    public IPing myPing(IClientConfig config) {
        return new PingUrl(false, "/Provide/");
    }
}
