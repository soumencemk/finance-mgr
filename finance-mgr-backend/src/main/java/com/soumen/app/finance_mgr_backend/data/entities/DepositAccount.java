package com.soumen.app.finance_mgr_backend.data.entities;

import com.soumen.app.finance_mgr_backend.model.AccountType;
import com.soumen.app.finance_mgr_backend.model.PayoutType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import static com.soumen.app.finance_mgr_backend.model.AccountType.DEPOSITS;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("DEPOSIT")
@Data
public class DepositAccount extends BankAccount {
    private Date startDate;
    private Date matureDate;
    private Double maturityAmount;
    private PayoutType payoutType;
}
