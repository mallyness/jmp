package com.example.jmp.cache;

public interface Cache {

    void put(Data data);
    Data get(Data data);
    long getSize();
}
