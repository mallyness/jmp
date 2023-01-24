module com.example.jmp.cloud.service.impl {
    requires com.example.jmp.dto;
    requires transitive com.example.jmp.service.api;
    provides com.example.jmp.service.api.Service with com.example.jmp.cloud.service.impl.ServiceImpl;
}