package com.example.rateyourmovie;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController{
    @FXML
    private TextField enterUsernameField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Button loginButton, cancelButton;
    @FXML
    private Label messegeLabel;
    //close = cancelButton
    @FXML
    private Button minimizeButton;

    private Stage registerStage, loginStage;

    private double x= 0;
    private double y= 0;


    public void minimize(){
        Stage minimizeStage = (Stage) minimizeButton.getScene().getWindow();
        minimizeStage.setIconified(true);
    }

    public void loginButtonAction() {
        if(!enterUsernameField.getText().isBlank() && !enterPasswordField.getText().isBlank()){
            validateLogin();
        }else{
            messegeLabel.setText("Please enter username and password");
        }
    }

    public void cancelButtonAction() {
        loginStage = (Stage) cancelButton.getScene().getWindow();
        loginStage.close();
        Platform.exit();
    }
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String verifyLogin = "select count(1) from user_account where user_name = '" + enterUsernameField.getText() +
                "' and password = '"+ enterPasswordField.getText() + "';";

        try {
            Statement statement = connectionDB.createStatement();
            ResultSet qResult = statement.executeQuery(verifyLogin);

            while(qResult.next()){
                if(qResult.getInt(1) == 1){
                    messegeLabel.setText(null);
                    loginButton.getScene().getWindow().hide(); // hide your login form

                    Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
                    Stage appStage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) -> {
                        appStage.setX(event.getScreenX() - x);
                        appStage.setY(event.getScreenY() - y);

                    });

                    appStage.initStyle(StageStyle.TRANSPARENT);

                    appStage.setScene(scene);
                    appStage.show();

                } else {
                    messegeLabel.setText("Invalid Login, Please Try Again");
                }
            }


        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm(){
        try{
            Stage registerStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            registerStage.initStyle(StageStyle.UNDECORATED); // remove the title bar
            registerStage.setScene(scene);
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}