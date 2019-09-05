package sample;/**
 * Created by test on 9/30/2017.
 */

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SaleAsPerProduct  {

    public  ChoiceBox proIdList;
    public ObservableList<Table>  tableData=FXCollections.observableArrayList();

    public   TableView<Table> saleTableView;
    public List<String> idList;

    public Stage sales;
    public Stage saleasProduct()
    {
        Pane pane=getComponent();

        sales=new Common().customizeStage(pane,sales);

        return sales;
    };
    public Pane getComponent()
    {

        Text title=Common.getTitle("Sale as Per Product");

        Label proId=new Label("Product Id");

        proIdList=new ChoiceBox();
        proIdList.setPrefSize(200,30);
        try{

            proIdList.getItems().addAll(setChoiceBoxItem());
           }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }

        proIdList.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number>ob,
        Number old_val,Number new_val)->
        {
          String selectProductId=idList.get(new_val.intValue());

          try
          {
              searchProId(selectProductId);
          }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        });

        HBox hbox=new HBox(proId,proIdList);
        hbox.setSpacing(20);
        // ObservableList lis= FXCollections.observableArrayList("223","4444","1044");
       // proIdList.getItems().addAll();

        TableColumn<Table,String> prodName=Table.getProductNameCol();
        TableColumn<Table, Integer> quan=Table.getQuantityCol();
        TableColumn<Table,Double>  total=Table.getTotalCol();
        TableColumn<Table,LocalDate>  date=Table.getDateCol();
         saleTableView=new TableView();

        saleTableView.getColumns().addAll(prodName,quan,total,date);

        Button exportToExcel=new Button("Export To Excel");

        VBox container=new VBox(title,hbox,saleTableView,exportToExcel);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(20);
        container.setMargin(title,new Insets(15,20,0,40));



        return container;
    };

    public List setChoiceBoxItem() throws Exception

    {
        Connection con=dbConTest.getConnection();

        String SQLstatement="Select productId from productinfo";

        Statement stat=con.createStatement();

        ResultSet result=stat.executeQuery(SQLstatement);

        idList=new ArrayList();

        while(result.next())
        {
             idList.add(result.getString(1));

        }

        return idList;
    }

    public void searchProId(String myProductId) throws Exception
    {


        Integer proIdValue=Integer.valueOf(myProductId);

        Connection mycon=dbConTest.getConnection();
        String sqlStatement="Select productName,quantity,total,date from bill where productId="+proIdValue;
        Statement stat=mycon.createStatement();
        ResultSet resultSet=stat.executeQuery(sqlStatement);

          while(resultSet.next())
          {

              tableData.addAll(new Table(resultSet.getString(1),resultSet.getInt(2),resultSet.getDouble(3),resultSet.getDate(4)));

          }

        saleTableView.getItems().addAll(tableData);
       tableData.removeAll(tableData);



    }


}
