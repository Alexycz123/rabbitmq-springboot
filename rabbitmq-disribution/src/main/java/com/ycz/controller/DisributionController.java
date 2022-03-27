package com.ycz.controller;/*
 @author ycz
 @date 2022-03-05-20:37
*/


import com.ycz.eneity.Disribution;
import com.ycz.eneity.Order;
import com.ycz.service.DisributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dis")
public class DisributionController {

    @Autowired
    private DisributionService disributionService;

    @RequestMapping("get")
    public Object get(){
        return disributionService.list();
    }

    @RequestMapping("/getOrder")
    public Object getOrder(@RequestBody Order order){
        System.out.println(order);
        Disribution disribution=new Disribution();

        disribution.setOrderId(order.getId());
        disribution.setCreateTime(new Date());
        disribution.setRiderId(2);

        disributionService.getAndSave(disribution);


        return order;
    }


}
