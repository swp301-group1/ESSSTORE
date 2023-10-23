package com.shopping.esoshop.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    @Autowired 
    private TwilioSmsService twilioSmsService;
    

    @GetMapping("/send-sms")
    public String sendSms() {
        twilioSmsService.sendSms("+84339805402", "hello");
        return "SMS sent!";
    }
}

