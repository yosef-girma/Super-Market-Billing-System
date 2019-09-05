package sample;

/**
 * Created by test on 9/30/2017.
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SaleasPday {
public Stage salesStage;

    public Stage salePerDay()
    {

       VBox head=new Common().range("Sales As Per Day");


        TableView<Table> saleTableView=new TableView();

        TableColumn<Table,String> proName=Table.getProductNameCol();
        TableColumn<Table, Integer> quan=Table.getQuantityCol();
        TableColumn<Table,Double>  total=Table.getTotalCol();



        saleTableView.getColumns().addAll(proName,quan,total);

        Button exportToExcel=new Button("Export To Excel");

        VBox container=new VBox(head,saleTableView,exportToExcel);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(20);
        container.setMargin(head,new Insets(15,20,0,40));

        salesStage=new Common().customizeStage(container,salesStage);
        return salesStage;
    };





}
