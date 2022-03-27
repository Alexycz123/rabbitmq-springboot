package com.ycz.eneity;/*
 @author ycz
 @date 2022-03-05-20:34
*/


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disribution {


    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer orderId;

    private Integer riderId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;



}
