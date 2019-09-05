package sample;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

/**
 * Created by test on 10/3/2017.
 */
public class Login {


    public Stage primaryStage = new Stage();

    public static  String username;

    public void setUserName(String username)
    {
        this.username=username;
    }
    public static String getUserName()
    {
      return username;
    };

    public String accountType = new String();
    public TextField usernameTfld;
    public PasswordField passwordTfld;

    //    public static void main(String[] args)
    {
        //launch(args);
    }


    public void setAccountType(String acutType)
       {
         accountType=acutType;
        }
        public String getAccountType()
        {
            return accountType;
        }


        public Stage getLogin()

        {

            Label Title=new Label("  SAFEWAY SUPER  MARKET " +
                    "\n            BILLING SYSTEM ");

            Title.setFont(Font.font(20));
            Title.setPadding(new Insets(30,10,10,50));

            BorderPane bpane=new BorderPane();

            bpane.setTop(Title);

            BorderPane.setMargin(Title,new Insets(40,23,10,40));
            BorderPane.setAlignment(Title, Pos.TOP_CENTER);

            //Label usertypeLbl=new Label();

            Text userTypeText=new Text("UserType");
            userTypeText.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR,18));
            userTypeText.setStyle("fx-text-fill:blue;");

            ComboBox usertypeChoice=new ComboBox<>();

            ObservableList<String> usertypeItem= FXCollections.observableArrayList(
                    "admin","casher");


            usertypeChoice.getItems().addAll(usertypeItem);
            usertypeChoice.getSelectionModel().selectFirst();
            usertypeChoice.setPrefWidth(300);

            //Label usernameLbl=new Label("Username");

            Text userNameText=new Text("Username");

            userNameText.setFont(Font.font("arial",FontWeight.BOLD,FontPosture.REGULAR,18));
            userNameText.setStyle("fx-text-fill:blue;");
            usernameTfld=new TextField();
            usernameTfld.setPromptText("Enter your ID or username");
            usernameTfld.setPrefSize(300,30);

            // Label password=new Label("Password");
            Text Password=new Text("Password");
            Password.setFont(Font.font("verdana",FontWeight.BOLD,FontPosture.REGULAR,18));
            System.out.println(Font.getFamilies());
            Password.setStyle("fx-text-fill:blue;");
            passwordTfld=new PasswordField();
            passwordTfld.setPromptText("Enter your password");
            passwordTfld.setPrefSize(300,30);


            Button loginBtn=new Button(" Login");
            loginBtn.setPrefSize(150,25);
            loginBtn.setDefaultButton(true);

            usertypeChoice.getSelectionModel().selectedIndexProperty().addListener(
                    (ObservableValue<? extends Number> ob, Number old_val, Number new_val) ->
                    {
              //          setAccountType(usertypeChoice.getSelectionModel().getSelectedItem().toString());


                        //  System.out.println(usertypeItem.get(new_val.intValue()));
                        System.out.println(usertypeChoice.getSelectionModel().getSelectedItem().toString());
                    });
            loginBtn.setOnAction(ev ->
            {

                System.out.println(" df "+usertypeChoice.getSelectionModel().getSelectedItem().toString());

                //setAccountType(usertypeChoice.getSelectionModel().getSelectedItem().toString());
                  /*

                  usertypeChoice.getSelectionModel().selectedIndexProperty().addListener(

                     ( ObservableValue <? extends Number> ob,Number old_val,Number new_val) ->
                     {
                         setAccountType(usertypeItem.get(new_val.intValue()));

                         System.out.println(usertypeItem.get(new_val.intValue()));
                         System.out.println(usertypeChoice.getSelectionModel().getSelectedItem());
                     });
                  */

                try {
                    Boolean confirmed=extractdata(usernameTfld.getText().trim(), passwordTfld.getText().trim(), usertypeChoice.getSelectionModel().getSelectedItem().toString());

                    if(confirmed)
                    {
                         getSuccessDialog();
                        //System.out.println("Access Granted");
                        }
                    else
                        {
                            getErrorDialog();
                            //System.out.println("Access Denied");
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            });


            Button cancelBtn=new Button("Cancel");
            cancelBtn.setPrefSize(150,25);

            cancelBtn.setCancelButton(true);
            cancelBtn.setOnAction(ev ->
            {
                System.exit(0);
            });

            HBox btncoll=new HBox(loginBtn,cancelBtn);
            btncoll.setSpacing(20);
            userNameText.textProperty().bind(usertypeChoice.valueProperty());

            GridPane userInfoContainer=new GridPane( );
            userInfoContainer.setHgap(130);
            userInfoContainer.setVgap(60);
            userInfoContainer.setPrefSize(400,400);
            userInfoContainer.addRow(0,userTypeText,usertypeChoice);
            userInfoContainer.addRow(1,userNameText,usernameTfld);
            userInfoContainer.addRow(2,Password,passwordTfld);
            userInfoContainer.addRow(4,new Label(),btncoll);
            userInfoContainer.setHalignment(btncoll, HPos.RIGHT);

            bpane.setCenter(userInfoContainer);
            BorderPane.setAlignment(userInfoContainer,Pos.TOP_CENTER);
            BorderPane.setAlignment(new Separator(),Pos.TOP_CENTER);
            BorderPane.setMargin(userInfoContainer,new Insets(10,20,40,60));

            //scene
            Scene scene=new Scene(bpane);
            primaryStage.setScene(scene);
            primaryStage.setWidth(700);
            primaryStage.setHeight(600);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setResizable(false);
            primaryStage.setTitle("                                     Super Market Billing Sytem Login");
            primaryStage.show();
            return primaryStage;
        }

        public void getSuccessDialog()
        {

            Alert success=new Alert(Alert.AlertType.INFORMATION);
            success.setHeaderText("Login Successfull");
            success.setTitle("Login");
            Optional<ButtonType> result=success.showAndWait();

            if(result.get()==ButtonType.OK)
            {
                try
                {

                    setUserName(usernameTfld.getText());
                    primaryStage.close();

                    if(getAccountType().compareTo("admin")==0) {
                        new Menu().MenuTable();
                    }
                    else
                    {
                        new CasherMenu().myCasherMenu(username);
                    }

                }
                catch(Exception e)
                {

                }
            }

        }
        public void getErrorDialog()
        {
            Alert error=new Alert(Alert.AlertType.ERROR);

            error.setHeaderText("Invalid password and username");
            error.setContentText("Please enter your corrent username and password");

            error.setTitle("Login Error");

            Optional<ButtonType>  result=error.showAndWait();

        }


        public Boolean extractdata(String username,String password,String accountType) throws Exception {

            boolean grant=false;

            Connection con=dbConTest.getConnection();

            String query = "Select * from login";

            PreparedStatement pstat = con.prepareStatement(query);

            ResultSet result = pstat.executeQuery();

            while (result.next())
            {

                if(username.compareTo(result.getString(1))==0 && password.compareTo(result.getString(2))==0 && accountType.compareTo(result.getString(3))==0)
                {

                    grant=true;
                setAccountType(result.getString(3));
          System.out.print("This is account Type "+accountType);
                    // System.out.println("Access Granted");
                    return grant;
                }
                else
                {

                }
            }
            return grant;
        }
    }