package com.ycz.entity;/*
 @author ycz
 @date 2022-03-04-13:44
*/

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_order")
public class Order implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "consumer_id")
    private Integer consumerId;
    @TableField(value = "product_id")
    private Integer productId;
    @TableField(value = "num")
    private Integer num;
    @TableField(value = "total_price")
    private Double totalPrice;

    @TableField(value = "create_time")
    private Date createTime;



}
