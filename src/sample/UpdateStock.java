package sample;

/**
 * Created by test on 9/28/2017.
 */
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import  java.util.*;
import javax.swing.undo.StateEdit;
import java.sql.*;
public class UpdateStock {
    public Stage updateStockStage;
    public Text title;
    public Label proId;
    public TextField prodIdField;
    public Label proName;
    public TextField proNameField;
    public Label quanavailable;
    public TextField qtyavaiable;
    public Label quanPur = new Label("Quantity Purchased");
    public TextField quanPurField;
    public Button search;
    public Button update;
    public Stage updateStock()
    {
        getComponent();
        Pane pane = addComponent();
        updateStockStage = new Common().customizeStage(pane, updateStockStage);
        return updateStockStage;
    }

    public void getComponent() {
        title = Common.getTitle("Update Stock");
        proId = new Label("Product Id");
        prodIdField = new TextField();
        search = new Button("Search");

        search.setOnAction(ev ->
        {
            try{
                quanPurField.clear();
                searchValue();  }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        });


        proName = new Label("Product Name");
        proNameField = new TextField();
        quanavailable = new Label("Quantity available");

        qtyavaiable = new TextField();
        qtyavaiable.setEditable(false);
        quanPur = new Label("Quantity Purchased");
        quanPurField = new TextField();
        update = new Button("Update");

         update.setOnAction((ActionEvent eve)  ->
        {

    try {
            Alert confirm=new Alert(Alert.AlertType.CONFIRMATION);


            confirm.setHeaderText("Please confirm update");
            Optional<ButtonType> re=confirm.showAndWait();
            if(re.get()==ButtonType.OK)
            {
                System.out.println("hello");


                updateData();

                //System.out.println("Result of updateData method"+result);


                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setHeaderText("Record updated Successsfully");
                    info.showAndWait();
                }
                else
                    new Alert(Alert.AlertType.ERROR).showAndWait();
            } catch (Exception e) {
        e.printStackTrace();
    }

        });
        //return addComponent();
    }
    public void  updateData() throws Exception
    {
        boolean state=true;


        int new_quan=Integer.valueOf(qtyavaiable.getText())+Integer.valueOf(quanPurField.getText());


        Integer pro_id=Integer.valueOf(prodIdField.getText());
        String mypro=prodIdField.getText();
        System.out.println("product id"+pro_id);

        Connection con=dbConTest.getConnection();
        Statement st=con.createStatement();
        String query="update productinfo set quantity="+new_quan+"  where productId="+pro_id;
        System.out.println("new quantity"+new_quan);


       st.executeUpdate(query);

       /*
        System.out.println("Count"+count);

       if(count!=0)
       {
           System.out.println("Count"+count);
           state=true;
       }
        else
            {
        System.out.println("Count"+count);
       }
*/
    }
    public Pane addComponent() {
        //getComponent();
        GridPane container = new GridPane();
        container.setVgap(25);
        container.setHgap(30);
        container.setAlignment(Pos.CENTER);

        container.addRow(0, proId, prodIdField, search);
        container.addRow(1, proName, proNameField);
        container.addRow(2, quanavailable, qtyavaiable);

        container.addRow(3, quanPur, quanPurField);
        container.addRow(4, update);
        container.setHalignment(update, HPos.RIGHT);
        container.setPrefSize(400, 400);

        VBox vbox = new VBox(title, container);
        vbox.setStyle("fx-background-color:white");
        vbox.setSpacing(15);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    public void searchValue() throws Exception
    {

        Connection con=dbConTest.getConnection();

        if(prodIdField.getText()!=null)
        {

            Statement st=con.createStatement();
            Integer searchedPid=Integer.valueOf(prodIdField.getText());
            ResultSet result=st.executeQuery("select productName,quantity from productinfo where productId=" + searchedPid );

            result.next();
            proNameField.setText(result.getString(1));
            qtyavaiable.setText(result.getString(2));



        }



    };



}
