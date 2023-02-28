package com.example.server.runningService;

import com.example.server.common.Client;
import com.example.Message;
import com.example.server.queue.QueueIO;
import com.example.server.Service.InternetConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

@Service
public class ConnectionHandler {

    @Autowired
    InternetConnection internetConnection;

    @Autowired
    QueueIO queueIO;

    public void handler(String rowMsg, Socket socket){
        internetConnection.AddUser(rowMsg,new Client(socket));
    }

    public Socket listen(){
        Socket socket = null;
        try {
            socket = internetConnection.getServerSocket().accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    public HashMap<String, Client> Notice(){
        return internetConnection.getOnlineList();
    }

    public void run(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Socket socket = listen();
                    if (socket != null) {
                        System.out.println(socket.getInetAddress());
                        Client cli = new Client(socket);
                        internetConnection.AddSocket(socket);
                        Thread thread = new Thread(new Job(cli));
                        thread.start();
                    }
                }
            }
        }).start();
    }

    class Job implements Runnable{
        public Client cli;
        public Job(Client cli){
            this.cli = cli;
        }
        @Override
        public void run() {
            while(true){
                try {
                    Object obj = cli.getInput().readObject();
                    Message msg = (Message) obj;
                    if(msg.getType().equals("connection")){
                        internetConnection.getOnlineList().put(msg.getContext(),cli);
                        System.out.println("connection");
                        System.out.println(Notice());
                    }
                    else if(msg.getType().equals("file")){
                        try {
                            DataInputStream dis = new DataInputStream(cli.getSocket().getInputStream());
                            // 文件名和长度
                            String fileName = dis.readUTF();
                            long fileLength = dis.readLong();
                            File directory = new File("src/main/java/com/example/server/file");
                            if(!directory.exists()) {
                                directory.mkdir();
                            }
                            File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);

                            FileOutputStream fos = new FileOutputStream(file);
                            System.out.println("file。。。。。。。。。。。。。。"+file);
                            System.out.println("fileName。。。。。。。。。。。。。。"+fileName);

                            System.out.println("======== 开始接收文件 ========");
                            byte[] bytes = new byte[1024];
                            int length = 0;
                            while((length = dis.read(bytes, 0, bytes.length)) != -1) {
                                fos.write(bytes, 0, length);
                                fos.flush();
                            }

                            System.out.println("======== 文件接收成功 ========");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else queueIO.Put(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
