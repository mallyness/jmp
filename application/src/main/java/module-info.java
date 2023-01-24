module com.example.jmp.application {
    requires com.example.jmp.dto;
    requires com.example.jmp.cloud.service.impl;
    requires com.example.jmp.cloud.bank.impl;
    uses com.example.jmp.bank.api.Bank;
    uses com.example.jmp.service.api.Service;
}