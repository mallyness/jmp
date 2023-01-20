package com.example.jmp.bank.api;

import com.example.jmp.dto.BankCard;
import com.example.jmp.dto.BankCardType;
import com.example.jmp.dto.User;

public interface Bank {

    BankCard createBankCard(User user, BankCardType bankCardType);
}
