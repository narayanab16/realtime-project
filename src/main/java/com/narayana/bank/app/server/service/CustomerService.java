package com.narayana.bank.app.server.service;

import com.narayana.bank.app.server.common.helper.BankappHelper;
import com.narayana.bank.app.server.exception.BankappBusinessException;
import com.narayana.bank.app.server.model.Customer;
import com.narayana.bank.app.server.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class CustomerService {
    private static Logger LOG = LoggerFactory.getLogger(CustomerService.class);
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> getCustomerById(String id) {
        LOG.info("getCustomerById by id: " + id);
        Mono<Customer> customerMono = customerRepository.findById(id);
        return customerMono;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Mono<Customer> registerCustomer(Customer customer) throws BankappBusinessException {
        LOG.info("registerCustomer ");
        Mono<Customer> resCustomer = null;
        try {
            Mono<Customer> customerMono = customerRepository.findByFirstNameAndLastNameAndDateOfBirth(customer.getFirstName(), customer.getLastName(), customer.getDateOfBirth());
            Customer custOld = customerMono.block();
            if(Objects.isNull(custOld)) {
                LOG.info("registerCustomer - new customer");
                customer.setNewCustomer();
                customer.setId(BankappHelper.getCustomerId());
                resCustomer = customerRepository.save(customer);
            } else {
                // Customer already exists and return customer id
                LOG.info("Customer already exists and return customer id");
                resCustomer = customerMono;
            }
        } catch (Exception e) {
            throw new BankappBusinessException(e.getMessage());
        }
        return resCustomer;
    }

    public Flux<Customer> getAllCustomers() throws BankappBusinessException {
        LOG.info("getAllCustomers ");
        Flux<Customer> allCustomers = null;
        try {
            allCustomers = customerRepository.findAll();
        } catch (Exception e) {
            throw new BankappBusinessException(e.getMessage());
        }
        return allCustomers;
    }
}
