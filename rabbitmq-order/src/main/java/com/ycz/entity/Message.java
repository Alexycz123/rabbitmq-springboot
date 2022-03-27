package com.ycz.entity;/*
 @author ycz
 @date 2022-03-06-0:08
*/

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "t_message")
public class Message {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer orderId;

    private Integer status;

    private Date createTime;


}
