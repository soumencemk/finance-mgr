package com.soumen.app.finance_mgr_backend.service;

import com.soumen.app.finance_mgr_backend.data.entities.BankAccount;
import com.soumen.app.finance_mgr_backend.data.entities.Customer;
import com.soumen.app.finance_mgr_backend.data.repositories.CustomerRepository;
import com.soumen.app.finance_mgr_backend.model.AccountInfoVO;
import com.soumen.app.finance_mgr_backend.model.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


    public Collection<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerID) {
        return customerRepository.findById(customerID);
    }

    public AccountInfoVO getFullAccountInfo(Long customerID, boolean includeAccountDetails) throws Throwable {
        Customer customer = getCustomerById(customerID)
                .orElseThrow((Supplier<Throwable>) () -> new RuntimeException("Customer not found"));
        var totalBalance = sumBalance(customer.getBankAccountList());
        var savings = sumBalance(customer.getBankAccountList()
                .stream()
                .filter(bankAccount -> bankAccount.getAccountType()
                        .equals(AccountType.SAVINGS))
                .toList());
        var depositBalance = totalBalance - savings;
        return AccountInfoVO.builder()
                .customerId(customerID)
                .bankAccounts(includeAccountDetails ? customer.getBankAccountList() : null)
                .totalSavingsBalance(savings)
                .totalDepositsBalance(depositBalance)
                .totalBalance(totalBalance)
                .build();
    }

    public AccountInfoVO getAccountInfoByAccountType(Long customerID, String accountType, boolean includeBankAccountInfo) throws Throwable {
        Customer customer = getCustomerById(customerID)
                .orElseThrow((Supplier<Throwable>) () -> new RuntimeException("Customer not found"));

        List<BankAccount> list = customer.getBankAccountList()
                .stream()
                .filter(bankAccount -> bankAccount.getAccountType()
                        .name()
                        .equalsIgnoreCase(accountType))
                .toList();
        var balance = list.stream()
                .mapToDouble(BankAccount::getBalance)
                .sum();

        return AccountInfoVO.builder()
                .customerId(customerID)
                .bankAccounts(includeBankAccountInfo ? list : null)
                .totalSavingsBalance(accountType.equalsIgnoreCase(AccountType.SAVINGS.name()) ? balance : null)
                .totalDepositsBalance(accountType.equalsIgnoreCase(AccountType.DEPOSITS.name()) ? balance : null)
                .build();
    }

    public AccountInfoVO getAccountsBalance(Long customerID, String accountType) throws Throwable {
        return getAccountInfoByAccountType(customerID, accountType, false);
    }

    private Double sumBalance(List<BankAccount> bankAccounts) {
        return bankAccounts.stream()
                .mapToDouble(BankAccount::getBalance)
                .sum();
    }

    public AccountInfoVO getFullAccountBalance(Long customerID) throws Throwable {
        return getFullAccountInfo(customerID, false);
    }
}
