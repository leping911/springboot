package com.zlp.springboot.config;

/**
 * Created by lenovo on 2017/7/5.
 */

import com.zlp.springboot.properties.RestTemplateProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

@Configuration
public class RestConfiguration {
    @Autowired
    private RestTemplateProperties restTemplateProperties;

    @Bean
    public SimpleClientHttpRequestFactory httpClientFactory() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(restTemplateProperties.getReadTimeout());
        httpRequestFactory.setConnectTimeout(restTemplateProperties.getConnectTimeout());

        if(restTemplateProperties.getEnabled()){
            SocketAddress address = new InetSocketAddress(restTemplateProperties.getHost(),restTemplateProperties.getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
            httpRequestFactory.setProxy(proxy);
        }

        return httpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory httpClientFactory) {
        RestTemplate restTemplate = new RestTemplate(httpClientFactory);
        return restTemplate;
    }
}
