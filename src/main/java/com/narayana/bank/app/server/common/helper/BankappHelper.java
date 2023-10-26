package com.narayana.bank.app.server.common.helper;

import java.util.Random;

public class BankappHelper {
    private static final long SEED = System.currentTimeMillis();
    // Some random numbers to make unique numbers for account number, customer id, account tx
    private static final int[] arr  = {
            -151, 255, -121, -7, -127, 431, 0, 1, 2, 3, 4, -111, 331, 221, 991,
            10, 46, 51, 8888, 22222, 666, 5, 9, 100, 200, 300, 99, -99,-77, 22,
            55, 5000, 1000, -7777, 1001, 337, 441};

    private static String getRandomNumber() {
        int number = new Random(SEED).nextInt(99999999) + arr[new Random().nextInt(31)];
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
