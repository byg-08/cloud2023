package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfoGlobalHandler")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/comsumer/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id){

        String result=paymentHystrixService.paymentInfoOK(id);
        log.info("*********result:"+result);
        return result;
    }

    @GetMapping("/comsumer/payment/hystrix/timeOut/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "10000")
//    })
    @HystrixCommand
    public String paymentInfoTimeOut(@PathVariable("id") Integer id){
        String result=paymentHystrixService.paymentInfoTimeOut(id);
        log.info("*********result:"+result);
        return result;
    }

    public String paymentInfoTimeOutHandler(Integer id){

        return "线程池" + Thread.currentThread().getName() + "80系统繁忙：paymentInfoTimeOut " + id + "/t 哈哈 paymentInfoTimeOutHandler********";
    }


    public String paymentInfoGlobalHandler(){

        return "******全局异常处理，请稍后再试";
    }
}
