package com.narayana.bank.app.server.routes;

import com.narayana.bank.app.server.handlers.AccountHandler;
import com.narayana.bank.app.server.handlers.AccountTransactionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class AccountTransactionRoute {
    private static Logger LOG = LoggerFactory.getLogger(AccountTransactionRoute.class);
    @Bean
    public RouterFunction<ServerResponse> getAccountTrxn(AccountTransactionHandler accountTrxnHandler) {
        LOG.info("getAccountTrxn");
        return RouterFunctions.route(
                GET("/getAccountTrxnById/{id}").and(accept(MediaType.APPLICATION_JSON)), accountTrxnHandler::getAccountTrxn
        );
    }

    @Bean
    public RouterFunction<ServerResponse> createAccountTrxn(AccountTransactionHandler accountTrxnHandler) {
        LOG.info("createAccountTrxn");
        return RouterFunctions.route(
                POST("/createAccountTrxn").and(accept(MediaType.APPLICATION_JSON)), accountTrxnHandler::createAccountTrxn
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getAccountTrxns(AccountTransactionHandler accountTrxnHandler) {
        LOG.info("getAccountTrxns");
        return RouterFunctions.route(
                GET("/getAllAccountTrxns").and(accept(MediaType.APPLICATION_JSON)), accountTrxnHandler::getAccountTrxns
        );
    }
}
