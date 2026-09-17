package com.idrissa.ebankservice;

import com.idrissa.ebankservice.entities.BankAccount;
import com.idrissa.ebankservice.repositories.BankAccountRepository;
import com.idrissa.ebankservice.services.EbankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EbankService ebankService) {
        return args -> {
            for (int i = 1; i <= 3; i++) {
                for (int j = 0; j <=5 ; j++) {
                    ebankService.saveBankAccount(BankAccount.builder()
                                    .balance(1000 + Math.random() * 60000)
                                    .Type(Math.random()>0.5?"Current-Account":"Saving-Account")
                                    .customerId((long) i)
                            .build());
                }
            }
        };
    }
}
