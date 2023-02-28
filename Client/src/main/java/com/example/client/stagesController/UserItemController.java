package com.example.client.stagesController;


import com.example.client.common.SpringContextUtil;
import com.example.client.entity.User;
import com.example.client.runningService.ClientService;
import com.example.client.service.UserService;
import com.example.client.viewsController.HomeController;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class UserItemController {
    public Label name;
    public Label id;
    public AnchorPane main;

    public void click(){
        System.out.println(id.getText());
        System.out.println(SpringContextUtil.getBean(ClientService.class).getUserId());
        List<User> list = SpringContextUtil.getBean(UserService.class).findFriend(SpringContextUtil.getBean(ClientService.class).getUserId());

        if(list.contains(SpringContextUtil.getBean(UserService.class).GetUser(id.getText()))){
            System.out.println("bb");
            SpringContextUtil.getBean(HomeController.class).roomName.setText(id.getText());
        }
        else{
            SpringContextUtil.getBean(UserService.class).AddFriend(SpringContextUtil.getBean(ClientService.class).getUserId(),id.getText());
            System.out.println("添加好友");
        }
    }
}
