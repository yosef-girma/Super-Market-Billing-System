package sample;

/**
 * Created by test on 9/25/2017.
 */
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javax.xml.soap.Detail;
import java.util.*;
import javafx.event.ActionEvent;
import static javafx.geometry.HPos.RIGHT;
import java.sql.*;
import java.util.concurrent.Executor;

public class AddCasher {

    public Text plate;
public Label casherId;
public TextField casherField;
public Label  casherName;
public  TextField casherNameFilled;

public Label address;
public TextField addressField;
public Label email;
public TextField emailField;

public  Label mobileNo;

public TextField mobileNoField;
public Label Password;
public TextField passwordField;
public GridPane container;
public Stage addCasherStage;
public Button submit;
public Button cancel;
public HBox subcanbtn;

    public Stage menuStage;
   public  Stage addCasherDetail() throws  Exception
   {
       //getComponent method do all ui component
       getComponent();
       //add to Container method set structure how it render in the stage ,Layouting and Position the component
      Pane container=addToContainer();
      addCasherStage=new Common().customizeStage(container,addCasherStage);

      submit.setOnAction(ev ->
      {
          try {

              if(!storeData())
              {
;
                  Alert emptyFilled=new Alert(Alert.AlertType.ERROR);
                  emptyFilled.setHeaderText("Please check for error");
                  emptyFilled.setContentText(" Check : 1. Empty filled \n 2. Email address \n 3. mobileno");
                  emptyFilled.show();
                  // do something
              }


             }
          catch(Exception e)
                  {
          e.printStackTrace();

                  }
      });
       return addCasherStage;
   };



    public void getComponent()
    {

        plate=new Text(" Add Casher Details");
        plate.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,18));

        casherId=new Label("Casher ID");
        casherField=new TextField();
        casherField.setEditable(false);

        casherField.setText(generateCaId());
        casherField.setPromptText("Identfication No");
        casherName=new Label("Casher Name");
        casherNameFilled=new TextField();


        mobileNo=new Label("Mobile No");
        mobileNoField=new TextField();
        mobileNoField.setPromptText(" +251923804227");
        email=new Label("Email");
        emailField=new TextField();
        emailField.setPromptText("something@example.com");
        address=new Label("Address");

        addressField=new TextField();
        addressField.setPromptText("street/city/subcity");
        Password=new Label("Password");
        passwordField=new PasswordField();

        submit=new Button("   Submit  ");
        cancel=new Button("   Cancel   ");
        subcanbtn=new HBox(submit,cancel);
        subcanbtn.setSpacing(15);
        subcanbtn.setAlignment(Pos.TOP_RIGHT);
    }
    public String generateCaId()
    {
      Integer casherid;
      Random rand=new Random();
      casherid=1000+rand.nextInt(1000);
      return casherid.toString();

    };
    public GridPane addToContainer()
    {

        container=new GridPane();
        container.setHgap(20);
        container.setVgap(30);

        container.addRow(0,plate);

        GridPane.setHalignment(plate,HPos.CENTER);

        container.addRow(1,casherId,casherField);
        container.addRow(2,casherName,casherNameFilled);
        container.addRow(3,mobileNo,mobileNoField);
        container.addRow(4,email,emailField);
        container.addRow(5,address,addressField);
        container.addRow(6,Password,passwordField);
        container.addRow(7,subcanbtn);

        cancel.setOnAction((ActionEvent ev)->
        {
            addCasherStage.close();

        });


        GridPane.setHalignment(subcanbtn, RIGHT);
        container.setAlignment(Pos.TOP_CENTER);
        container.setStyle("-fx-background-color:white;");

        return container;

    }

    public Boolean storeData() throws Exception
    {

        String[] casherData=new String[]{
                casherNameFilled.getText(), mobileNoField.getText(),

                emailField.getText(),addressField.getText(), passwordField.getText()

        };

        boolean errorfree=true;

         Connection con=dbConTest.getConnection();
         String query="Insert into casherdata values(?,?,?,?,?,?)";

         PreparedStatement pstat=con.prepareStatement(query);


          if(check(casherData)==false)
          {
       System.out.println("False");
              errorfree=false;

          }
         if(errorfree)
         {


          Alert alert=confirmSubmission();
              Optional<ButtonType> res=alert.showAndWait();
             if(res.get()==ButtonType.YES)
             {

                System.out.println(casherField.getText()
                );
                System.out.print(casherNameFilled.getText());
                 pstat.setString(1, casherField.getText());
                 pstat.setString(2, casherNameFilled.getText());
                 pstat.setString(3, mobileNoField.getText());
                 pstat.setString(4, emailField.getText());
                 pstat.setString(5, addressField.getText());
                 pstat.setString(6, passwordField.getText());
                 int cound = pstat.executeUpdate();

                 // here is putting the casherId and password to login database with the accountType
                   con.close();
                   pstat.close();

                   Connection mycon=dbConTest.getConnection();
                 String insertData="insert into login values (?,?,?)";
                   PreparedStatement st=mycon.prepareStatement(insertData);



                   st.setString(1,casherField.getText());
                   st.setString(2,passwordField.getText());
                   st.setString(3,"casher");
                   st.executeUpdate();



                 Alert ialert=new Alert(Alert.AlertType.INFORMATION);
                 ialert.setHeaderText("Your data submitted successfully");

                 Optional<ButtonType> result=ialert.showAndWait();

                 if(result.get()==ButtonType.OK)
                 {

                    casherField.setText(generateCaId());
                    casherNameFilled.clear();
                    mobileNoField.clear();
                    emailField.clear();
                    addressField.clear();
                    passwordField.clear();


                 }
             }
         }

         return errorfree;

    }
    // confirm submission assuring that  you are really want to add the record to the database
    public Alert confirmSubmission()
    {
        Alert alert=new Alert(Alert.AlertType.NONE);
        alert.setHeaderText("Are you sure you want to submit the record");
        alert.getDialogPane().getButtonTypes().addAll(ButtonType.YES,ButtonType.NO);

        return alert;
    }
    public boolean check(String[] string)
    {
        boolean correct=true;

        for(int x=0;x<string.length;x++)
        {
            if(string[x].isEmpty() || string[1].length()!=10 || !(string[2].contains("@")) || !(string[2].contains(".")))
            {

                correct=false;
                break;
            }

        }


       return correct;
    }



        };