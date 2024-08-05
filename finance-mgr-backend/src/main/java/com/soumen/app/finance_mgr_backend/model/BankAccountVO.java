package com.soumen.app.finance_mgr_backend.model;

import java.util.Date;

public record BankAccountVO(AccountType type,
                            String accountNumber,
                            String bankName,
                            Double balance,
                            Double roi,
                            Date startDate,
                            Date matureDate,
                            Double matAmount,
                            PayoutType payoutType) {
}
