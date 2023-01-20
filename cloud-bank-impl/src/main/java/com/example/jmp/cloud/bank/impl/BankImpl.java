package com.example.jmp.cloud.bank.impl;

import com.example.jmp.bank.api.Bank;
import com.example.jmp.dto.BankCard;
import com.example.jmp.dto.BankCardType;
import com.example.jmp.dto.CreditBankCard;
import com.example.jmp.dto.DebitBankCard;
import com.example.jmp.dto.User;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class BankImpl implements Bank {

    private static final int CARD_NUMBER_LENGTH = 16;

    public BankCard createBankCard(User user, BankCardType bankCardType) {
        return switch (bankCardType) {
            case DEBIT -> new DebitBankCard(generateCardNumber(), user);
            case CREDIT -> new CreditBankCard(generateCardNumber(), user);
        };
    }

    private String generateCardNumber() {
        var cardNumbers = new int[CARD_NUMBER_LENGTH];
        var checkArray = new int[CARD_NUMBER_LENGTH - 1];

        for (int i = checkArray.length - 1; i >= 0; i--) {
            cardNumbers[i] = new Random().nextInt(0, 9);
            checkArray[i] = (cardNumbers[i] * (((i + 1) % 2) + 1)) % 9;
        }
        cardNumbers[15] = (Arrays.stream(checkArray).sum() * 9) % 10;  // A Luhn validation
        return Arrays
            .stream(cardNumbers)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining());
    }
}
