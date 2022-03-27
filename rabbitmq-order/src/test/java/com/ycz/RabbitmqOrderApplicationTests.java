package com.ycz;

import com.ycz.entity.Order;
import com.ycz.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootTest
class RabbitmqOrderApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {

        orderService.saveOrderAndMessage();



    }


}
