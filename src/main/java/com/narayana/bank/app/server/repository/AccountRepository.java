package com.narayana.bank.app.server.repository;

import com.narayana.bank.app.server.model.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<Account, Serializable> {
}
