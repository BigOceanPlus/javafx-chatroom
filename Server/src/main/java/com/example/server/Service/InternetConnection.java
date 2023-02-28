package com.example.server.Service;

import com.example.server.common.Client;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

@Service
@Data
public class InternetConnection {
    private static int PORT = 8000;
    private HashMap<String, Client> onlineList = new HashMap<>();
    private HashSet<Client> sockets = new HashSet<>();
    private ServerSocket serverSocket;

    {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddUser(String userId, Client client){
        onlineList.put(userId,client);
    }

    public void AddSocket(Socket socket){
        sockets.add(new Client(socket));
    }
}
