package com.example.server.runningService;

import com.example.Message;
import com.example.server.Service.InternetConnection;
import com.example.server.common.Client;
import com.example.server.common.SpringContextUtil;
import com.example.server.queue.QueueIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QueueHandler {
    @Autowired
    QueueIO queueIO;

    @Autowired
    ConnectionHandler connectionHandler;

    public void run(){
        new Thread(() -> {
            while(true){
                Message msg = queueIO.Get();
                queueIO.getMessageQueue().getExecutorService().execute(new Job(msg));
            }
        }).start();
    }
}

class Job implements Runnable{
    public Message msg;
    public Job(Message msg){
        this.msg = msg;
    }

    @Override
    public void run() {
        switch (msg.getType()) {
            case "all":
                for(Client client : SpringContextUtil.getBean(InternetConnection.class).getOnlineList().values()) {
                    try {
                        client.getOutput().writeObject(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "user":
                String[] res = msg.getContext().split("@");
                Client cli = SpringContextUtil.getBean(InternetConnection.class).getOnlineList().get(res[1]);
                if(cli != null) {
                    try {
                        cli.getOutput().writeObject(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
