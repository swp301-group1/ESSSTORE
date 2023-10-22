package com.shopping.esoshop.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMSService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    @GetMapping("/send-sms")
    public String sendSMS() {
        try {
             Twilio.init(accountSid, authToken);

        Message message = Message.creator(
                new PhoneNumber("+84974841508"),
                new PhoneNumber(twilioPhoneNumber),
                "hello"
        ).create();
        message.getSid();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "Sent message with SID: ";
    }
}
