package com.idrissa.ebankservice.controllers;

import com.idrissa.ebankservice.entities.BankAccount;
import com.idrissa.ebankservice.services.EbankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EbankRestController {
    private EbankService ebankService;

    public EbankRestController(EbankService ebankService) {
        this.ebankService = ebankService;
    }

    @GetMapping("/accounts")
    public List<BankAccount> findAllAccounts(){
        return ebankService.findAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount findBankAccountById(@PathVariable String id){
        return ebankService.findBankAccountById(id);
    }

    @PostMapping("/accounts/save")
    public BankAccount saveBankAccount(@RequestBody BankAccount bankAccount){
        return ebankService.saveBankAccount(bankAccount);
    }

    @GetMapping("/accounts/customer/{id}")
    public List<BankAccount> findBankAccountByCustomerId(@PathVariable Long customerId){
        return ebankService.findBankAccountByCustomerId(customerId);
    }
}
