package com.example.client.common;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileUtil {
    DataOutputStream output;
    DataInputStream input;
    public void WriteFile(String fileName,String[] data){
        try {
            output = new DataOutputStream(new FileOutputStream(fileName));
            int len = data.length;
            for(int i = 0; i < len; i++) output.writeUTF(data[i]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] PasswordSavedVerification(String fileName){
        try {
            input = new DataInputStream(new FileInputStream(fileName));
            String[] res = new String[2];

            if(input.readUTF().equals("false")){
                res[0] = "";
                res[1] = "";
                return res;
            }
            else
                res[0] = input.readUTF();

            if(input.readUTF().equals("false")){
                res[1] = "";
                return res;
            }
            else{
                res[1] = input.readUTF();
                return res;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
