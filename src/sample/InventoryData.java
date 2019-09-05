package sample;/**
 * Created by test on 9/30/2017.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;


public class InventoryData {

    public  Stage inventroyStage;
    public   Stage returnInventoryStage()
    {

        Pane pane=getComponent();
        inventroyStage=new Common().customizeStage(pane,inventroyStage);
        return inventroyStage;
    }
    public Pane getComponent()
    {
        Text title=Common.getTitle("Inventory");
        Label productId=new Label("Product Id");
        TextField proidField=new TextField();
        Button search=new Button("Search");
        HBox contain=new HBox(productId,proidField,search);
         contain.setAlignment(Pos.CENTER);
        contain.setSpacing(10);
        TableView inventoryTableView=inventoryTable();


        VBox table=new VBox(inventoryTableView);
         table.setStyle("-fx-background-color:gray");
        table.setPrefSize(300,200);

        VBox bound=new VBox(contain,table);
        bound.setSpacing(20);

        return bound;
    };

    public TableView  inventoryTable()
    {

        TableView<Table> tview=new TableView();
        TableColumn<Table,String> proId=Table.getProdIdCol();
        TableColumn<Table,String>  proName=Table.getProductNameCol();
        TableColumn<Table,Integer> quan=Table.getQuantityCol();
        tview.getColumns().addAll(proId,proName,quan);


        try{

            tview.getItems().addAll(getTableData());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }



        return tview;
    };
public ObservableList getTableData() throws Exception
{

    Connection con=dbConTest.getConnection();
    Statement stat=con.createStatement();
    String query="select productId,productName,quantity from productinfo";
    ResultSet  rset=stat.executeQuery(query);

    ObservableList list= FXCollections.observableArrayList();

    while(rset.next())
    {
        list.add(new Table(rset.getInt(1),rset.getString(2),rset.getInt(3)));

    }

return list;
}

}
