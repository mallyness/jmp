package com.example.jmp.service.api;

import com.example.jmp.dto.BankCard;
import com.example.jmp.dto.Subscription;
import com.example.jmp.dto.User;
import java.util.List;
import java.util.Optional;

public interface Service {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

}
