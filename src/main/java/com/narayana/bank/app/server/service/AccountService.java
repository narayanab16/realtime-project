package com.narayana.bank.app.server.service;

import com.narayana.bank.app.server.common.helper.BankappHelper;
import com.narayana.bank.app.server.exception.BankappBusinessException;
import com.narayana.bank.app.server.model.Account;
import com.narayana.bank.app.server.model.Customer;
import com.narayana.bank.app.server.repository.AccountRepository;
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
public class AccountService {
    private static Logger LOG = LoggerFactory.getLogger(AccountService.class);
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public Mono<Account> getAccount(String id) {
        LOG.info("getAccount id: " + id);
        Mono<Account> account = accountRepository.findById(id);
        return account;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Mono<Account> createAccount(Account account) throws BankappBusinessException {
        LOG.info("createAccount");
        Mono<Account> accountAdded = null;
        try {
            Customer inCustomer = account.getCustomer();
            Mono<Customer> customerExists = customerRepository.findByFirstNameAndLastNameAndDateOfBirth(inCustomer.getFirstName(), inCustomer.getLastName(), inCustomer.getDateOfBirth());
            Customer oldCust = customerExists.block();
            if(Objects.isNull(oldCust)) {
                LOG.info("createAccount - new account");
                String cust_id = BankappHelper.getCustomerId();
                inCustomer.setId(cust_id);
                inCustomer.setNewCustomer();
                account.setCustomer(inCustomer);
                account.setId(BankappHelper.getAccountNumber());
                account.setCustomerId(cust_id);
                account.setBranchId(account.getBankBranch().getId());
                account.setNewAccount();
                // 1st save customer and then subsequently save account
                accountAdded = customerRepository.save(inCustomer)
                        .flatMap(x -> accountRepository.save(account).then(Mono.just(account)));
            } else {
                // Customer Already Exists, existing branches
                // Add new accounts
                account.setCustomer(oldCust);
                account.setId(BankappHelper.getAccountNumber());
                account.setCustomerId(oldCust.getId());
                account.setBranchId(account.getBankBranch().getId());
                account.setNewAccount();
                accountAdded = accountRepository.save(account);
            }
        } catch (Exception e) {
            throw new BankappBusinessException(e.getMessage());
        }
        if(Objects.isNull(accountAdded))
            return Mono.just(new Account());
        else
            return accountAdded;
    }

    public Flux<Account> getAllAccounts() throws BankappBusinessException {
        LOG.info("getAllAccounts ");
        Flux<Account> allAccounts = null;
        try {
            allAccounts = accountRepository.findAll();
        } catch (Exception e) {
            throw new BankappBusinessException(e.getMessage());
        }
        return allAccounts;
    }
}
