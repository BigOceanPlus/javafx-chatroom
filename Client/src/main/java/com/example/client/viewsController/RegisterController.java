package com.example.client.viewsController;

import com.example.client.ClientApplication;
import com.example.client.common.SpringContextUtil;
import com.example.client.service.UserService;
import com.example.client.views.LoginView;
import com.example.client.views.RegisterView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FXMLController
public class RegisterController implements Initializable {

    public TextField user_field;
    public PasswordField password_field;
    public PasswordField password2_field;
    public Label user_label;
    public Label label2;
    public Label label3;

    public Button btn1;
    public Button btn2;
    public Label clue;

    @Autowired
    RegisterView registerView;

    private Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent parent = registerView.getView();

                RegisterController.this.stage = (Stage) parent.getScene().getWindow();
                stage.setTitle("注册");
                stage.setResizable(false);
            }
        });
    }

    // 用户标签
    public void user_field_exit(){
        if(user_field.getText().length() > 0){
            if(SpringContextUtil.getBean(UserService.class).Verification(user_field.getText())){
                user_label.setTextFill(new Color(1.0,0,0,1.0));
                user_label.setText("用户已存在");
            }
            else{
                user_label.setTextFill(new Color(0,0,1.0,1.0));
                user_label.setText("用户可用");
            }
        }
        else {
            user_label.setTextFill(new Color(1.0,0,0,1.0));
            user_label.setText("请输入用户");
        }
    }

    // 密码标签
    public void password_field_exit(){
        String str = password_field.getText();
        String patternStr = "^[0-9a-zA-Z]{6,18}$";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(str);
        if(str.length() > 0) {
            if (!matcher.matches())
                label2.setText("密码必须为6-18位数字字母");
            else
                label2.setText("");
        }
        else
            label2.setText("请输入密码");

        if(!password_field.getText().equals(password2_field.getText()))
            label3.setText("两次密码输入不一致");
        else
            label3.setText("");
    }

    // 密码确认标签
    public void password2_field_exit(){
        if(password2_field.getText().length() == 0)
            label3.setText("请确认密码");
        else if(!password_field.getText().equals(password2_field.getText()))
            label3.setText("两次密码输入不一致");
        else
            label3.setText("");
    }

    // 注册
    public void btn1_action(){
        if(user_label.getText().equals("用户可用") && label2.getText().equals("") && label3.getText().equals("")){
            if(!SpringContextUtil.getBean(UserService.class).Verification(user_field.getText())) {
                clue.setText("注册成功");
                SpringContextUtil.getBean(UserService.class).Register(user_field.getText(), password_field.getText());
            }
            else {
                user_label.setTextFill(new Color(1.0,0,0,1.0));
                user_label.setText("用户已存在");
                clue.setText("注册失败");
            }
        }
        else
            clue.setText("注册失败");
    }
    public void btn1_enter(){
        btn1.setOpacity(1.0);
    }
    public void btn1_exit(){
        btn1.setOpacity(0.4);
    }

    // 返回登录
    public void btn2_action(){
        ClientApplication.showView(LoginView.class);
    }
    public void btn2_enter(){
        btn2.setOpacity(1.0);
    }
    public void btn2_exit(){
        btn2.setOpacity(0.4);
    }
}
