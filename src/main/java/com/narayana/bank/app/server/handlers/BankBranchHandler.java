package com.narayana.bank.app.server.handlers;

import com.narayana.bank.app.server.exception.BankappBusinessException;
import com.narayana.bank.app.server.model.BankBranch;
import com.narayana.bank.app.server.service.BankBranchService;
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
public class BankBranchHandler {
    private static Logger LOG = LoggerFactory.getLogger(BankBranchHandler.class);
    private BankBranchService bankBranchService;

    public BankBranchHandler(BankBranchService bankBranchService) {
        this.bankBranchService = bankBranchService;
    }

    public ServerResponse getBranchById(ServerRequest req) {
        LOG.info("getBranchDetails ");
        String id = req.pathVariable("id");
        LOG.info(" id: " + id);
        Mono<BankBranch> bankBranchMono = bankBranchService.getBranchById(id);
        LOG.info(" bankBranchMono " + bankBranchMono);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(bankBranchMono);
    }

    public ServerResponse createBranch(ServerRequest req) throws ServletException, IOException, BankappBusinessException {
        BankBranch newBranch = req.body(BankBranch.class);
        newBranch.setNewBranch();
        Mono<BankBranch> branchAdded = bankBranchService.saveBranch(newBranch);

        return ServerResponse.created(URI.create("/createBranch")).contentType(MediaType.APPLICATION_JSON)
                .body(branchAdded);
    }

    public ServerResponse getAllBranches(ServerRequest req) throws BankappBusinessException {
        LOG.info("getAllBranches  ");
        Flux<BankBranch> users = bankBranchService.getAllBranches();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                //.body(BodyInserters.fromValue(users));
                .body(users);
    }
}
