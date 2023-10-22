package com.narayana.bank.app.server.repository;

import com.narayana.bank.app.server.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.time.LocalDate;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Serializable> {
    Mono<Customer> findByFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName, LocalDate dateOfBirth);
}
