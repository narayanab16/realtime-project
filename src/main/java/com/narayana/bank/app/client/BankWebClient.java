package com.narayana.bank.app.client;

import com.narayana.bank.app.server.common.AccountStatusType;
import com.narayana.bank.app.server.common.AccountType;
import com.narayana.bank.app.server.model.Account;
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


    private static void createaBranch(BankWebClient client) {
        BankBranch b1 = new BankBranch("BR0004", "SVG Bank", "Chitoor", "Andhra Pradesh", "India");
        Mono<BankBranch> branch = client.createBranch(b1);
        System.out.println("Got branch 1 : " + branch.block());
    }

    private static void createaCustomer(BankWebClient client) {
        Customer customer = new Customer(null, "Narayana", "", "Basetty", "Bengaluru", "1234567892", "IT-Software", LocalDate.now());
        Mono<Customer> customerMono = client.registerCustomer(customer);
        System.out.println("Got customerMono : " + customerMono.block());
    }

    private static void createaAccount(BankWebClient client) {
        // Create a Customer and then create account
        BankBranch b1 = new BankBranch("BR0001", "SVG Bank", "Nagari", "Andhra Pradesh", "India");
        Customer c1 = new Customer(null, "Narayana", "", "Basetty", "Bengaluru", "1234567892", "IT-Software", LocalDate.now());
        Account account = new Account();
        account.setCustomer(c1);
        account.setBankBranches(Arrays.asList(b1));
        account.setAccountStatus(AccountStatusType.ACTIVE.name());
        account.setAccountType(AccountType.SAVINGS.name());
        account.setOpeningBalance(501.00);
        account.setAccountOpenDate(LocalDateTime.now());
        Mono<Account> resAccount = client.createAccount(account);
        System.out.println(" Account Created " + resAccount.block());
    }

    public static void main(String[] args) {
        WebClient webClient =
                WebClient.builder().baseUrl("http://localhost:10000/bank-app").build();
        BankWebClient client = new BankWebClient(webClient);
        // App flows
        createaAccount(client);
//        createaBranch(client);
//        createaCustomer(client);
    }


}
