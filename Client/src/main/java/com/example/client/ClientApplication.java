package com.example.client;

import com.example.client.common.DateUtil;
import com.example.client.common.FileUtil;
import com.example.client.common.SpringContextUtil;
import com.example.client.service.UserService;
import com.example.client.views.LoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class ClientApplication extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(ClientApplication.class, LoginView.class, new CustomSplashScreen(), args);

    }
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }

    @Override
    public Collection<Image> loadDefaultIcons() {
        return Arrays.asList(new Image(this.getClass().getClassLoader().getResource("icons/icon.png").toExternalForm()));
    }
}
