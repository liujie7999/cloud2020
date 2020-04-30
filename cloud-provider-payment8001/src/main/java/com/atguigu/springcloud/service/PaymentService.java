package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

    List<Payment> query();
}
