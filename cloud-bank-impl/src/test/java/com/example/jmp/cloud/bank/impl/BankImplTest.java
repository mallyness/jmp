package com.example.jmp.cloud.bank.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.jmp.bank.api.Bank;
import com.example.jmp.dto.BankCard;
import com.example.jmp.dto.BankCardType;
import com.example.jmp.dto.CreditBankCard;
import com.example.jmp.dto.DebitBankCard;
import com.example.jmp.dto.User;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BankImplTest {

    private static final String NAME = "Bob";
    private static final String SURNAME = "Smith";
    private static final LocalDate BIRTHDAY = LocalDate.now();
    private final Bank bank = new BankImpl();

    @Test
    void createBankCardShouldReturnDebitCardIfCardTypeIsDebit() {
        BankCard bankCard = bank.createBankCard(createUser(), BankCardType.DEBIT);

        assertNotNull(bankCard);
        assertThat(bankCard, instanceOf(DebitBankCard.class));
    }

    @Test
    void createBankCardShouldReturnCreditCardIfCardTypeIsCredit() {
        BankCard bankCard = bank.createBankCard(createUser(), BankCardType.CREDIT);

        assertNotNull(bankCard);
        assertThat(bankCard, instanceOf(CreditBankCard.class));
    }

    @Test
    void createBankCardShouldThrowExceptionWhenCardTypeIsNull() {
        assertThrows(NullPointerException.class, () -> bank.createBankCard(createUser(), null));
    }

    private User createUser() {
        return new User().setName(NAME)
            .setSurname(SURNAME)
            .setBirthday(BIRTHDAY);
    }
}