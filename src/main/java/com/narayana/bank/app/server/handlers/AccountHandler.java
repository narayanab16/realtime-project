package com.narayana.bank.app.server.handlers;

import com.narayana.bank.app.server.exception.BankappBusinessException;
import com.narayana.bank.app.server.model.Account;
import com.narayana.bank.app.server.service.AccountService;
import jakarta.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;

@Component
public class AccountHandler {
    private static Logger LOG = LoggerFactory.getLogger(AccountHandler.class);
    private AccountService accountService;

    public AccountHandler(AccountService accountService) {
        this.accountService =  accountService;
    }

    public ServerResponse getAccount(ServerRequest req) {
        LOG.info("getAccount ");
        String id = req.pathVariable("id");
        Mono<Account> account = accountService.getAccount(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(account);
    }

    public ServerResponse createAccount(ServerRequest req) throws ServletException, IOException, BankappBusinessException {
        Account account = req.body(Account.class);
        Mono<Account> accountAdded = accountService.createAccount(account);
        return ServerResponse.created(URI.create("/createAccount")).contentType(MediaType.APPLICATION_JSON)
                .body(accountAdded);
    }

    public ServerResponse getAccounts(ServerRequest req) throws BankappBusinessException {
        LOG.info("getAccounts");
        Flux<Account> accounts = accountService.getAllAccounts();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                //.body(BodyInserters.fromValue(users));
                .body(accounts);
    }
}
