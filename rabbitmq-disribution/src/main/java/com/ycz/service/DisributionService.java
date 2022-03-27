package com.ycz.service;/*
 @author ycz
 @date 2022-03-05-20:36
*/

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycz.eneity.Disribution;

public interface DisributionService
extends IService<Disribution>
{
    void getAndSave(Disribution disribution);
}
