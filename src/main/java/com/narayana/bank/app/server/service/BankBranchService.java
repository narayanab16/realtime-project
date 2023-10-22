package com.narayana.bank.app.server.service;

import com.narayana.bank.app.server.exception.BankappBusinessException;
import com.narayana.bank.app.server.model.BankBranch;
import com.narayana.bank.app.server.repository.BankBranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class BankBranchService {
    private static Logger LOG = LoggerFactory.getLogger(BankBranchService.class);
    private BankBranchRepository bankBranchRepository;

    public BankBranchService(BankBranchRepository bankBranchRepository) {
        this.bankBranchRepository = bankBranchRepository;
    }

    public Mono<BankBranch> getBranchById(String id) {
        LOG.info("getBranchById by id: " + id);
        Mono<BankBranch> bankBranchMono = bankBranchRepository.findById(id);
        return bankBranchMono;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Mono<BankBranch> saveBranch(BankBranch branch) throws BankappBusinessException {
        LOG.info("saveBranch new branch");
        Mono<BankBranch> branchAdded = null;
        try {
            branchAdded = bankBranchRepository.save(branch);
        } catch (Exception e) {
            throw new BankappBusinessException(e.getMessage());
        }
        return branchAdded;
    }

    public Flux<BankBranch> getAllBranches() throws BankappBusinessException {
        LOG.info("getAllBranches");
        Flux<BankBranch> allBranches = null;
        try {
            allBranches = bankBranchRepository.findAll();
        } catch (Exception e) {
            throw new BankappBusinessException(e.getMessage());
        }
        return allBranches;
    }
}
