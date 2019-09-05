/**
 * Created by test on 9/23/2017.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import java.util.Optional;

public class Login extends Application
{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {





        Scene scene=new Scene(new HBox(new Button("")),40,222);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(800);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(1000);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMaxHeight(1000);
        primaryStage.setTitle("Super Market Billing System");
        primaryStage.show();
    }
}
