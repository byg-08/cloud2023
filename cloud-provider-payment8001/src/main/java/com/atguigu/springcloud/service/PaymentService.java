package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.PayMent;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(PayMent payMent);

    public PayMent getPayMentById(@Param("id") Long id);
}
