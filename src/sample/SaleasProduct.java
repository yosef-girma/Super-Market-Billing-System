package sample;

/**
 * Created by test on 9/30/2017.
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.util.Optional;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.collections.*;
import java.time.*;
public class SaleasProduct {

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

     ChoiceBox proIdList=new ChoiceBox();
     HBox hbox=new HBox(proId,proIdList);
      hbox.setSpacing(20);
     proIdList.setPrefSize(200,30);
     ObservableList lis=FXCollections.observableArrayList("223","4444","1044");
     proIdList.getItems().addAll();

     TableColumn<Table,String> prodName=Table.getProdIdCol();
     TableColumn<Table, Integer> quan=Table.getQuantityCol();
     TableColumn<Table,Double>  total=Table.getTotalCol();
     TableColumn<Table,LocalDate>  date=Table.getDateCol();
     TableView saleTableView=new TableView();

     saleTableView.getItems().addAll(prodName,quan,total,date);

     Button exportToExcel=new Button("Export To Excel");

     VBox container=new VBox(title,hbox,saleTableView,exportToExcel);
     container.setAlignment(Pos.CENTER);
     container.setSpacing(20);
     container.setMargin(title,new Insets(15,20,0,40));




return container;
    };





}
