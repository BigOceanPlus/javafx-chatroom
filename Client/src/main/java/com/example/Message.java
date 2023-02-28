package com.example;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    String type;
    String context;
    public Message(String type,String context){
        this.type = type;
        this.context = context;
    }
}
