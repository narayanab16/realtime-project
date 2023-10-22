package com.narayana.bank.app.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is a spring boot web reactive microservice to handle bank aplication
 * (customer registration, account number generation, branch, transaction record)
 *
 * @author : Narayana Basetty
 * @created on : Oct/22/2023
 */

@SpringBootApplication
public class BankApplication {
    private static Logger LOG = LoggerFactory.getLogger(BankApplication.class);

    public static void main(String[] args) {
        LOG.info("BankApplication starting.....");
        SpringApplication.run(BankApplication.class, args);
        LOG.info("BankApplication started successfully!!!");
    }
}