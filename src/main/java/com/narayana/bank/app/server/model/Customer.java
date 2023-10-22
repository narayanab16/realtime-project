package com.narayana.bank.app.server.model;

import com.narayana.bank.app.server.common.model.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "customer")
public class Customer extends BaseEntity {
    @Column(value = "first_name")
    String firstName;
    @Column(value = "middle_name")
    String middleName;
    @Column(value = "last_name")
    String lastName;
    @Column(value = "city")
    String city;
    @Column(value = "mobile_no")
    String mobileNo;
    @Column(value = "occupation")
    String occupation;
    @Column(value = "date_of_birth")
    LocalDate dateOfBirth;


    public Customer() {
    }

    public Customer(String id, String firstName, String middleName, String lastName, String city,
                    String mobileNo, String occupation, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.city = city;
        this.mobileNo = mobileNo;
        this.occupation = occupation;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer setNewCustomer() {
        this.isNewObject = true;
        return this;
    }

    @Id
    @Column(value = "customer_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
