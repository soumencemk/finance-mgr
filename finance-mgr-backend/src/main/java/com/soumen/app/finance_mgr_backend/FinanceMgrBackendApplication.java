package com.soumen.app.finance_mgr_backend;

import com.soumen.app.finance_mgr_backend.data.entities.SavingsAccount;
import com.soumen.app.finance_mgr_backend.data.repositories.BankAccountRepository;
import com.soumen.app.finance_mgr_backend.data.repositories.BankRepository;
import com.soumen.app.finance_mgr_backend.data.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class FinanceMgrBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(FinanceMgrBackendApplication.class, args);
    }

    @Bean
    public ApplicationRunner runApp(BankRepository bankRepository,
                                    BankAccountRepository bankAccountRepository,
                                    CustomerRepository customerRepository) {
        return args -> {




        };
    }

}
