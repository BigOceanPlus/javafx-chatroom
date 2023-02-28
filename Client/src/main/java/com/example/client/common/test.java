package com.example.client.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        /*AudioRecorder audioRecorder = new AudioRecorder("test.wav");
        new Thread(() -> {
            audioRecorder.start();
            try {
                Thread.sleep(5000);//这里是设置录音的时长
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Scanner in = new Scanner(System.in);
        in.next();
        audioRecorder.stopRecording();
        audioRecorder.play("test.wav");//根据文件路径播放音频*/
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        System.out.println(dateNowStr);
    }
}
