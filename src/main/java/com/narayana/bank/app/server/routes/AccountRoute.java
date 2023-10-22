package com.narayana.bank.app.server.routes;

import com.narayana.bank.app.server.handlers.AccountHandler;
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
public class AccountRoute {
    private static Logger LOG = LoggerFactory.getLogger(AccountRoute.class);
    @Bean
    public RouterFunction<ServerResponse> getAccount(AccountHandler accountHandler) {
        LOG.info("getAccount");
        return RouterFunctions.route(
                GET("/getAccountById/{id}").and(accept(MediaType.APPLICATION_JSON)), accountHandler::getAccount
        );
    }

    @Bean
    public RouterFunction<ServerResponse> createAccount(AccountHandler accountHandler) {
        LOG.info("createAccount");
        return RouterFunctions.route(
                POST("/createAccount").and(accept(MediaType.APPLICATION_JSON)), accountHandler::createAccount
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getAccounts(AccountHandler accountHandler) {
        LOG.info("getAccounts");
        return RouterFunctions.route(
                GET("/getAllAccounts").and(accept(MediaType.APPLICATION_JSON)), accountHandler::getAccounts
        );
    }
}
