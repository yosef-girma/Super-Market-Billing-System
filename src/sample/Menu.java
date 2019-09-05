package sample;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.layout.Pane;
import javafx.geometry.*;
/**
 * Created by test on 10/3/2017.
 */
public class Menu
{


    public Stage myStage;
    public Stage primaryStage=new Stage();

      public void MenuTable() {

       Label addCasher = new Label("  Add Cashiers");
       Label addPro = new Label("Add Products");
       Label viewCasherDetail = new Label("View Casheries Details     ");

       Label CasherDetailByDate = new Label("Casher Details By Date   ");
       Label viewTrans = new Label("View Transactions     ");
       Label inventory = new Label("Inventory    ");
       Label updatestock = new Label("Update Stock   ");
       Label sale = new Label("Sale as per Product   ");
       Label saleasPerDay = new Label("Saler as Per Day    ");
       Label logout = new Label("Logout");

       //Action on Mouse Click

       addCasher.setOnMouseClicked((MouseEvent ev) ->
       {
           try {
               myStage = new AddCasher().addCasherDetail();
           } catch (Exception e) {
               e.printStackTrace();
           }
       });

       addPro.setOnMouseClicked((MouseEvent ev) ->
       {
           //myStage.close();
           myStage = new AddProdDetail().addProduct();
       });

       viewCasherDetail.setOnMouseClicked((MouseEvent ev) ->
       {
           new ViewCasherDetail().viewCasher();

       });

       viewTrans.setOnMouseClicked((MouseEvent ev) ->
       {

           myStage = new ViewTrans().viewTranaction();

       });
       CasherDetailByDate.setOnMouseClicked((MouseEvent ev) ->
       {

           myStage = new ViewCasherDetail().viewCasherDetailByDate();

       });
       inventory.setOnMouseClicked((MouseEvent ev) ->
       {
           myStage = new InventoryData().returnInventoryStage();
       });
       updatestock.setOnMouseClicked((MouseEvent ev) ->
       {
           new UpdateStock().updateStock();
       });
       sale.setOnMouseClicked((MouseEvent ev) ->
       {
           myStage = new SaleAsPerProduct().saleasProduct();
       });
       saleasPerDay.setOnMouseClicked((MouseEvent ev) ->
       {
               myStage = new SalesAsPerDay().salePerDay();
       });
       logout.setOnMouseClicked(eve ->
       {
           primaryStage.close();
           new Login().getLogin();

       });

       HBox detail = new HBox(new Label(), addCasher, addPro, viewCasherDetail, viewTrans, CasherDetailByDate, inventory, updatestock, sale, saleasPerDay, logout);
       detail.setSpacing(20);
       detail.setPrefHeight(30);
       detail.setPadding(new Insets(10));


       ToolBar toolbar = new ToolBar();
       toolbar.setCursor(Cursor.HAND);
       toolbar.getItems().addAll(detail);
       //toolbar.setStyle("-fx-background-color:white;");
       toolbar.setPrefWidth(30);

          BorderPane mainPane = new BorderPane();
          mainPane.setTop(toolbar);
          mainPane.setPrefSize(1000, 700);

          Scene scene=new Scene(mainPane);
          primaryStage.setScene(scene);
          primaryStage.setMinWidth(800);
          primaryStage.setMinHeight(600);
          primaryStage.setWidth(1377);
          primaryStage.setHeight(740);
          primaryStage.setMaxWidth(1378);
          primaryStage.setMaxHeight(740);
          primaryStage.setX(0);
          primaryStage.setY(0);
          primaryStage.setTitle("Super Market Billing System");

          primaryStage.show();
      }
}
