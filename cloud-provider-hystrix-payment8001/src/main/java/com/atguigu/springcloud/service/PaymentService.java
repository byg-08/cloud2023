package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfoOK(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_ok " + id + "/t 哈哈";
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfoTimeOut(Integer id) {
        int timeNumber = 3;
//        int age=10/0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + "paymentInfoTimeOut " + id + "/t 哈哈 超时" + timeNumber + "秒";
    }

    public String paymentInfoTimeOutHandler(Integer id){

        return "线程池" + Thread.currentThread().getName() + "8001系统繁忙：paymentInfoTimeOut " + id + "/t 哈哈 paymentInfoTimeOutHandler********";
    }
}
