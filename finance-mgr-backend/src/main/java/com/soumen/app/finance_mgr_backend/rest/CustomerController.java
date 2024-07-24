package com.soumen.app.finance_mgr_backend.rest;


import com.soumen.app.finance_mgr_backend.data.entities.Customer;
import com.soumen.app.finance_mgr_backend.model.AccountInfoVO;
import com.soumen.app.finance_mgr_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.function.Supplier;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Long customerID) throws Throwable {
        return customerService.getCustomerById(customerID)
                .orElseThrow((Supplier<Throwable>) () -> new RuntimeException("Customer not found"));
    }

    @GetMapping("/{id}/accounts")
    public AccountInfoVO getAccountInfoForCustomer(@PathVariable("id") Long customerID) throws Throwable {
        return customerService.getFullAccountInfo(customerID, true);

    }

    @GetMapping("/{id}/accounts/balance")
    public AccountInfoVO balanceForAllAccounts(@PathVariable("id") Long customerID) throws Throwable {
        return customerService.getFullAccountBalance(customerID);

    }

    @GetMapping("/{id}/accounts/{accountType}")
    public AccountInfoVO getAccountInfoForCustomerByAccountType(@PathVariable("id") Long customerID,
                                                                @PathVariable("accountType") String accountType) throws Throwable {
        return customerService.getAccountInfoByAccountType(customerID, accountType, true);

    }

    @GetMapping("/{id}/accounts/{accountType}/balance")
    public AccountInfoVO getAccountsBalance(@PathVariable("id") Long customerID,
                                            @PathVariable("accountType") String accountType) throws Throwable {
        return customerService.getAccountsBalance(customerID, accountType);

    }


}
