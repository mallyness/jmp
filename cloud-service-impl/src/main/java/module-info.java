module com.example.jmp.cloud.service.impl {
    requires com.example.jmp.dto;
    requires transitive com.example.jmp.service.api;
    requires java.sql;
    requires org.apache.logging.log4j;
    provides com.example.jmp.service.api.Service with com.example.jmp.cloud.service.impl.ServiceImpl;
}