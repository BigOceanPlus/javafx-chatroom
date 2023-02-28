package com.example.client.runningService;

import com.example.Message;
import com.example.client.common.MyObjectInputStream;
import com.example.client.common.SpringContextUtil;
import com.example.client.viewsController.HomeController;
import javafx.application.Platform;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReceiverService {
    public String[] data;
    public void run(){
        new Thread(() -> {
            MyObjectInputStream input = SpringContextUtil.getBean(ClientService.class).getInput();
            while (true){
                try {
                    Message msg = (Message)input.readObject();
                    switch (msg.getType()){
                        case "all":
                            data = msg.getContext().split("@");
                            if(!data[0].equals(SpringContextUtil.getBean(ClientService.class).getUserId())) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        SpringContextUtil.getBean(HomeController.class).sendFrom(data[0], data[1]);
                                    }
                                });
                            }
                            break;
                        case "user":
                            data = msg.getContext().split("@");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    SpringContextUtil.getBean(HomeController.class).sendFrom(data[0], data[2]);
                                }
                            });
                            break;
                        default:
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }
}
