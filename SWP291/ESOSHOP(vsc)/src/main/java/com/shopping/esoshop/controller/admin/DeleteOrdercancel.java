package com.shopping.esoshop.controller.admin;


import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shopping.esoshop.service.DaoService;

@Component
public class DeleteOrdercancel {
    @Autowired
    private DaoService daoService;
    @Scheduled(cron = "0 0 0 */4 * *")
    public void reportCurrentTime() {
        LocalDate time = LocalDate.now().minusDays(4);
       System.out.println(daoService.deleteOrderCancelAfterTime(Date.valueOf(time)));
    }
}
