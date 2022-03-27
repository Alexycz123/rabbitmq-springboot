package com.ycz;

import com.ycz.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqProducerApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
        orderService.makeOrder2("1","1",12);
    }

    @Test
    void contextLoads2() {
        orderService.makeOrder3("1","1",12);
    }

    @Test
    void contextLoads3() {
        orderService.makeOrderTTlMessage("1","1",12);
    }

}
