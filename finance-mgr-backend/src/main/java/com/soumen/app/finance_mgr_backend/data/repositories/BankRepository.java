package com.soumen.app.finance_mgr_backend.data.repositories;

import com.soumen.app.finance_mgr_backend.data.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByName(String name);
}
