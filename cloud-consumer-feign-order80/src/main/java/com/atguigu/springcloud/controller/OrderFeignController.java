package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.PayMent;
import lombok.extern.slf4j.Slf4j;
import com.atguigu.springcloud.service.PaymentFeginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeginService paymentFeginService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<PayMent> getPayMentById(@PathVariable("id") Long id) {

        return paymentFeginService.getPayMentById(id);
    }

    @GetMapping(value = "/consumer/payment/fegin/timeout")
    public String paymentFeignTimeOut(){
        return paymentFeginService.paymentFeignTimeOut();
    }
}
