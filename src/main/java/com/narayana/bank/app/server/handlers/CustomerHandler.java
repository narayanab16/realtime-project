package com.narayana.bank.app.server.handlers;

import com.narayana.bank.app.server.exception.BankappBusinessException;
import com.narayana.bank.app.server.model.Customer;
import com.narayana.bank.app.server.service.CustomerService;
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
public class CustomerHandler {
    private static Logger LOG = LoggerFactory.getLogger(CustomerHandler.class);

    private CustomerService customerService;

    public CustomerHandler(CustomerService customerService) {
        this.customerService = customerService;
    }

    public ServerResponse getCustomer(ServerRequest req) {
        LOG.info("getCustomer ");
        String id = req.pathVariable("id");
        Mono<Customer> customer = customerService.getCustomerById(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(customer);
    }

    public ServerResponse registerCustomer(ServerRequest req) throws ServletException, IOException, BankappBusinessException {
        Customer customer = req.body(Customer.class);
        Mono<Customer> customerAdded = customerService.registerCustomer(customer);
        return ServerResponse.created(URI.create("/registerCustomer")).contentType(MediaType.APPLICATION_JSON)
                .body(customerAdded);
    }

    public ServerResponse getCustomers(ServerRequest req) throws BankappBusinessException {
        LOG.info("getCustomers");
        Flux<Customer> customers = customerService.getAllCustomers();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                //.body(BodyInserters.fromValue(users));
                .body(customers);
    }
}
