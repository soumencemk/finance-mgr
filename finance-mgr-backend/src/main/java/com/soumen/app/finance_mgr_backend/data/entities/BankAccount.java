package com.soumen.app.finance_mgr_backend.data.entities;


import com.soumen.app.finance_mgr_backend.model.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = STRING)
@Data
public abstract class BankAccount {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    private Double balance;
    private AccountType accountType;
    private String accountNumber;
    private Double rateOfInterest;
}
