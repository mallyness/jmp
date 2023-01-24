package com.example.jmp.bank.api;

import com.example.jmp.dto.BankCard;
import com.example.jmp.dto.User;
import org.junit.jupiter.api.Test;

class BankTest {

    @Test
    void createBankCard() {
        Bank bank = (user, bankCardType) -> new BankCard("333", new User());
        System.out.println(bank);
    }
}
