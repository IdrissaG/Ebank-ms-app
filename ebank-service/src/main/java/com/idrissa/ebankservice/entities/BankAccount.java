package com.idrissa.ebankservice.entities;

import com.idrissa.ebankservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {

    @Id
    private String id;
    private Date createAt;
    private Double balance;
    private String Type;
    private Long customerId;
    @Transient
    private Customer customer;

}
