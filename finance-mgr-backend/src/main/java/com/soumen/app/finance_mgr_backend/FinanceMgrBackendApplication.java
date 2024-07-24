package com.soumen.app.finance_mgr_backend;

import com.soumen.app.finance_mgr_backend.data.entities.*;
import com.soumen.app.finance_mgr_backend.data.repositories.BankAccountRepository;
import com.soumen.app.finance_mgr_backend.data.repositories.BankRepository;
import com.soumen.app.finance_mgr_backend.data.repositories.CustomerRepository;
import com.soumen.app.finance_mgr_backend.model.AccountType;
import com.soumen.app.finance_mgr_backend.model.PayoutType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

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
            log.info("Loading fake Data");
            var ad = new Address();
            ad.setCity("Kolkata");
            ad.setCountry("IN");

            Stream.of("Axis Bank", "ICICI Bank")
                    .map(bankName -> Bank.builder()
                            .name(bankName)
                            .currency(Currency.getInstance("INR"))
                            .build())
                    .forEach(bankRepository::saveAndFlush);

            SavingsAccount savingsAccount = new SavingsAccount();
            savingsAccount.setAccountNumber("12345");
            savingsAccount.setBank(bankRepository.findById(1l)
                    .get());
            savingsAccount.setAccountNumber("1111111111111111");
            savingsAccount.setBalance(10000000.0);
            savingsAccount.setRateOfInterest(4.0d);
            savingsAccount.setAccountType(AccountType.SAVINGS);

            DepositAccount depositAccount = new DepositAccount();
            depositAccount.setAccountType(AccountType.DEPOSITS);
            depositAccount.setAccountNumber("FD-1");
            depositAccount.setBank(bankRepository.findById(2L)
                    .get());
            depositAccount.setBalance(100000.0d);
            depositAccount.setRateOfInterest(7.5d);
            depositAccount.setPayoutType(PayoutType.MONTHLY);
            depositAccount.setStartDate(new Date());

            var customer = new Customer();
            customer.setFirstName("Soumen");
            customer.setLastName("Karmakar");
            customer.setUsername("admin");
            customer.setPassword("admin");
            customer.setAddress(ad);
            customer.setBankAccountList(List.of(savingsAccount, depositAccount));

            bankAccountRepository.save(savingsAccount);
            bankAccountRepository.save(depositAccount);
            customerRepository.save(customer);

        };


    }

}
