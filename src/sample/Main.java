package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.util.Optional;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.Stage;
public class Main extends Application
{

    public Stage myStage;
    public void start(Stage primaryStage) throws  Exception
    {
          Stage st=new Login().getLogin();

//        Pane mainPane=new Menu().autheticate();
    }
    public void getLoginPage()
    {
        new Login().getLogin();
    }
    /*
    @Override
      public void start(Stage primaryStage) throws Exception
      {
        Button laun=new Button("Show Dialog");
        Dialog di=new Dialog();

       Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        di.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);

        laun.setOnAction(ev->{

            Optional opt=di.showAndWait();
/*
       if(opt.isPresent())
       {
           if(opt.get()==ButtonType.OK)
           {
                 alert.show();
           }
       }
       */
        /*
        });
        Button ple=(Button) di.getDialogPane().lookupButton(ButtonType.OK);

        ple.addEventFilter(ActionEvent.ACTION, event ->
        {
            Optional<ButtonType>  ds=alert.showAndWait();
            //if((ds.get()==ButtonType.OK))
            //{
            event.consume();
            //}
        });
        BorderPane bpane=new BorderPane();
        bpane.setCenter(laun);
        Scene scene=new Scene(bpane,200,200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    */
}
