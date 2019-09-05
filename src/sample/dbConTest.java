package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

/**
 * Created by test on 10/1/2017.
 */
public class dbConTest {



    public static Connection getConnection() throws     SQLException,ClassNotFoundException
    {
        String host = "jdbc:mysql://localhost:3306/superMarketBill";
        String username = "root";
        String password = "";
        //regiset

        //creating connection
        //stagtem
        //resultset
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(host, username, password);

      return  con;
    };


    public static void main(String [] args) throws Exception
    {
        String host = "jdbc:mysql://localhost:3306/superMarketBill";
        String username = "root";
        String password = "";
        //regiset

        //creating connection
        //stagtem
        //resultset
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(host, username, password);

        //PreparedStatement pstat = con.prepareStatement("show columns from superMarketBill");

  Statement stat=con.createStatement();


  //stat.executeUpdate("insert into login "+ "values('times','joe')");


 //       ResultSet result = pstat.executeQuery();

        /*
        pstat.setString(0, casherField.getText());
        pstat.setString(1, casherNameFilled.getText());
        pstat.setString(2, mobileNoField.getText());
        pstat.setString(3, emailField.getText());
        pstat.setString(4, addressField.getText());
        pstat.setString(5, passwordField.getText());
        */

    }

}
