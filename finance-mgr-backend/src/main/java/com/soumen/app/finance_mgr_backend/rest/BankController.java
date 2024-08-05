package com.soumen.app.finance_mgr_backend.rest;

import com.soumen.app.finance_mgr_backend.data.entities.Bank;
import com.soumen.app.finance_mgr_backend.data.repositories.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankRepository bankRepository;

    @GetMapping
    public Collection<Bank> getBanks() {
        return bankRepository.findAll();
    }
}
