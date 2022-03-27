package com.ycz.service.impl;/*
 @author ycz
 @date 2022-03-05-20:37
*/

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycz.eneity.Disribution;
import com.ycz.mapper.DisributionMapper;
import com.ycz.service.DisributionService;
import org.springframework.stereotype.Service;

@Service
public class DisributionServiceImpl
extends ServiceImpl<DisributionMapper, Disribution>
    implements DisributionService
{


    @Override
    public void getAndSave(Disribution disribution) {
        this.save(disribution);
    }
}
