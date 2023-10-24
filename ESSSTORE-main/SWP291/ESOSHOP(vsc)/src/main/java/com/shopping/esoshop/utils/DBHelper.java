package com.shopping.esoshop.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

@Repository
@Configuration
@PropertySource("classpath:application.properties")
public class DBHelper implements Serializable {


    @Value("${spring.datasource.driverClassName}")
    public  String driverClassName;

    @Value("${spring.datasource.url}")
    public  String url;

    @Value("${spring.datasource.username}")
    public  String username;

    @Value("${spring.datasource.password}")
    public  String password;

    public  Connection makeConnection() {
        try {
            Class.forName(driverClassName);
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (Exception e) {
            System.out.println("Cannot connect to the database");
			System.out.println(driverClassName+"-"+username);
        }
        return null;
    }
}
