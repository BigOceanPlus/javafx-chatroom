package com.example.server;

import com.example.server.runningService.ConnectionHandler;
import com.example.server.runningService.QueueHandler;
import com.example.server.common.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

            SpringContextUtil.getBean(QueueHandler.class).run();
            SpringContextUtil.getBean(ConnectionHandler.class).run();


    }

}
