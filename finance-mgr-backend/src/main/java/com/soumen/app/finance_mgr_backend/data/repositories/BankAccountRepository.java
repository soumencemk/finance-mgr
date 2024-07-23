package com.soumen.app.finance_mgr_backend.data.repositories;

import com.soumen.app.finance_mgr_backend.data.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
