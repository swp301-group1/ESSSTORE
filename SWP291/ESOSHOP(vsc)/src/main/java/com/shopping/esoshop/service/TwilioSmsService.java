package com.shopping.esoshop.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService {
    
    public void sendSms(String to, String message) {
        Twilio.init("AC5eadfed8218c1255fd07246492a07d3c", "413644778193ac5ec27b0012ab1ac70b");
        
        Message.creator(
            new com.twilio.type.PhoneNumber(to),
            new com.twilio.type.PhoneNumber("+12512203179"),
            message
        ).create();
    }
}
