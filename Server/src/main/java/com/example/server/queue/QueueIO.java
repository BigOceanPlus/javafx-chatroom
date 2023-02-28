package com.example.server.queue;

import com.example.Message;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class QueueIO {
    @Autowired
    MessageQueue messageQueue;
    public Message Get(){
        try {
            return messageQueue.getBuf().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void Put(Message msg){
        try {
            messageQueue.getBuf().put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
