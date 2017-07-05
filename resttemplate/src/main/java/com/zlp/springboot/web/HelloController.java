package com.zlp.springboot.web;

import com.zlp.springboot.entity.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lenovo on 2017/7/5.
 */
@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/index")
    public Quote index(){
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        return quote;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
