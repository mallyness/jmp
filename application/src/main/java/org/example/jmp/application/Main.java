package org.example.jmp.application;

import com.example.jmp.bank.api.Bank;
import com.example.jmp.dto.BankCard;
import com.example.jmp.dto.BankCardType;
import com.example.jmp.dto.User;
import com.example.jmp.service.api.Service;
import java.time.LocalDate;
import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {
        // check a work of the ServiceImpl class
        Iterable<Service> services = ServiceLoader.load(Service.class);
        Service service = services.iterator().next();

        System.out.println(service.getClass());
        service.getAllUsers();

        // check a work of the BankImpl class
        Iterable<Bank> bankServices = ServiceLoader.load(Bank.class);
        Bank bank = bankServices.iterator().next();

        System.out.println(bank.getClass());
        BankCard bankCard = bank.createBankCard(new User()
            .setName("Alice")
            .setSurname("Gold")
            .setBirthday(LocalDate.now()), BankCardType.DEBIT);
        System.out.println(bankCard.toString());
    }
}