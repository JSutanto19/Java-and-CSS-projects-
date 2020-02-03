///////////////////////////////////////////////////////////////////////////////
// Title:            GraphVisualization.java
// Semester:         Fall 2019
//
// Author:           Ateam69
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class is the graph visualization of the friend's list
 */

package application;
 
import java.awt.Scrollbar;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class GraphVisualization extends Application {
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) {
    	
    	Pane root = new Pane();
    	Scene friendScene = new Scene (root , 800 , 500);

    	//Friends of the user
        ArrayList<String> friendList = new ArrayList<String>() ;
        friendList.add("Ali");
        friendList.add("Abu");
        friendList.add("Tom");
        friendList.add("Tommas");
        
    	
        //User node
        Text user = new Text("Main User");
        user.setStrokeWidth(100);
        user.setFont(Font.font("Brush Script", 50));
        
        StackPane userStackPane = new StackPane();
        Rectangle userRectangle = new Rectangle();
        userRectangle.setFill(Color.AQUA);
        userRectangle.setStroke(Color.BLUEVIOLET);
        userRectangle.setStrokeWidth(3);
        userRectangle.setWidth(user.getText().length() * 40);
        userRectangle.setHeight(100);
        userStackPane.getChildren().addAll(userRectangle,user);
        userStackPane.setLayoutX(friendScene.getWidth()/2 - userRectangle.getWidth() /2);
        userStackPane.setLayoutY(100);
        root.getChildren().add(userStackPane);
        

        
        //Friends of user node
        for (int i = 0 ; i < friendList.size() ; ++ i) {
        	
        	StackPane stack = new StackPane();
        	
        	
        	//The circle
        	Circle circle = new Circle ();
            circle.setFill(Color.AQUAMARINE);
            
        	//The circle if friendList is less than 4 so that the scene looks nicer
        	if (friendList.size() < 4) {
        		circle.setRadius(100);
        	}
        	
        	else {
        		circle.setRadius(friendScene.getWidth() / friendList.size() / 2);
        	}
        	
            circle.setStroke(Color.MEDIUMAQUAMARINE);
            circle.setStrokeWidth(3);
            
            //The text in the circle
            Text text = new Text (friendList.get(i));
            stack.getChildren().addAll(circle,text);
            stack.setLayoutX(i * friendScene.getWidth() / friendList.size());
            stack.setLayoutY(300);
        	
            //Start of the Line
            Line line = new Line();
            line.setStartX(userStackPane.getLayoutX() + userRectangle.getWidth()/2);
            line.setStartY(userStackPane.getLayoutY() + userRectangle.getHeight());
            
            
            //End of the Line
            line.setEndX(i * friendScene.getWidth() / friendList.size() + circle.getRadius());
            line.setEndY(300);

            root.getChildren().add(line);
            root.getChildren().add(stack);
        }
        
    	primaryStage.setScene(friendScene);

        primaryStage.show();
        
    }

}