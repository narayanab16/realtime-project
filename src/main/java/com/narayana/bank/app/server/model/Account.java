package com.narayana.bank.app.server.model;


import com.narayana.bank.app.server.common.model.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "account")
public class Account extends BaseEntity {
    @Column(value = "customer_id")
    String customerId;
    @Column(value = "branch_id")
    String branchId;
    @Transient
    Customer customer;
    @Transient
    BankBranch bankBranch;
    @Column(value = "opening_balance")
    Double openingBalance;
    @Column(value = "account_open_date")
    LocalDateTime accountOpenDate;
    @Column(value = "account_update_date")
    LocalDateTime accountUpdateDate;
    @Column(value = "account_type")
    String accountType;
    @Column(value = "account_status")
    String accountStatus;
    public Account() {
    }

    @Id
    @Column(value = "account_id")
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return super.getId();
    }
    public Account setNewAccount() {
        this.isNewObject = true;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }

    public Double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public LocalDateTime getAccountOpenDate() {
        return accountOpenDate;
    }

    public void setAccountOpenDate(LocalDateTime accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }

    public LocalDateTime getAccountUpdateDate() {
        return accountUpdateDate;
    }

    public void setAccountUpdateDate(LocalDateTime accountUpdateDate) {
        this.accountUpdateDate = accountUpdateDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}
