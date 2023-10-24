package com.narayana.bank.app.server.routes;

import com.narayana.bank.app.server.handlers.HomeHandler;
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
public class HomeRoute {
    private static Logger LOG = LoggerFactory.getLogger(HomeRoute.class);
    @Bean
    public RouterFunction<ServerResponse> home(HomeHandler homeHandler) {
        LOG.info("home");
        return RouterFunctions.route(
                GET("/").and(accept(MediaType.APPLICATION_JSON)), homeHandler::welcomeHome
        );
    }
}
