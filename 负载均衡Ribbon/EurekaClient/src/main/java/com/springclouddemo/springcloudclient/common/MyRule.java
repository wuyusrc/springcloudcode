package com.springclouddemo.springcloudclient.common;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/*
自定义负载均衡器的策略
 */
public class MyRule implements IRule {
    private ILoadBalancer lb;// 声明负载均衡器的接口

    /*
    这里主要实现了自定义分配请求的逻辑
     */
    @Override
    public Server choose(Object key) {
        // 获取服务器列表
        List<Server> servers = lb.getAllServers();
        Random r = new Random();
        //生成随机数
        int rand = r.nextInt(10);
        //这里设置自定义的负载均衡策略，如果随机数大于6，则转发到端口为40001的服务提供者上，反之则转发到端口为40002的服务提供者上
        //这里仅仅做模拟，后续需要根据业务逻辑重新实现
        if(rand > 6){
            return getServerByPort(servers, 40001);
        }else{
            return getServerByPort(servers, 40002);
        }
    }

    private Server getServerByPort(List<Server> servers, int port){
        for(Server s : servers){
            if(s.getPort() == port){
                return s;
            }
        }
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.lb;
    }
}
