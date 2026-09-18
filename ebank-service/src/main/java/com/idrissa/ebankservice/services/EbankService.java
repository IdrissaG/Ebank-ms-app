package com.idrissa.ebankservice.services;

import com.idrissa.ebankservice.entities.BankAccount;
import com.idrissa.ebankservice.feign.CustomerRestClient;
import com.idrissa.ebankservice.model.Customer;
import com.idrissa.ebankservice.repositories.BankAccountRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
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

    @McpTool(description = "Get all accounts")
    public List<BankAccount> findAllAccounts(){
        return bankAccountRepository.findAll();
    }

    @McpTool(description = "Get a customer by his id")
    public BankAccount findBankAccountById(@McpToolParam(description = "the account id") String id){
       BankAccount bankAccount =  bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Bank account not found"));
       bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
       return bankAccount;
    }

    @McpTool(description = "Save a bank account")
    public BankAccount saveBankAccount(@McpToolParam(description = "The Bank Account to save(Balance and Type)") BankAccount bankAccount){
        try {
            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreateAt(new Date());
            return bankAccountRepository.save(bankAccount);
        }catch (Exception e){
            throw new RuntimeException("Could not retrieve the customer maybe it does not exist, id: "+bankAccount.getCustomerId());
        }
    }

    @McpTool(description = "Find a bank account with the customer id")
    public List<BankAccount> findBankAccountByCustomerId(@McpToolParam(description = "the customer id") Long customerId){
        return bankAccountRepository.findByCustomerId(customerId);
    }
}
