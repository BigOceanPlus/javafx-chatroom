package com.example.client.runningService;

import com.example.Message;
import com.example.client.common.MyObjectInputStream;
import com.example.client.common.MyObjectOutputStream;
import com.example.client.common.SpringContextUtil;
import com.example.client.viewsController.LoginController;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

@Service
@Data
public class ClientService {
    public String userId;
    static public final int PORT = 8000;
    static public final String LOCAL = "localhost";
    static public final String INET = "43.143.167.120";
    public MyObjectInputStream input;
    public MyObjectOutputStream output;
    public Socket socket;
    public void run(){
        try {
            socket = new Socket(LOCAL,PORT);
            input = new MyObjectInputStream(socket.getInputStream());
            output = new MyObjectOutputStream(socket.getOutputStream());
            output.writeObject(new Message("connection", SpringContextUtil.getBean(LoginController.class).user_field.getText()));
            System.out.println(233);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendFile(String url) throws IOException {
        File file = new File(url);
        try {
            FileInputStream fis = new FileInputStream(file);;
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            //文件名、大小等属性
            dos.writeUTF(file.getName());
            dos.flush();
            dos.writeLong(file.length());
            dos.flush();
            // 开始传输文件
            System.out.println("======== 开始传输文件 ========");
            byte[] bytes = new byte[1024];
            int length = 0;

            while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
                dos.write(bytes, 0, length);
                dos.flush();
            }
            System.out.println("======== 文件传输成功 ========");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("客户端文件传输异常");
        }finally{

        }
    }
}
