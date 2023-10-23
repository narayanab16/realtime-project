package com.narayana.bank.app.server.routes;

import com.narayana.bank.app.server.handlers.BankBranchHandler;
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
public class BankBranchRoute {
    private static Logger LOG = LoggerFactory.getLogger(BankBranchRoute.class);
    @Bean
    public RouterFunction<ServerResponse> getBranchById(BankBranchHandler bankBranchHandler) {
        LOG.info("getBranchById");
        return RouterFunctions.route(
                GET("/getBranchById/{id}").and(accept(MediaType.APPLICATION_JSON)), bankBranchHandler::getBranchById
        );
    }

    @Bean
    public RouterFunction<ServerResponse> createBranch(BankBranchHandler bankBranchHandler) {
        LOG.info("createBranch");
        return RouterFunctions.route(
                POST("/createBranch").and(accept(MediaType.APPLICATION_JSON)), bankBranchHandler::createBranch
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getAllBranches(BankBranchHandler bankBranchHandler) {
        LOG.info("getAllBranches");
        return RouterFunctions.route(
                GET("/getAllBranches").and(accept(MediaType.APPLICATION_JSON)), bankBranchHandler::getAllBranches
        );
    }
}
