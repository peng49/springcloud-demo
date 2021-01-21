package com.github.peng49.springclouddemo;


import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import org.assertj.core.util.Lists;
import rx.Observable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RibbonDemo {
    public static void main(String[] args) {
        ArrayList<Server> servers = Lists.newArrayList(new Server("localhost", 9200), new Server("localhost", 9201));

        ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(servers);

        for (int i = 0; i < 5; i++) {
            String result = LoadBalancerCommand.<String>builder().withLoadBalancer(loadBalancer).build()
                    .submit(new ServerOperation<String>() {
                        public Observable<String> call(Server server) {
                            try {
                                String addr = "http://" + server.getHost() + ":" + server.getPort() + "/product/list";
                                System.out.println(" 调用地址：" + addr);
                                URL url = new URL(addr);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setRequestMethod("GET");
                                conn.connect();
                                InputStream in = conn.getInputStream();
                                byte[] data = new byte[in.available()];
                                in.read(data);
                                return Observable.just(new String(data));
                            } catch (Exception e) {
                                return Observable.error(e);
                            }
                        }
                    }).toBlocking().first();
            System.out.println(" 调用结果：" + result);
        }
    }
}
