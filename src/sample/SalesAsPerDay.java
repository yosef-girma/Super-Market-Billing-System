package sample;/**
 * Created by test on 9/30/2017.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SalesAsPerDay {


        public Stage salesStage;

        public Stage salePerDay() {

            VBox head = new Common().range("Sales As Per Day");


            TableView<Table> saleTableView = new TableView();
            TableColumn<Table, String> prodName = Table.getProductNameCol();
            TableColumn<Table, Integer> quan = Table.getQuantityCol();
            TableColumn<Table, Double> total = Table.getTotalCol();



            saleTableView.getColumns().addAll(prodName, quan, total);

            Button exportToExcel = new Button("Export To Excel");

            VBox container = new VBox(head, saleTableView, exportToExcel);
            container.setAlignment(Pos.CENTER);
            container.setSpacing(20);
            container.setMargin(head, new Insets(15, 20, 0, 40));

            salesStage = new Common().customizeStage(container, salesStage);
            return salesStage;
        }


    };
