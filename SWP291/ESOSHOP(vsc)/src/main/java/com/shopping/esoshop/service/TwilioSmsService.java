package com.shopping.esoshop.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
@PropertySource("classpath:application.properties")
public class TwilioSmsService {

    @Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public boolean sendSms(String to, String message) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        try {
            Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(twilioPhoneNumber),
                    message)
                .create();
            return true; // Return true if sending was successful
        } catch (Exception e) {
           System.out.println("can not send "+to); // Print the error for debugging purposes
            return false; // Return false if there was an error in sending
        }
    }
    
}

