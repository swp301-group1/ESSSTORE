package com.shopping.esoshop.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shopping.esoshop.service2.IDaoService;

@Component
public class DeleteOrdercancel {
    @Autowired
    private IDaoService daoService;

    // system will delete all order cancel each 1day
    @Scheduled(cron = "0 0 0 * * *")
    public void reportCurrentTime() {
       System.out.println(daoService.deleteOrderCancelAfterTime());
    }
}
