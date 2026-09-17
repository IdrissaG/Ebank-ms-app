package com.idrissa.ebankservice.services;

import com.idrissa.ebankservice.entities.BankAccount;
import com.idrissa.ebankservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {
    BankAccountRepository bankAccountRepository;

    public EbankService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> findAllAccounts(){
        return bankAccountRepository.findAll();
    }

    public BankAccount findBankAccountById(String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Bank account not found"));
    }

    public BankAccount saveBankAccount(BankAccount bankAccount){
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreateAt(new Date());
        return bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> findBankAccountByCustomerId(Long customerId){
        return bankAccountRepository.findByCustomerId(customerId);
    }
}
