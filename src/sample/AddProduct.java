package sample; /**
 * Created by test on 9/25/2017.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import static javafx.geometry.HPos.RIGHT;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.scene.layout.Pane;
public class AddProduct  {

    public Text plate;
    public Stage addProductStage;


    public Label productId;
    public TextField productIdF;
    public Label prodName;
    public TextField proNameFld;
    public Label amt;
    public TextField amtFld;
    public Label quan;
    public TextField quanFld;
    public  Label desc;
    public  TextField descFld;

    public Button submit;
    public Button cancel;

    public HBox subcanbtn;
    public GridPane container;




    public  Stage addProduct()
    {
        getComponent();
        //addToContainer();
        Pane pane=addToContainer();

        addProductStage=new Common().customizeStage(pane,addProductStage);
        return addProductStage;
    }





    public void  getComponent()
    {


        plate=new Text(" Add Casher Details");
        plate.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,18));


         productId=new Label("Product Id");
         productIdF=new TextField();
         prodName=new Label("Product Name");
         proNameFld=new TextField();
         amt=new Label("Amount");
         amtFld=new TextField();
         quan=new Label("Quantity");
         quanFld=new TextField();
         desc=new Label("Description");
         descFld=new TextField();



        submit=new Button("   Submit  ");

        cancel=new Button("   Cancel   ");

        subcanbtn=new HBox(submit,cancel);
        subcanbtn.setSpacing(15);
        subcanbtn.setAlignment(Pos.TOP_RIGHT);


    };


    public GridPane addToContainer()
    {

        container=new GridPane();
        container.setHgap(20);
        container.setVgap(30);

        container.addRow(0,plate);

        GridPane.setHalignment(plate, HPos.CENTER);

        container.addRow(1,productId,productIdF);
        container.addRow(2,prodName,proNameFld);
        container.addRow(3,amt,amtFld);
        container.addRow(4,quan,quanFld);
        container.addRow(5,desc,descFld);
        container.addRow(7,subcanbtn);


        cancel.setOnAction((ActionEvent ev)->
        {
            addProductStage.close();

        });



        GridPane.setHalignment(subcanbtn, RIGHT);
        container.setAlignment(Pos.TOP_CENTER);
        container.setStyle("-fx-background-color:white;");

        return container;
    }


}
