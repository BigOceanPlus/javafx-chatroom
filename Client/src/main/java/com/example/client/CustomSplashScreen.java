package com.example.client;

import de.felixroske.jfxsupport.SplashScreen;
import javafx.scene.Parent;

public class CustomSplashScreen extends SplashScreen {
    @Override
    public String getImagePath() {
        return super.getImagePath();//"/images/welcome.gif";
    }
    @Override
    public Parent getParent() {
        // TODO Auto-generated method stub
        return super.getParent();
    }

    @Override
    public boolean visible() {
        // TODO Auto-generated method stub
        return super.visible();
    }
}
