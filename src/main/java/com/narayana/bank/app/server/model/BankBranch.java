package com.narayana.bank.app.server.model;

import com.narayana.bank.app.server.common.model.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "bank_branch")
public class BankBranch extends BaseEntity {
    @Column(value = "branch_name")
    String branchName;
    @Column(value = "branch_city")
    String branchCity;
    @Column(value = "branch_state")
    String branchState;
    @Column(value = "branch_country")
    String branchCountry;

    public BankBranch() {
    }

    public BankBranch(String id, String branchName, String branchCity, String branchState, String branchCountry) {
        this.id = id;
        this.branchName = branchName;
        this.branchCity = branchCity;
        this.branchState = branchState;
        this.branchCountry = branchCountry;
    }

    @Id
    @Column(value = "branch_id")
    public String getId() {
        return id;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public String getBranchState() {
        return branchState;
    }

    public String getBranchCountry() {
        return branchCountry;
    }

    public BankBranch setNewBranch() {
        this.isNewObject = true;
        return this;
    }
}
