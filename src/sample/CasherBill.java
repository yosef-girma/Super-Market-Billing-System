package sample;

/**
 * Created by test on 10/5/2017.
 */
import com.sun.glass.ui.SystemClipboard;
import com.sun.glass.ui.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static java.time.LocalDate.*;

public class CasherBill
{


    public Double totalAmount=0.0;


    public void setTotal(Double eachTotal)
    {

        totalAmount = getTotal()+ eachTotal;

    }
    public Double getTotal()
    {

        return totalAmount;
    };




    public Stage billStage;

    public  TableView<Table> billTable;

    public Label proId;
    public TextField proIdFld;

    public Label proName;
    public TextField proNameFld;

    public Label price;
    public TextField  priceFld;

    public Label quan;
    public TextField quanFld;

    public Label discount;
    public TextField discountFld;

    public Pane getBillStage()
    {

        Label billNo=new Label("Bill No");
        TextField billField=new TextField();

        billField.setEditable(false);

        try {
            billField.setText(generateBillno().toString());    }
        catch(Exception ev) {     ev.printStackTrace();        }
        billField.setStyle("-fx-background-color:orange");
        
        HBox head=new HBox(billNo,billField);
        head.setSpacing(20);


         proId=new Label("Product Id");
         proIdFld=new TextField();

         // find button
         Button search=new Button("Find");

         Image img=new Image(this.getClass().getResourceAsStream("search.jpg"));

         ImageView imgv=new ImageView(img);
         imgv.prefWidth(40);
         imgv.prefHeight(40);

         //search.setGraphic(imgv);
         search.setPrefSize(100,30);
         proName=new Label("Product Name");
         proNameFld=new TextField();
         proNameFld.setEditable(false);
         price=new Label("Price");
         priceFld=new TextField();
         priceFld.setEditable(false);
         quan=new Label("Quantity");
         quanFld=new TextField();
         discount=new Label("Discount");
         discountFld=new TextField();

         search.setOnAction(event ->
         {
             try
             {
                 searchForData(proIdFld.getText());
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         });

         Button add=new Button("      Add          ");


         Button reset=new Button("     Reset        ");

          reset.setOnAction((ActionEvent ac) ->
          {
              proIdFld.clear();
              proNameFld.clear();
              quanFld.clear();
              discountFld.setText("0");
              priceFld.clear();
              proIdFld.clear();

          });

         HBox hbox=new HBox(add,reset);
         hbox.setSpacing(15);
         GridPane gpane=new GridPane();
         gpane.setVgap(20);
         gpane.setHgap(30);
         gpane.addRow(0,proId,proIdFld,search);
         gpane.addRow(1,proName,proNameFld);
         gpane.addRow(2,price,priceFld);
         gpane.addRow(3,quan,quanFld);
         gpane.addRow(4,discount,discountFld,new Label("%"));
         gpane.addRow(5,hbox);

         GridPane.setHalignment(hbox, HPos.RIGHT);
         gpane.setStyle("-fx-border:2px;");

         VBox leftContainer=new VBox(head,gpane);
         leftContainer.setSpacing(34);
         leftContainer.setPadding(new Insets(30,20,10,30));

         // Right side

 /// this is the bill table view
         billTable=billTable();

        Label billDate=new Label("Bill Date");

         Label date=new Label();

         date.setText(now().toString());
         HBox bill=new HBox(billDate,date);
         bill.setSpacing(50);

         StackPane stackPane=new StackPane();
         stackPane.setPrefSize(500,340);

         stackPane.setStyle("-fx-background-color:gray");
         stackPane.getChildren().add(billTable);
         billTable.setVisible(false);


        Label total=new Label("Total");

        TextField totalFld=new TextField();


        totalFld.setEditable(false);


        Button calcuTotal=new Button("       Calculate Total Amount      ");
        calcuTotal.setOnAction(eve ->
        {
         totalFld.setText(String.valueOf(getTotal()));
        });


        HBox bottom=new HBox(calcuTotal,total,totalFld);
        bottom.setSpacing(40);

        Button expToExcel=new Button("      Export To Excel    ");

        expToExcel.setOnAction(ev ->
        {
            FileChooser saveDialog=new FileChooser();

            File initialDirPath=new File(System.getProperty("user.home"));
            saveDialog.setInitialDirectory(initialDirPath);
            saveDialog.setInitialFileName("custombill");
            saveDialog.setTitle("   Export To Excel   ");

            Stage bu=new Stage();
            bu.setScene(new Scene(new HBox(billTable)));
            saveDialog.showSaveDialog(bu);

        });

        VBox rightContainer=new VBox();

        rightContainer.getChildren().addAll(bill,stackPane,bottom,expToExcel);
        rightContainer.setSpacing(29);

        // Table for bill


        expToExcel.setAlignment(Pos.BOTTOM_RIGHT);

        HBox container=new HBox(leftContainer,rightContainer);
        container.setSpacing(40);
        container.setPrefSize(800,800);




        // adding event for the button add
        ObservableList<Table> list=FXCollections.observableArrayList();
        add.setOnAction(eve ->
        {

        // calculating total
           Double calculatedTotalvalue;

            Integer myQuantity=Integer.valueOf(quanFld.getText());

            Double myDiscount=Double.valueOf(discountFld.getText());
            if(myDiscount>85)
            {
                myDiscount=0.0;
            }

            Double  myPrice=Double.valueOf(priceFld.getText());

            calculatedTotalvalue=(myQuantity*myPrice);
            calculatedTotalvalue=calculatedTotalvalue-(calculatedTotalvalue*myDiscount/100);

            setTotal(calculatedTotalvalue);

            list.add(new Table(Integer.valueOf(proIdFld.getText()),proNameFld.getText(),myQuantity,myDiscount,myPrice,calculatedTotalvalue));

             billTable.getItems().addAll(list);
             billTable.setPrefSize(300,300);
             billTable.setFixedCellSize(30);
             billTable.setVisible(true);
            list.removeAll(list);

             try{
                 saveBillData(calculatedTotalvalue);
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        });


    return container;

    }

    public void saveBillData(Double finalTotal) throws Exception
    {
        Connection con=dbConTest.getConnection();

        Statement statement=con.createStatement();
        String insert="insert into bill values(?,?,?,?,?,?,?)";


        PreparedStatement pstatement=con.prepareStatement(insert);

        try{
            pstatement.setInt(1,generateBillno());
            pstatement.setInt(2,Integer.valueOf(proIdFld.getText()));
            pstatement.setString(3,proNameFld.getText());
            pstatement.setInt(4,Integer.valueOf(quanFld.getText()));
            pstatement.setDouble(5,finalTotal);
            pstatement.setString(6,Login.getUserName());
            pstatement.setDate(7, Date.valueOf(LocalDate.now()));
            pstatement.executeUpdate();

        }
        catch(Exception e)
        {
        e.printStackTrace();
        }

    }


    public TableView  billTable()
    {
        TableColumn<Table,Integer> proId=Table.getProdIdCol();

        TableColumn<Table,String> proName=Table.getProductNameCol();

        TableColumn<Table,Integer> quantity=Table.getQuantityCol();

        TableColumn<Table,Double> discount=Table.getDiscountCol();
        TableColumn<Table,Double> price=Table.getPriceCol();

        TableColumn<Table,Double> total=Table.getTotalCol();



       TableView billTview=new TableView();

      billTview.getColumns().addAll(proId,proName,quantity,discount,price,total);


      return  billTview;
    };
public void searchForData(String proId) throws Exception
{
    Connection con=dbConTest.getConnection();

    Statement stat=con.createStatement();

    if(proId!=null || proId!="")
    {
        Integer productId = Integer.valueOf(proId);
        String sqlStatement = "select productName,quantity,amount from productinfo where productId=" + productId;
        ResultSet reset = stat.executeQuery(sqlStatement);

        while(reset.next())
        {
          proNameFld.setText(reset.getString(1));
          quanFld.setText(String.valueOf(( reset.getInt(2))));
          priceFld.setText(String.valueOf(reset.getInt(3)));

        };
    }

}

    public Integer generateBillno() throws Exception
    {

        Connection con=dbConTest.getConnection();
        Statement st=con.createStatement();
        ResultSet reSet=st.executeQuery("select max(billno) from bill");
        reSet.next();
        Integer billno=reSet.getInt(1);

        st.close();
        con.close();

        return ++billno;
    }




}
