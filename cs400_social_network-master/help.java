package application;

import java.time.LocalDate;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class help extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also
  private List<String> args;
  ComboBox<String> cBox;

  private static final int WINDOW_WIDTH = 800;
  private static final int WINDOW_HEIGHT = 800;
  private static final String APP_TITLE = "CS400 MyFirstJavaFX";

  @Override
  public void start(Stage primaryStage) throws Exception {
    // vbox2
    Label label1 = new Label("");
    Button button1 = new Button("Load network");

    button1.setOnAction(value -> {
      label1.setText("1. Click file \n" + "2. Click upload \n" + "Or press ctrl U\n");
    });

    VBox vbox1 = new VBox(button1, label1);

    // vbox2
    Label label2 = new Label("");
    Button button2 = new Button("Save Network");

    button2.setOnAction(value -> {
      label2.setText("1. Click file \n" + "2. Click save \n" + "Or press shortcut ctrl S\n");
    });

    VBox vbox2 = new VBox(button2, label2);

    // vbox3
    Label label3 = new Label("");
    Button button3 = new Button("How to add friend");

    button3.setOnAction(value -> {
      label3.setText("1. Click add friend\n");
    });

    VBox vbox3 = new VBox(button3, label3);

    // vbox4
    Label label4 = new Label("");
    Button button4 = new Button("How to search for friends");

    button4.setOnAction(value -> {
      label4.setText("1. Type name of friend you want to search \n" + "2. Click search\n");
    });

    VBox vbox4 = new VBox(button4, label4);

    // vbox5
    Label label5 = new Label("");
    Button button5 = new Button("How to Log Out");

    button5.setOnAction(value -> {
      label5.setText("1. Click on logout button\n");
    });

    VBox vbox5 = new VBox(button5, label5);

    // vbox6
    Label label6 = new Label("");
    Button button6 = new Button("How to remove friend");

    button6.setOnAction(value -> {
      label6.setText("1. Click remove friend button\n");
    });

    VBox vbox6 = new VBox(button6, label6);

    // vbox7
    Label label7 = new Label("");
    Button button7 = new Button("How to undo and redo changes");

    button7.setOnAction(value -> {
      label7.setText("Option 1: click edit. Then click undo or redo button\n"
          + "Option 2: type Control+x to undo or Control+y\n");
    });

    VBox vbox7 = new VBox(button7, label7);

    // vbox8
    Label label8 = new Label("");
    Button button8 = new Button("How to Close Window without warnings");

    button8.setOnAction(value -> {
      label8.setText("1. Type Control + F\n");
    });

    VBox vbox8 = new VBox(button8, label8);

    // vbox9
    Label label9 = new Label("");
    Button button9 = new Button("How to find mutual friends \n");

    button9.setOnAction(value -> {
      label9.setText("1. Click on friend.\n" + "2. Click search.\n "
          + "3. Mutual friends will Appear in window\n");
    });

    VBox vbox9 = new VBox(button9, label9);

    // vbox10
    Label label10 = new Label("");
    Button button10 = new Button("How to find shortest path of friends between two users\n");

    button10.setOnAction(value -> {
      label10.setText("1. Select person A.\n" + "2. Select person B\n" + "3. Click find path button \n");
    });

    VBox vbox10 = new VBox(button9, label9);

    // Label 10
    Label l10 = new Label("Instructions");


    VBox root = new VBox(l10, vbox1, vbox3, vbox4, vbox5, vbox6, vbox7, vbox8, vbox9, vbox10);
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    mainScene.getStylesheets().add("ateam69.css");
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * This method calls launch which calls start
   * 
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}


