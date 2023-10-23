package com.narayana.bank.app.server.repository;

import com.narayana.bank.app.server.model.AccountTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface AccountTransactionRepository extends ReactiveCrudRepository<AccountTransaction, Serializable> {
}
