package com.narayana.bank.app.server.service;

import com.narayana.bank.app.server.common.AccountTransactionType;
import com.narayana.bank.app.server.common.helper.BankappHelper;
import com.narayana.bank.app.server.exception.BankappBusinessException;
import com.narayana.bank.app.server.model.Account;
import com.narayana.bank.app.server.model.AccountTransaction;
import com.narayana.bank.app.server.repository.AccountRepository;
import com.narayana.bank.app.server.repository.AccountTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class AccountTransactionService {
    private static Logger LOG = LoggerFactory.getLogger(AccountTransactionService.class);
    private AccountTransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public AccountTransactionService(AccountTransactionRepository transactionRepository,
                                     AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public Mono<AccountTransaction> getAccountTrxnById(String id) {
        LOG.info("getAccountTrxnById id: " + id);
        Mono<AccountTransaction> accountTrxn = transactionRepository.findById(id);
        return accountTrxn;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Mono<AccountTransaction> createAccountTrxn(AccountTransaction accountTrxn) throws BankappBusinessException {
        LOG.info("createAccountTrxn");
        Mono<AccountTransaction> accountTrxnAdded = null;
        try {
            String accountId = accountTrxn.getAccountId();
            Mono<Account> accountExists = accountRepository.findById(accountId);
            Account oldAccount = accountExists.block();
            if(Objects.nonNull(oldAccount)) {
                LOG.info("createAccountTrxn - oldAccount");
                Double balance = 0.0;
                if(AccountTransactionType.DEPOSIT.name().equalsIgnoreCase("DEPOSIT")) {
                    balance = oldAccount.getOpeningBalance() + accountTrxn.getTransactionAmount();
                }
                if(AccountTransactionType.WITHDRAWAL.name().equalsIgnoreCase("WITHDRAWAL")) {
                    balance = oldAccount.getOpeningBalance() - accountTrxn.getTransactionAmount();
                    if(balance < 0) {
                        throw new BankappBusinessException("You do not have sufficient balance to withdraw");
                    }
                }
                // old Account
                LocalDateTime nowDtTime = LocalDateTime.now();
                oldAccount.setOpeningBalance(balance);
                oldAccount.setAccountUpdateDate(nowDtTime);
                // New Account Trxn
                accountTrxn.setId(BankappHelper.getTrxnNumber());
                accountTrxn.setDateOfTrxn(nowDtTime);
                accountTrxn.setNewAccountTransaction();
                accountTrxnAdded = accountRepository.save(oldAccount).flatMap(x -> transactionRepository.save(accountTrxn)
                        .then(Mono.just(accountTrxn)));
            } else {
                throw new BankappBusinessException("No account id available to process trxn");
            }
        } catch (Exception e) {
            throw new BankappBusinessException(e.getMessage());
        }
        if(Objects.isNull(accountTrxnAdded))
            return Mono.just(new AccountTransaction());
        else
            return accountTrxnAdded;
    }

    public Flux<AccountTransaction> getAllAccountTrxns() throws BankappBusinessException {
        LOG.info("getAllAccountTrxns ");
        Flux<AccountTransaction> allAccountTrxns = null;
        try {
            allAccountTrxns = transactionRepository.findAll();
        } catch (Exception e) {
            throw new BankappBusinessException(e.getMessage());
        }
        return allAccountTrxns;
    }
}
