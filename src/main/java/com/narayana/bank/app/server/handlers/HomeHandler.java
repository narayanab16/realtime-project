package com.narayana.bank.app.server.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HomeHandler {
    private static Logger LOG = LoggerFactory.getLogger(HomeHandler.class);
    public HomeHandler() {
    }

    public ServerResponse welcomeHome(ServerRequest req) {
        LOG.info("welcomeHome ");
        Mono<String> welcomeText = Mono.just("Welcome to Bank Application!! \nHow to setup?, Please refer README.md");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(welcomeText);
    }

}
