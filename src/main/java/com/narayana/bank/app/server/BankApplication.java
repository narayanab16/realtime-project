package com.narayana.bank.app.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is a spring boot web reactive microservice to handle bank aplication.
 * The following activities are performed.
 *
 * 1. Customer registration
 * 2. Add new branches to the system
 * 3. Account number generation to the customer, and link account number to the specific branch,
 * 4. Capture all the transaction records link to the specific account
 * 5. Get the account details
 * 6. etc.
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