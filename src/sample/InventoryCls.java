package sample;

/**
 * Created by test on 9/28/2017.
 */
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.geometry.*;
public class InventoryCls {

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
       VBox heading=new VBox(title,contain);
       heading.setSpacing(20);
       heading.setAlignment(Pos.TOP_CENTER);
       heading.setMargin(title,new Insets(30,20,10,40));

       contain.setSpacing(10);

       TableView inventoryTableView=inventoryTable();
        inventoryTableView.setPrefSize(300,200);
        VBox bound=new VBox(contain,inventoryTableView);
        bound.setSpacing(20);

        return bound;
    };

    public TableView  inventoryTable()
    {

     TableView<Table> tview=new TableView();

     TableColumn<Table,String>  proId=Table.getProdIdCol();
     TableColumn<Table,String>  proName=Table.getProductNameCol();
     TableColumn<Table,Integer> quan=Table.getQuantityCol();
     tview.getColumns().addAll(proId,proName,quan);



        return tview;
    };

}
