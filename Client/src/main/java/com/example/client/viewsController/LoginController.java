package com.example.client.viewsController;

import com.example.client.ClientApplication;
import com.example.client.common.FileUtil;
import com.example.client.common.SpringContextUtil;
import com.example.client.runningService.ClientService;
import com.example.client.service.UserService;
import com.example.client.views.HomeView;
import com.example.client.views.LoginView;
import com.example.client.views.RegisterView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class LoginController implements Initializable {

    public Label user_label;
    public Label password_label;
    public Button btn1;
    public Button btn2;
    public Button del1;
    public Button del2;
    public TextField user_field;
    public PasswordField password_field;
    public RadioButton choice1;
    public RadioButton choice2;
    public Label clue;

    @Autowired
    LoginView loginView;

    private Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent parent = loginView.getView();

                LoginController.this.stage = (Stage) parent.getScene().getWindow();
                stage.setTitle("登录");
                stage.setResizable(false);

                String[] res = SpringContextUtil.getBean(FileUtil.class).PasswordSavedVerification("src/main/java/com/example/client/config/login.dat");
                user_field.setText(res[0]);
                password_field.setText(res[1]);
            }
        });
    }

    // 标签控制
    public void password_label_enter(){
        password_label.setTextFill(new Color(0,0,1.0,0.8));
    }

    public void password_label_exit(){
        password_label.setTextFill(new Color(0,0,0,1));
    }

    public void user_label_enter(){
        user_label.setTextFill(new Color(0,0,1.0,0.8));
    }

    public void user_label_exit(){
        user_label.setTextFill(new Color(0,0,0,1));
    }

    // 登录
    public void btn1_action(){
        if(SpringContextUtil.getBean(UserService.class).Login(user_field.getText(),password_field.getText())){

            if(choice1.isSelected() && choice2.isSelected())
                SpringContextUtil.getBean(FileUtil.class).WriteFile("src/main/java/com/example/client/config/login.dat",new String[]{"true", user_field.getText(),"true",password_field.getText()});
            else if(choice1.isSelected())
                SpringContextUtil.getBean(FileUtil.class).WriteFile("src/main/java/com/example/client/config/login.dat",new String[]{"true", user_field.getText(),"false"});
            else
                SpringContextUtil.getBean(FileUtil.class).WriteFile("src/main/java/com/example/client/config/login.dat",new String[]{"false"});

            clue.setText("登录成功");
            SpringContextUtil.getBean(ClientService.class).setUserId(user_field.getText());
            SpringContextUtil.getBean(ClientService.class).run();
            ClientApplication.showView(HomeView.class);
            //TestStage();
        }
        else
            clue.setText("账户密码错误");
    }
    public void btn1_enter(){
        btn1.setOpacity(1.0);
    }
    public void btn1_exit(){
        btn1.setOpacity(0.5);
    }
    // 注册
    public void btn2_action(){
        ClientApplication.showView(RegisterView.class);
    }
    public void btn2_enter(){
        btn2.setOpacity(1.0);
    }
    public void btn2_exit(){
        btn2.setOpacity(0.5);
    }

    // 删除
    public void del1_action(){
        user_field.setText("");
    }
    public void del2_action(){
        password_field.setText("");
    }

    // 选择
    public void choice_action(){
        if(choice2.isSelected())
            choice1.setSelected(true);
        else
            choice1.setSelected(false);
    }

    private void TestStage(){
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChatItem.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("测试");
            stage.getIcons().add(new Image("icons/icon.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test(){
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/test.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("测试");
            stage.getIcons().add(new Image("icons/icon.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
