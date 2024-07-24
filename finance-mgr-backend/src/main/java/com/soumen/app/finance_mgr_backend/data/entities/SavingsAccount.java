package com.soumen.app.finance_mgr_backend.data.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("SAVING")
@Data
public class SavingsAccount extends BankAccount {

}
