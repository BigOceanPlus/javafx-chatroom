package com.example.client.viewsController;

import com.example.Message;
import com.example.client.ClientApplication;
import com.example.client.common.AudioRecorder;
import com.example.client.common.DateUtil;
import com.example.client.common.SpringContextUtil;
import com.example.client.entity.User;
import com.example.client.runningService.ClientService;
import com.example.client.runningService.ReceiverService;
import com.example.client.service.UserService;
import com.example.client.views.HomeView;
import com.example.client.views.LoginView;
import com.example.client.views.RegisterView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FXMLController
public class HomeController implements Initializable {
    public static final int ROOM = 0;
    public static final int CHAT = 1;
    public static final int ADD = 2;
    public static final int IDEN = 3;
    public static final int GROUP = 4;

    public Button btn1;
    public Button btn2;
    ObservableList<AnchorPane> oblist = FXCollections.observableArrayList();
    public ListView list = new ListView<AnchorPane>(oblist);
    public TextArea textarea;
    public VBox vbox;
    public TextField text1;

    public Label status;
    public ImageView img1;
    public ImageView img2;
    public ImageView img3;
    public ImageView img4;
    public Label label;
    public Label roomName;

    public ImageView file;

    public ImageView maike;

    public int tag = 0;

    @Autowired
    HomeView homeView;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent parent = homeView.getView();

                HomeController.this.stage = (Stage) parent.getScene().getWindow();
                stage.setTitle("用户主页");

                stage.setResizable(false);
                SpringContextUtil.getBean(ReceiverService.class).run();
                status.setText("0");
            }
        });
    }

    // 提交
    public void btn1_action(){
        sendTo();
        try {
            if(roomName.getText().equals("聊天室"))
                SpringContextUtil.getBean(ClientService.class).getOutput().writeObject(new Message("all",SpringContextUtil.getBean(ClientService.class).getUserId() + "@" + textarea.getText()));
            else if(tag == 1){
                SpringContextUtil.getBean(ClientService.class).getOutput().writeObject(new Message("user",SpringContextUtil.getBean(ClientService.class).getUserId() + "@" + roomName.getText() + "@" + textarea.getText()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        textarea.setText("");
    }

    public void sendTo(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/ChatItemMe.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane an = (AnchorPane)root;
        String data = SpringContextUtil.getBean(DateUtil.class).Date();
            for(Node node : an.getChildren()) {
                if(node.getId().equals("time")){
                    Label tmp = (Label) node;
                    tmp.setText(data);
                }
                else if(node.getId().equals("name")){
                    Label tmp = (Label) node;
                    tmp.setText(SpringContextUtil.getBean(ClientService.class).getUserId());
                }
                else if(node.getId().equals("content")){
                    Label tmp = (Label) node;
                    tmp.setText(textarea.getText());

                }
            }
            vbox.getChildren().add(an);
    }

    public void sendFrom(String name,String content){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/ChatItem.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane an = (AnchorPane)root;
        for(Node node : an.getChildren()) {
            if(node.getId().equals("time")){
                Label tmp = (Label) node;
                tmp.setText(SpringContextUtil.getBean(DateUtil.class).Date());

            }
            else if(node.getId().equals("name")){
                Label tmp = (Label) node;
                tmp.setText(name);

            }
            else if(node.getId().equals("content")){
                Label tmp = (Label) node;
                tmp.setText(content);

            }
        }
        vbox.getChildren().add(an);
    }

    // 清空
    public void btn2_action(){
        textarea.setText("");
    }

    public void text1_rel(){
        List<User> users = null;
        if(tag == 2)
            users = SpringContextUtil.getBean(UserService.class).findByName(text1.getText());
        else if(tag == 1)
            users = SpringContextUtil.getBean(UserService.class).findFriend(SpringContextUtil.getBean(ClientService.class).userId);
        System.out.println(users);
        list.getItems().clear();
        for(User user : users){
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/UserItem.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            AnchorPane an = (AnchorPane)root;
            for(Node node : an.getChildren()) {
                if(node.getId().equals("name")){
                    Label l = (Label) node;
                    l.setText(user.getUsername());
                }
                else if(node.getId().equals("id")){
                    Label l = (Label) node;
                    l.setText(user.getUserId());
                }
            }
            oblist.add(an);
        }
        list.setItems(oblist);

    }

    // 标签
    public void img1_enter(){
        img1.setOpacity(1.0);
    }
    public void img1_exit(){
        img1.setOpacity(0.4);
    }
    public void img1_click(){
        status.setText("1");
        tag = 1;
        text1_rel();
    }
    public void img2_enter(){
        img2.setOpacity(1.0);
    }
    public void img2_exit(){
        img2.setOpacity(0.4);
    }
    public void img2_click(){
        status.setText("2");
        tag = 2;
    }
    public void img3_enter(){
        img3.setOpacity(1.0);
    }
    public void img3_exit(){
        img3.setOpacity(0.4);
    }
    public void img3_click(){
        status.setText("3");
        tag = 3;
    }
    public void img4_enter(){
        img4.setOpacity(1.0);
    }
    public void img4_exit(){
        img4.setOpacity(0.4);
    }
    public void img4_click(){
        status.setText("4");
        tag = 4;
    }

    public void label_click(){
        status.setText("0");
        tag = 0;
        roomName.setText("聊天室");
    }

    public void maike_enter(){
        maike.setOpacity(1.0);
    }
    public void maike_exit(){
        maike.setOpacity(0.3);
    }
    public void maike_click(){
        AudioRecorder audioRecorder = new AudioRecorder("test.wav");
        new Thread(() -> {
            audioRecorder.start();
            try {
                Thread.sleep(5000);//这里是设置录音的时长
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            audioRecorder.stopRecording();
            audioRecorder.play("test.wav");
        }).start();
    }

    public void file_click(){
        try {
            SpringContextUtil.getBean(ClientService.class).getOutput().writeObject(new Message("file",""));
            SpringContextUtil.getBean(ClientService.class).sendFile("src/main/java/com/example/client/config/login.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void file_enter(){
        file.setOpacity(1.0);
    }
    public void file_exit(){
        file.setOpacity(0.3);
    }
}