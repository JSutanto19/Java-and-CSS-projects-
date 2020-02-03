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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class UploadFileSample extends Application {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final String APP_TITLE = "Social Media";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        root.setTop(new Label(APP_TITLE));
        Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        Button openButton = new Button("upload file");
        Label fileLabel = new Label();
        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().add(new ExtensionFilter("Select a .txt file", "*.txt"));
                File myFile = fileChooser.showOpenDialog(primaryStage);
                if(myFile != null) {
                    fileLabel.setText(myFile.getName() + " has been successfully uploaded!");
                    try {
                        Scanner scnr = new Scanner(myFile);
                        while(scnr.hasNextLine()) {
                            String str = scnr.nextLine();
                            List<String> strs = Arrays.asList(str.split("\\s* \\s*"));
                            System.out.println(strs);
                        }
                    } catch (FileNotFoundException e) {
                    }
                }                
            }
        });        
        HBox hbox = new HBox(openButton, fileLabel);
        root.setLeft(hbox);
        
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(mainScene);
            primaryStage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
           launch(args);
    }
}