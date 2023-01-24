package com.example.jmp.dto;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testToString() {
        User user = new User().setName("name")
            .setSurname("surname")
            .setBirthday(LocalDate.now());
        System.out.println(user);
        System.out.println(new DebitBankCard("1", user));
        System.out.println(new CreditBankCard("2", user));
        System.out.println(new Subscription("123", LocalDate.now()));

    }

    @Test
    void testSubscription() {
        Subscription subscription = new Subscription("123", LocalDate.now());
        System.out.println(subscription);
    }
}