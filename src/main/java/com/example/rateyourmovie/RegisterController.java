package com.example.rateyourmovie;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private Button minimizeButton;
    @FXML
    private TextField firstNameTextField, lastNameTextField, userNameTextField;
    @FXML
    private PasswordField passWordTextField, confirmPasswordTextField;
    @FXML
    private Button registerButton,closeButton;
    @FXML
    private Label messegeLabel, marchPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messegeLabel.setText(null);
        marchPassword.setText(null);

    }

    public void minimize(){
        Stage minimizeStage = (Stage) minimizeButton.getScene().getWindow();
        minimizeStage.setIconified(true);
    }

    public void regiterButtonOnAction(){
        if(confirmPasswordTextField.getText().equals(passWordTextField.getText())){
            marchPassword.setText(null);
            registerUser();
        } else {
            marchPassword.setText("Password Does Not Match");
            messegeLabel.setText(null);
        }

    }

    public void closeButtonOnAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String firstName = firstNameTextField.getText(), lastName = lastNameTextField.getText(),
                userName = userNameTextField.getText(), password = passWordTextField.getText();

        String insertField = "insert into user_account (lastname, firstname, user_name, password) values('",
                insertValues = firstName +"','" + lastName + "','"+ userName +"','"+password+"');";
        String insertToRegister = insertField + insertValues;

        try{
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(insertToRegister);

            messegeLabel.setText("User Registered Successfully");
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
