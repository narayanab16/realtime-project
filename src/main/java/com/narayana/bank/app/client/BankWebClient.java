package com.narayana.bank.app.client;

import com.narayana.bank.app.server.common.AccountStatusType;
import com.narayana.bank.app.server.common.AccountTransactionType;
import com.narayana.bank.app.server.common.AccountType;
import com.narayana.bank.app.server.common.TransactionModeType;
import com.narayana.bank.app.server.model.Account;
import com.narayana.bank.app.server.model.AccountTransaction;
import com.narayana.bank.app.server.model.BankBranch;
import com.narayana.bank.app.server.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class BankWebClient {
    public WebClient webClient;

    public BankWebClient(WebClient webClient) {
        this.webClient = webClient;
    }
    public Mono<Customer> registerCustomer(Customer customer) {
        return this.webClient.post().uri("/registerCustomer").accept(MediaType.APPLICATION_JSON)
                .bodyValue(customer)
                .exchangeToMono(x ->
                        x.statusCode().equals(HttpStatus.CREATED) ?
                                x.bodyToMono(Customer.class) :
                                Mono.justOrEmpty(null)
                );
    }

    public Mono<BankBranch> createBranch(BankBranch branch) {
        return this.webClient.post().uri("/createBranch").accept(MediaType.APPLICATION_JSON)
                .bodyValue(branch)
                .exchangeToMono(x ->
                        x.statusCode().equals(HttpStatus.CREATED) ?
                                x.bodyToMono(BankBranch.class) :
                                Mono.justOrEmpty(null)
                );
    }

    public Mono<Account> createAccount(Account account) {
        return this.webClient.post().uri("/createAccount").accept(MediaType.APPLICATION_JSON)
                .bodyValue(account)
                .exchangeToMono(x ->
                        x.statusCode().equals(HttpStatus.CREATED) ?
                                x.bodyToMono(Account.class) :
                                Mono.justOrEmpty(null)
                );
    }

    public Mono<AccountTransaction> createAccountTrxn(AccountTransaction accountTrxn) {
        return this.webClient.post().uri("/createAccountTrxn").accept(MediaType.APPLICATION_JSON)
                .bodyValue(accountTrxn)
                .exchangeToMono(x ->
                        x.statusCode().equals(HttpStatus.CREATED) ?
                                x.bodyToMono(AccountTransaction.class) :
                                Mono.justOrEmpty(null)
                );
    }


    private static void createaBranch(BankWebClient client) {
        BankBranch b1 = new BankBranch("BR0004", "SVG Bank", "Chittoor", "Andhra Pradesh", "India");
        Mono<BankBranch> branch = client.createBranch(b1);
        System.out.println("New branch added to the Database table : " + branch.block());
    }

    private static void createaCustomer(BankWebClient client) {
        Customer customer = new Customer(null, "Narayana", "", "Basetty", "Bengaluru", "1234567892", "IT-Software", LocalDate.now());
        Mono<Customer> customerMono = client.registerCustomer(customer);
        System.out.println("Got customer Id : " + customerMono.block().getId());
    }

    private static String createaAccount(BankWebClient client) {
        // Create a Customer and then create account
        BankBranch b1 = new BankBranch("BR0002", "SVG Bank", "Nagalapuram", "Andhra Pradesh", "India");
        Customer c1 = new Customer(null, "Narayana", "", "Basetty", "Bengaluru", "1234567892", "IT-Software", LocalDate.now());
        Account account = new Account();
        account.setCustomer(c1);
        account.setBankBranch(b1);
        account.setAccountStatus(AccountStatusType.ACTIVE.name());
        account.setAccountType(AccountType.SAVINGS.name());
        account.setOpeningBalance(501.70);
        account.setAccountOpenDate(LocalDateTime.now());
        Mono<Account> resAccount = client.createAccount(account);
        Account _resAccount = resAccount.block();
        System.out.println(" Account Created : " + _resAccount);
        System.out.println(" Account ID : " + _resAccount.getId());
        return _resAccount.getId();
    }
    private static String createaAccount2(BankWebClient client) {
        // Create a Customer and then create account
        BankBranch b1 = new BankBranch("BR0001", "SVG Bank", "Nagari", "Andhra Pradesh", "India");
        Customer c1 = new Customer(null, "Narayana", "", "Basetty", "Bengaluru", "1234567892", "IT-Software", LocalDate.now());
        Account account = new Account();
        account.setCustomer(c1);
        account.setBankBranch(b1);
        account.setAccountStatus(AccountStatusType.ACTIVE.name());
        account.setAccountType(AccountType.SAVINGS.name());
        account.setOpeningBalance(700.00);
        account.setAccountOpenDate(LocalDateTime.now());
        Mono<Account> resAccount = client.createAccount(account);
        Account _resAccount = resAccount.block();
        System.out.println(" Account Created : " + _resAccount);
        System.out.println(" Account ID : " + _resAccount.getId());
        return _resAccount.getId();
    }

    private static void createaAccountTrxn(BankWebClient client, String accountId) {
        // Create a Customer and then create account
        AccountTransaction accountTrxn = new AccountTransaction();
        accountTrxn.setAccountId(accountId);
        accountTrxn.setModeOfTransaction(TransactionModeType.CASH.name());
        accountTrxn.setTransactionType(AccountTransactionType.WITHDRAWAL.name());
        accountTrxn.setTransactionAmount(20.50);
        Mono<AccountTransaction> resAccountTrxn = client.createAccountTrxn(accountTrxn);
        AccountTransaction at = resAccountTrxn.block();
        System.out.println(" AccountTransaction Created : " + at);
        System.out.println(" AccountTransaction ID : " + at.getId());
    }

    public static void main(String[] args) {
        WebClient webClient =
                WebClient.builder().baseUrl("http://localhost:10000/bank-app").build();
        BankWebClient client = new BankWebClient(webClient);
        // App flows
        // 1st call - create a customer and add new account number, note it for transaction
        String accountId = createaAccount(client);
        String accountId2 = createaAccount2(client);
        // 2nd call - update account id/number then call for trxn details
        //createaAccountTrxn(client, accountId);
        // Independent way works to create branch and customer data
        //createaBranch(client);
        //createaCustomer(client);
    }


}
