package com.narayana.bank.app.server.routes;

import com.narayana.bank.app.server.handlers.CustomerHandler;
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
public class CustomerRoute {
    private static Logger LOG = LoggerFactory.getLogger(CustomerRoute.class);
    @Bean
    public RouterFunction<ServerResponse> getCustomer(CustomerHandler customerHandler) {
        LOG.info("getCustomerById: ");
        return RouterFunctions.route(
                GET("/getCustomerById/{id}").and(accept(MediaType.APPLICATION_JSON)), customerHandler::getCustomer
        );
    }

    @Bean
    public RouterFunction<ServerResponse> registerCustomer(CustomerHandler customerHandler) {
        LOG.info("registerCustomer");
        return RouterFunctions.route(
                POST("/registerCustomer").and(accept(MediaType.APPLICATION_JSON)), customerHandler::registerCustomer
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getAllCustomers(CustomerHandler customerHandler) {
        LOG.info("getAllCustomers");
        return RouterFunctions.route(
                GET("/getAllCustomers").and(accept(MediaType.APPLICATION_JSON)), customerHandler::getCustomers
        );
    }
}
