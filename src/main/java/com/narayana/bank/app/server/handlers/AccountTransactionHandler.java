package com.narayana.bank.app.server.handlers;

import com.narayana.bank.app.server.exception.BankappBusinessException;
import com.narayana.bank.app.server.model.Account;
import com.narayana.bank.app.server.model.AccountTransaction;
import com.narayana.bank.app.server.service.AccountService;
import com.narayana.bank.app.server.service.AccountTransactionService;
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
public class AccountTransactionHandler {
    private static Logger LOG = LoggerFactory.getLogger(AccountTransactionHandler.class);
    private AccountTransactionService accountTrxnService;

    public AccountTransactionHandler(AccountTransactionService accountTrxnService) {
        this.accountTrxnService =  accountTrxnService;
    }

    public ServerResponse getAccountTrxn(ServerRequest req) {
        LOG.info("getAccountTrxn ");
        String id = req.pathVariable("id");
        Mono<AccountTransaction> accountTrxn = accountTrxnService.getAccountTrxnById(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(accountTrxn);
    }

    public ServerResponse createAccountTrxn(ServerRequest req) throws ServletException, IOException, BankappBusinessException {
        AccountTransaction accountTrxn = req.body(AccountTransaction.class);
        Mono<AccountTransaction> accountTrxnAdded = accountTrxnService.createAccountTrxn(accountTrxn);
        return ServerResponse.created(URI.create("/createAccountTrxn")).contentType(MediaType.APPLICATION_JSON)
                .body(accountTrxnAdded);
    }

    public ServerResponse getAccountTrxns(ServerRequest req) throws BankappBusinessException {
        LOG.info("getAccountTrxns");
        Flux<AccountTransaction> accounts = accountTrxnService.getAllAccountTrxns();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(accounts);
    }
}
