package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Login extends Application {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final String APP_TITLE = "Social Media";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        BorderPane loginRoot = new BorderPane();
        Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        Scene loginScene = new Scene(loginRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        root.setTop(new Label(APP_TITLE));
        loginRoot.setTop(new Label("Welcome to Social Network!"));

        // LOGIN
        Label usernameLabel = new Label("Username: ");
        Label pwLabel = new Label("Password: ");
        Label statusLabel = new Label("");
        
        TextField usernameText = new TextField("");
        usernameText.setPromptText("Enter Username");
        
        PasswordField pwText = new PasswordField();
        pwText.setPromptText("Enter Password");
        
        Button login = new Button("login");
        login.setOnMouseEntered(e -> login.setStyle("-fx-font-size:11pt;"));
        login.setOnMouseExited(e -> login.setStyle("-fx-font-size:10pt;"));
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                String username = usernameLabel.getText().toString();
                String pw = pwLabel.getText().toString();
                // FIXME PASSWORD CHECKING
                if(true) {
//                    statusLabel.setText("Welcome back " + username);
//                    statusLabel.setTextFill(Color.GREEN);
                    primaryStage.setScene(mainScene);
                    primaryStage.show();
                } else{
                    statusLabel.setText("Couldn't find username or password! Try again later!");
                    statusLabel.setTextFill(Color.RED);
                }
            }
        });
        
        HBox usernameBox = new HBox(usernameLabel, usernameText);
        HBox pwBox = new HBox(pwLabel, pwText);
        VBox loginBox = new VBox(usernameBox, pwBox, login, statusLabel);
        loginRoot.setCenter(loginBox);
        
        /*
         * DO SOMETHING USING ROOT HERE
         */
        
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
           launch(args);
    }
}
