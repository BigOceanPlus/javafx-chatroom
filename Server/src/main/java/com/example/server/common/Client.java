package com.example.server.common;

import lombok.Data;

import java.io.*;
import java.net.Socket;

@Data
public class Client {
    private Socket socket;
    private MyObjectInputStream input;
    private MyObjectOutputStream output;
    public Client(Socket socket){
        try {
            this.socket = socket;
            input = new MyObjectInputStream(socket.getInputStream());
            output = new MyObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
