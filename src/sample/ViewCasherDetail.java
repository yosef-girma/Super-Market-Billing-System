package sample;

/**
 * Created by test on 9/25/2017.
 */
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.geometry.Insets;
public class ViewCasherDetail

{
    public Stage viewCasherStage;
    public Text plate;



public  TableColumn<LoginTime,String> casherid;
public  TableColumn<LoginTime,LocalTime>  loginTime;
public  TableColumn<LoginTime,LocalTime>    logoutTime;
public  TableColumn<LoginTime,Integer>  amt;
public  TableColumn<LoginTime,LocalDate> date;
    public Stage viewCasher()
{

    Pane container= getComponent();

    viewCasherStage=new Common().customizeStage(container,viewCasherStage);

    return viewCasherStage;

};

public Pane getComponent()
{


     plate=new Text(" View Casher Login");
     plate.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,18));

     ComboBox casherIdlist=new ComboBox();
       casherIdlist.setPrefSize(200,32);
     Text text=new Text("Choose Casher");
     casherIdlist.setPlaceholder(text);
     casherIdlist.setVisibleRowCount(10);



     TableView tableview=getTable();

      ObservableList<LoginTime> list=FXCollections.observableArrayList(
              new LoginTime("1105", LocalTime.of(8,11).toString(),LocalTime.of(10,22).toString(),3,LocalDate.of(2009,10,3).toString())
      );

      tableview.getItems().addAll(list);
      ScrollPane spane=new ScrollPane();
      spane.setContent(tableview);
      spane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
      spane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
      VBox  container=new VBox(plate,casherIdlist,tableview);
      container.setAlignment(Pos.TOP_CENTER);
      container.setPadding(new Insets(20));
      container.setPrefSize(300,200);
      container.setSpacing(20);
      container.setAlignment(Pos.CENTER);
      container.setStyle("-fx-background-color:blue");
      return container;


}


public TableView getTable()
{


    TableView<LoginTime> tableView=new TableView();

     casherid=new TableColumn<>("Casher Id");
    casherid.setResizable(false);
     loginTime=new TableColumn<>("Login Time");
      logoutTime=new TableColumn<>(" Logout Time");
      amt=new TableColumn<>("Amount");
    date=new TableColumn<>("Date");
    casherid.setCellValueFactory(new PropertyValueFactory("casherId"));
    loginTime.setCellValueFactory(new PropertyValueFactory("loginT"));
    logoutTime.setCellValueFactory(new PropertyValueFactory("logoutT"));
    amt.setCellValueFactory(new PropertyValueFactory("amt"));
    date.setCellValueFactory(new PropertyValueFactory("date"));

     tableView.getColumns().addAll(casherid,loginTime,logoutTime,amt,date);
     tableView.setFixedCellSize(20);

    return tableView;
};

public Stage viewCasherDetailByDate()
{
     Stage vCasherDetail=new Stage();

      VBox mypane= (VBox) new Common().range("Casher Login By Date");
      mypane.setPadding(new Insets(20));

        VBox vbox=new VBox(mypane,getTable());
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

   vCasherDetail= new Common().customizeStage(vbox,vCasherDetail);


    return vCasherDetail;
}
public class LoginTime
{

    SimpleStringProperty casherId;
    SimpleStringProperty  loginT;
    SimpleStringProperty  logoutT;
    SimpleIntegerProperty amt;
    SimpleStringProperty date;

    public LoginTime(String casherId, String loginT, String logoutT, Integer amt, String date)
     {
       this.casherId=new SimpleStringProperty(casherId);
       this.loginT=new SimpleStringProperty(loginT);
       this.logoutT=new SimpleStringProperty(logoutT);
       this.amt=new SimpleIntegerProperty(amt);
       this.date=new SimpleStringProperty((date));
     }

  public void setCasherId(String casherId)
  {

      this.casherId.set(casherId);
  }
  public String getCasherId()
  {
      return this.casherId.get();
  }
    public void setLoginT(String loginT)
    {

        this.loginT.set(loginT);
    }
    public String getLoginT()
    {
        return this.loginT.get();
    }
    public void setLogoutT(String logoutT)
    {

        this.logoutT.set(logoutT);
    }
    public String getLogoutT()
    {
        return this.logoutT.get();
    }
    public void setAmt(Integer amt)
    {

        this.amt.set(amt);
    }
    public Integer getAmt()
    {
        return this.amt.get();
    }

    public void setDate(String date)
    {

        this.date.set(date);
    }
    public String getDate()
    {
        return this.date.get();
    }
};
}
