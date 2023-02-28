package com.example.client.stagesController;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TestController {
    public VBox pane;
    public Button test;
    ObservableList<AnchorPane> oblist = FXCollections.observableArrayList();
    public ListView<AnchorPane> list = new ListView<AnchorPane>(oblist);

    public void test_action(){
        /*try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserItem.fxml"));
            AnchorPane an = (AnchorPane)root;
            for(Node node : an.getChildren()) {
                if(node.getId().equals("name")){
                    Label l = (Label) node;
                    l.setText("1234123123");
                }
            }
            list.setItems(oblist);
            oblist.add(an);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChatItem.fxml"));
            AnchorPane an = (AnchorPane)root;
            for(Node node : an.getChildren()) {
                if(node.getId().equals("content")){
                    Label l = (Label) node;
                    l.setText("777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777");
                }
            }
            an.setLayoutX(an.getLayoutX());
            pane.getChildren().add(an);

            root = FXMLLoader.load(getClass().getResource("/fxml/ChatItemMe.fxml"));
            an = (AnchorPane)root;
            for(Node node : an.getChildren()) {
                if(node.getId().equals("content")){
                    Label l = (Label) node;
                    l.setText("777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777");
                }
            }
            an.setLayoutX(an.getLayoutX());
            pane.getChildren().add(an);
            //oblist.add("123");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
