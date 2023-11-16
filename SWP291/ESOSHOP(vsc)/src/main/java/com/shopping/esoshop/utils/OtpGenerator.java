/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.esoshop.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Repository;

@Repository
public class OtpGenerator {

    public String createCapcha() {
        int otpLength = 6;
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            int digit = random.nextInt(10);
            otp.append(digit);
        }
        return otp.toString();
    }

    public String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        int minLength = 8;
        int maxLength = 13;
        StringBuilder password = new StringBuilder();
        password.append((char) (random.nextInt(26) + 'A'));
        password.append((char) (random.nextInt(10) + '0'));
        while (password.length() < minLength) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            password.append(randomChar);
        }
        for (int i = 0; i < maxLength - minLength; i++) {
            int randomIndex = random.nextInt(password.length());
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            password.insert(randomIndex, randomChar);
        }
        return password.toString();
    }

}
