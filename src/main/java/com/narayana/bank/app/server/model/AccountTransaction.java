package com.narayana.bank.app.server.model;


import com.narayana.bank.app.server.common.model.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "account_transaction")
public class AccountTransaction extends BaseEntity {
    @Column(value = "account_id")
    private String accountId;

    @Column(value = "date_of_trxn")
    private LocalDateTime dateOfTrxn;
    @Column(value = "mode_of_transaction")
    private String modeOfTransaction;

    @Column(value = "transaction_type")
    private String transactionType;

    @Column(value = "transaction_amount")
    private Double transactionAmount;

    public AccountTransaction(){
    }

    public AccountTransaction setNewAccountTransaction() {
        isNewObject = true;
        return this;
    }

    @Id
    @Column(value = "trxn_number")
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
       return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getDateOfTrxn() {
        return dateOfTrxn;
    }

    public void setDateOfTrxn(LocalDateTime dateOfTrxn) {
        this.dateOfTrxn = dateOfTrxn;
    }

    public String getModeOfTransaction() {
        return modeOfTransaction;
    }

    public void setModeOfTransaction(String modeOfTransaction) {
        this.modeOfTransaction = modeOfTransaction;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
