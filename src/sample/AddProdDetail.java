package sample;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.*;
import static javafx.geometry.HPos.RIGHT;
import java.sql.*;
import java.util.Optional;

/**
 * Created by test on 9/25/2017.
 */
public class AddProdDetail {

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
         Pane pane=addToContainer();
         addProductStage=new Common().customizeStage(pane,addProductStage);
        return addProductStage;
    }

    public void  getComponent()
    {



        plate=Common.getTitle("  Add Product Details");



        productId=new Label("Product Id");
        productIdF=new TextField();
        productIdF.setEditable(false);
        try {

            System.out.println("hellio" + generateProId().toString());
            productIdF.setText(generateProId().toString());}
        catch(Exception e)
        {}
        prodName=new Label("Product Name");
        proNameFld=new TextField();
        amt=new Label("Amount");
        amtFld=new TextField();
        quan=new Label("Quantity");
        quanFld=new TextField();
        desc=new Label("Description");
        descFld=new TextField();


        submit=new Button("   Submit  ");

        submit.setOnAction((ActionEvent ev)->
        {
            try {

                if(!store())
                {
                    Alert emptyFilled=new Alert(Alert.AlertType.ERROR);
                    emptyFilled.setHeaderText("Please check for error");
                    //emptyFilled.setContentText(" Check : 1. Empty filled \n 2. Email address \n 3. mobileno");
                    emptyFilled.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        cancel=new Button("   Cancel   ");


        cancel.setOnAction((ActionEvent ev)->
        {
            addProductStage.close();

        });
        subcanbtn=new HBox(submit,cancel);
        subcanbtn.setSpacing(15);
        subcanbtn.setAlignment(Pos.TOP_RIGHT);


    };

// this is container for ui elements
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



        GridPane.setHalignment(subcanbtn, RIGHT);
        container.setAlignment(Pos.TOP_CENTER);
        container.setStyle("-fx-background-color:white;");
        return container;

    }
// here is generating the next appropriate product Id
    public Integer generateProId() throws Exception
    {

         Connection con=dbConTest.getConnection();
         Statement st=con.createStatement();
         ResultSet reSet=st.executeQuery("select max(productid) from productinfo");
         reSet.next();
         Integer proId=reSet.getInt(1);

        st.close();
         con.close();

         return ++proId;
    }

    public Boolean store() throws  Exception
    {

   String[] productData=new String[]{
      proNameFld.getText(),quanFld.getText(),amtFld.getText(),descFld.getText()

   } ;
   boolean errorfree=true;
        if(check(productData)==false)
        {
            errorfree=false;

        }
        if(errorfree)
        {
            Alert confirmsub=new AddCasher().confirmSubmission();

            Optional res=confirmsub.showAndWait();
            if(res.get()== ButtonType.YES)
            {

                Connection connect=dbConTest.getConnection();
                String statementnvalue=" Insert into productinfo (productName,quantity,amount,description)  values(?,?,?,?)";
                PreparedStatement stat=connect.prepareStatement(statementnvalue);

                 stat.setString(1,productData[0]);
                 stat.setInt( 2,Integer.valueOf(productData[1]));
                 stat.setInt(3,Integer.valueOf(productData[2]));
                 stat.setString(4,productData[3]);

                stat.executeUpdate();

                Alert ialert=new Alert(Alert.AlertType.INFORMATION);
                ialert.setHeaderText("Your data submitted successfully");

                Optional re=ialert.showAndWait();
                if(re.get()==ButtonType.OK)
                {

                    try
                    {
                        productIdF.setText(generateProId().toString());}   catch(Exception e) {}

                    proNameFld.clear();
                    quanFld.clear();
                    amtFld.clear();
                    descFld.clear();
                };

            }  }
         return errorfree;  }
    public Boolean check(String [] pdata)
    {
        boolean valid=true;

      for(int i=0;i<pdata.length;i++)
      {
          if(pdata[i].isEmpty())
          {
              valid=false;
              break;   }  }

      return valid;   };
    //here is using the method check to check the filled is appropriately filled or not


}
