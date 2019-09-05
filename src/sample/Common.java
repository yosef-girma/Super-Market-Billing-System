package sample;/**
 * Created by test on 9/26/2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
public class Common {


    public Stage customizeStage(Pane pane, Stage primaryStage) {

        pane.setStyle("-fx-background-color:white");

        Scene scene = new Scene(pane);

        primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.setWidth(550);
        primaryStage.setHeight(480);
        primaryStage.setX(300);
        primaryStage.setY(100);
        primaryStage.show();

        return primaryStage;

    }

    public static Text getTitle(String Title)
    {

     Text   plate=new Text(Title);
     plate.setFill(Color.GREEN);
        plate.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,18));

       return plate;
    };

    public VBox range(String titleText)
    {



        Text title=getTitle(titleText);
        title.setTextAlignment(TextAlignment.CENTER);
        Label fromLabel=new Label("From");
        DatePicker fromDate=new DatePicker();
        Label toLabel=new Label("To");
        DatePicker to=new DatePicker();
        Button search=new Button("Search");

        HBox hbox=new HBox(10);

        hbox.getChildren().addAll(fromLabel,fromDate,toLabel,to,search);

        VBox vbox=new VBox(title,hbox);
         vbox.setSpacing(15);
        return vbox;

    }




}
