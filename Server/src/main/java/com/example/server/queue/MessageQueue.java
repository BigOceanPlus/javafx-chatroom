package com.example.server.queue;

import com.example.Message;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Data
public class MessageQueue {
    private static int QUEUE_SIZE = 100;
    private static int POOL_SIZE = 10;
    private ArrayBlockingQueue<Message> buf = new ArrayBlockingQueue<>(QUEUE_SIZE);
    private ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);

}
