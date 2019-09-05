package sample;

/**
 * Created by test on 10/5/2017.
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.cell.*;
import javafx.geometry.Insets;
import  javafx.collections.*;
import java.sql.*;
public class Product_Info
{

    public Stage product_info_stage;
   public Label proId;
   public TextField prodIdField;
   public Button search;


    public Stage product_info_()
    {


        Text title=Common.getTitle("Product-Information");
        proId = new Label("Product Id");
        prodIdField = new TextField();
        search = new Button("Search");
        HBox straight=new HBox(title,proId,prodIdField,search);
         straight.setSpacing(50);
        TableColumn<Table,Integer> id=Table.getProdIdCol();
        TableColumn<Table,String> proName=Table.getProductNameCol();
        TableColumn<Table,Integer> quan=Table.getQuantityCol();

        TableColumn<Table,Integer> amt=new TableColumn<>("Amount");
        amt.setCellValueFactory(new PropertyValueFactory("amount"));

        TableColumn<Table,String> descri=new TableColumn<>("Description");
        descri.setCellValueFactory(new PropertyValueFactory("description"));

        TableView tview=new TableView();

        tview.getColumns().addAll(id,proName,quan,amt,descri);
        tview.setPrefHeight(200);
        tview.setPrefWidth(300);
        VBox tableLayout=new VBox();
        tableLayout.setPrefSize(400,400);
        tableLayout.setStyle("-fx-background-color:gray;");
      try {
          ObservableList list=getTableData();
          tview.getItems().addAll(list);
      }
      catch(Exception e)
      {

      }

        StackPane stackPane=new StackPane(tableLayout,tview);

        VBox vbo=new VBox(straight,tview);
        vbo.setSpacing(50);
        vbo.setAlignment(Pos.CENTER);
        vbo.setMargin(straight,new Insets(20,10,10,30));

        product_info_stage=new Common().customizeStage(vbo,product_info_stage);


       return product_info_stage;
    };

  public ObservableList getTableData() throws Exception
  {
      ObservableList list=FXCollections.observableArrayList();

      Connection con=dbConTest.getConnection();

      Statement st=con.createStatement();
      String query="select * from productinfo";
      ResultSet res=st.executeQuery(query);

      while(res.next())
      {
       list.add(new Table(res.getInt(1),res.getString(2),res.getInt(3),res.getInt(4),res.getString(5)));


  };

return list;
}
    }