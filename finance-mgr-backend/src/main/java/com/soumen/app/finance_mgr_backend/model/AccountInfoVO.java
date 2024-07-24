package com.soumen.app.finance_mgr_backend.model;

import com.soumen.app.finance_mgr_backend.data.entities.BankAccount;
import lombok.Builder;

import java.util.List;

@Builder
public record AccountInfoVO(long customerId,
                            Double totalSavingsBalance,
                            Double totalDepositsBalance,
                            Double totalBalance,
                            List<BankAccount> bankAccounts) {
}
