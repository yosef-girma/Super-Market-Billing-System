package sample;/**
 * Created by test on 9/28/2017.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.time.LocalTime;
import java.time.LocalDate;
import javafx.beans.property.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.*;
public class ViewTrans {
Stage transStage;

    public Stage viewTranaction()
    {
        VBox vbox=new Common().range("View Transaction");

        VBox totalContainer=new VBox(10);
        totalContainer.setMargin(vbox,new Insets(20,10,0,40));

        totalContainer.getChildren().addAll(vbox,getTransTable());

        new Common().customizeStage(totalContainer,transStage);

        return transStage;

           }
    public TableView  getTransTable()
    {
        TableView tview=new TableView();

        TableColumn<TransTable,String> billno=new TableColumn("BillNo");
        TableColumn<TransTable,String> product=new TableColumn("Product");
        TableColumn<TransTable,Double> total=new TableColumn("Total");
        TableColumn<TransTable,String> casherid=new TableColumn("Casherid");
        TableColumn<TransTable,LocalDate> date=new TableColumn("Date");

         billno.setCellValueFactory(new PropertyValueFactory("billno"));
         product.setCellValueFactory(new PropertyValueFactory("product"));

         total.setCellValueFactory(new PropertyValueFactory("total"));

         casherid.setCellValueFactory(new PropertyValueFactory("casherid"));

         date.setCellValueFactory(new PropertyValueFactory("date"));

         tview.getColumns().addAll(billno,product,total,casherid,date);
         //TableCell s=new TableCell();
         tview.setFixedCellSize(5);
         return tview;

    }

    public class TransTable
    {

        SimpleStringProperty billno;
        SimpleStringProperty product;
        SimpleDoubleProperty total;
        SimpleStringProperty casherid;

        SimpleStringProperty date;

        public TransTable(String product,Double total,String date)
        {
            this.product =new SimpleStringProperty(product);
            this.total =new SimpleDoubleProperty(total);
            this.date=new SimpleStringProperty(date);

        }

        public TransTable(String billno,String product,Double total,String casherid,String date)
        {
            this(product,total,date);
            this.billno  =new SimpleStringProperty(billno);
             this.casherid=new SimpleStringProperty(casherid);

        };

        public void setbillno(String billno)
        {

            this.billno.set(billno);
        }
        public String getbillno()
        {
            return this.billno.get();
        }
        public void setproduct(String product)
        {
            this.product.set(product);
        }

        public String getproduct()
        {
            return this.product.get();
        }
        public void setcasherid(String casherid)
        {

            this.casherid.set(casherid);
        }

        public String getcasherid()
        {
            return this.casherid.get();
        }
        public void settotal(Double total)
        {

            this.total.set(total);
        }
        public Double gettotal()
        {

            return this.total.get();
        }
        public void setDate(String date)
        {

            this.date.set(date);
        }
        public String getDate()
        {
            return this.date.get();
        }
    };



    }

