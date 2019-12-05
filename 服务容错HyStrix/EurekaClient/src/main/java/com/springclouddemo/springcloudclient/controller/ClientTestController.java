package com.springclouddemo.springcloudclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class ClientTestController {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @HystrixCommand(fallbackMethod = "getFailure")
    @RequestMapping("/getTestMsg")
    public String getTestMsg(){
        RestTemplate rt = getRestTemplate();
        String result = rt.getForObject("http://ServerProvide/ServerTest", String.class);
        return result;
    }

    public String getFailure() {
        String Msg = "网络繁忙，请稍后再试。（这里是服务调用者的快速失败给出的提示）";
        return Msg;
    }
}
