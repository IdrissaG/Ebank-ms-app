package com.idrissa.ebankservice.services;

import com.idrissa.ebankservice.entities.BankAccount;
import com.idrissa.ebankservice.feign.CustomerRestClient;
import com.idrissa.ebankservice.model.Customer;
import com.idrissa.ebankservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {
    BankAccountRepository bankAccountRepository;
    CustomerRestClient customerRestClient;

    public EbankService(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    public List<BankAccount> findAllAccounts(){
        return bankAccountRepository.findAll();
    }

    public BankAccount findBankAccountById(String id){
       BankAccount bankAccount =  bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Bank account not found"));
       bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
       return bankAccount;
    }

    public BankAccount saveBankAccount(BankAccount bankAccount){
        try {
            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreateAt(new Date());
            return bankAccountRepository.save(bankAccount);
        }catch (Exception e){
            throw new RuntimeException("Could not retrieve the customer maybe it does not exist, id: "+bankAccount.getCustomerId());
        }
    }

    public List<BankAccount> findBankAccountByCustomerId(Long customerId){
        return bankAccountRepository.findByCustomerId(customerId);
    }
}
