package sample;

/**
 * Created by test on 10/5/2017.
 */
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import  javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class CasherMenu
{
   public  Label loginTimeValue;

    public Stage casherStage;
    public void myCasherMenu(String username)
    {
        Label bill=new Label("Bill");

        Label pro_info=new Label("Product Information");
        Label logout=new Label("Logout");
        HBox menuItem=new HBox(bill,pro_info,logout);
        menuItem.setSpacing(30);
        menuItem.setPadding(new Insets(10,10,10,25));





        ToolBar toolbar = new ToolBar();

        toolbar.setCursor(Cursor.HAND);
        toolbar.getItems().addAll(menuItem);
        toolbar.setPrefHeight(40);

        ToolBar statusbar=new ToolBar();
        Label accountType=new Label("Logged in Casher");
        Label account=new Label();
        account.setText(username);

        Label loginTime=new Label("Login Time");
        LocalTime lo= LocalTime.now();
        loginTimeValue=new Label(lo.toString());

        HBox statusElement=new HBox(accountType,account,loginTime,loginTimeValue);
        statusElement.setSpacing(40);

        statusbar.getItems().add(statusElement);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(toolbar);
        mainPane.setBottom(statusbar);

        mainPane.setStyle("-fx-background-color:gray;");
        mainPane.setPrefSize(1000, 700);

        bill.setOnMouseClicked(ev ->
        {
            Pane pane=new CasherBill().getBillStage();

            mainPane.setCenter(pane);
            mainPane.setStyle("-fx-background-color:white");
            //casherStage.show();
        });
        pro_info.setOnMouseClicked(ev->
        {
            mainPane.setCenter(null);
            mainPane.setStyle("-fx-background-color:gray;");
            new Product_Info().product_info_();

        });


        logout.setOnMouseClicked(ev ->
        {
            try{
                saveStayTime();
             }
            catch(Exception evv)
            {
                evv.printStackTrace();


            }


            casherStage.close();
            new Login().getLogin();
        });

        Scene scene=new Scene(mainPane);
        casherStage=new Stage();
        casherStage.setScene(scene);
        casherStage.setMinWidth(800);
        casherStage.setMinHeight(600);
        casherStage.setWidth(1377);
        casherStage.setHeight(740);
        casherStage.setMaxWidth(1378);
        casherStage.setMaxHeight(740);
        casherStage.setX(0);

        casherStage.setY(0);
        casherStage.setTitle("                                   Super Market Billing System                                               ");
        casherStage.show();

    }

    public void saveStayTime() throws Exception
    {

     Connection con=dbConTest.getConnection();

     String sqlstat="insert into logintime values(?,?,?,?,?)";

     PreparedStatement st=con.prepareStatement(sqlstat);

     st.setString(1,String.valueOf(Login.getUserName()));
     st.setTime(2, Time.valueOf(LocalTime.parse(loginTimeValue.getText())));
     st.setTime(3, Time.valueOf(LocalTime.now()));
     st.setInt(4,3);
     st.setDate(5, Date.valueOf(LocalDate.now()));
      st.executeUpdate();


    }
}
