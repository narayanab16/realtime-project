package com.narayana.bank.app.server.common.helper;

import java.util.Random;

public class BankappHelper {
    private static final long SEED = System.currentTimeMillis();

    private static String getRandomNumber() {
        int number = new Random(SEED).nextInt(99999999) + new Random().nextInt(3);
        return String.format("%08d", Math.abs(number));
    }

    public static String getCustomerId() {
        return "C-" + getRandomNumber();
    }

    public static String getAccountNumber() {
        return getRandomNumber();
    }

    public static String getTrxnNumber() {
        return "TRXN-" + getRandomNumber();
    }

    public static void main(String[] args) {
        System.out.println(" " + getCustomerId());
        System.out.println(" " + getAccountNumber());
        System.out.println(" " + getTrxnNumber());
    }
}
