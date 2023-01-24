module com.example.jmp.cloud.bank.impl {
    requires com.example.jmp.dto;
    requires transitive com.example.jmp.bank.api;
    provides com.example.jmp.bank.api.Bank with com.example.jmp.cloud.bank.impl.BankImpl;
}
