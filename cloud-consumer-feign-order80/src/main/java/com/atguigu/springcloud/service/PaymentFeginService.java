package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.PayMent;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeginService {
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<PayMent>  getPayMentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/fegin/timeout")
     public String  paymentFeignTimeOut() ;
}
