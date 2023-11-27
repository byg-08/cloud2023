package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.PayMent;
import com.atguigu.springcloud.service.PaymentService;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.TimeOnlyTypeHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/test")
    public CommonResult test(@RequestParam Long id) {
        PayMent payMent = paymentService.getPayMentById(id);
        log.info("****查询：" + payMent);
        if (null != payMent) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payMent);
        } else {
            return new CommonResult(444, "没有对应记录，查询id：" + id, null);
        }
    }

    @PostMapping(value = "/payment/create")
    public CommonResult<PayMent> creat(@RequestBody PayMent payment) {
        int result = paymentService.create(payment);
        log.info("****插入结果：" + result + "\t" + "哈哈");
        if (result > 0) {
            return new CommonResult(200, "成功", result);
        } else {
            return new CommonResult(444, "失败", null);
        }
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPayMentById(@PathVariable Long id) {
        PayMent payMent = paymentService.getPayMentById(id);
        log.info("****查询4444444：" + payMent);
        if (null != payMent) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payMent);
        } else {
            return new CommonResult(444, "没有对应记录，查询id：" + id, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/fegin/timeout")
    public String paymentFeignTimeOut() {

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
