package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.*;

import java.sql.Date;
import  java.time.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;


/**
 * Created by test on 9/30/2017.
 *
 *
 */

public class Table
{

    public  static TableColumn getProductNameCol()
    {
        TableColumn<Table,String> prodName=new TableColumn<>("Prod-Name");
        prodName.setCellValueFactory(new PropertyValueFactory("productName"));

        return prodName;
    }
    public static  TableColumn getProdIdCol()
    {

        TableColumn<Table,Integer> prodId=new TableColumn<>("Product-Id");
        prodId.setCellValueFactory(new PropertyValueFactory("productId"));
        return prodId;
}
    public static TableColumn getQuantityCol()
    {
       TableColumn<Table,Integer> qty=new TableColumn<>("Quantity");
        qty.setCellValueFactory(new PropertyValueFactory("quantity"));
        return qty;

   }

    public  static TableColumn getTotalCol()
{
        TableColumn<Table,Double> total=new TableColumn<>("Total");
        total.setCellValueFactory(new PropertyValueFactory("total"));
        return  total;
}
public static TableColumn getDiscountCol()
{
    TableColumn<Table,Double> discount=new TableColumn<>("Discount");
    discount.setCellValueFactory(new PropertyValueFactory("discount"));
    return discount;
}

public static TableColumn getPriceCol()
{
    TableColumn<Table,Double> price=new TableColumn<>("Price");
    price.setCellValueFactory(new PropertyValueFactory("price"));
    return price;
}

    public static TableColumn getDateCol()
{
        TableColumn<Table,LocalDate> date=new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory("date"));
        return  date;
}


    SimpleIntegerProperty productId;
    SimpleStringProperty productName;
    SimpleDoubleProperty total;
    SimpleIntegerProperty quantity;
    SimpleDoubleProperty price;
    SimpleDoubleProperty discount;
    SimpleIntegerProperty amount;
    SimpleStringProperty description;
    SimpleStringProperty date;
    //common constructor for inventory,sales per day and sales per product table
    public Table(String productName,Integer quantity)
    {
    this.productName=new SimpleStringProperty(productName);
    this.quantity=new SimpleIntegerProperty(quantity);
    }
    // sales per product and sales per day common constructor
    public Table(String productName,Integer quantity,Double total) {

        this(productName,quantity);
        this.total=new SimpleDoubleProperty(total);
    }

    //Inventory constructor
    public Table(Integer productId,String productName,Integer quantity)
    {
        this(productName,quantity);
        this.productId=new SimpleIntegerProperty(productId);
        //this.productName=new SimpleStringProperty(productName);
         // this.quantity=new SimpleIntegerProperty(quantity);
    }
    public Table(Integer productId,String productName,Integer quantity,Double discount,Double price,Double total)
    {
        this(productId,productName,quantity);
        this.discount=new SimpleDoubleProperty(discount);
        this.price=new SimpleDoubleProperty(price);
        this.total=new SimpleDoubleProperty(total);
    }

    public Table(Integer productId,String productName,Integer quantity,Integer amount,String description)
    {
        this(productId,productName,quantity);
        this.amount=new SimpleIntegerProperty(amount);
        this.description=new SimpleStringProperty(description);
    }
    //Sales per day
    public Table(String productName,Integer quantity,Double total,Date date)
    {
        this(productName,quantity,total);
        this.date=new SimpleStringProperty(date.toString());
    }


    public void setQuantity(Integer quantity)
    {

        this.quantity.set(quantity);
    }
    public Integer getQuantity()
    {
        return this.quantity.get();
    }
    public void setproductId(Integer productId)
    {
        this.productId.set(productId);
    }
    public Integer getProductId()
    {
        return this.productId.get();
    }
    public void setProductName(String productName)
    {
        this.productName.set(productName);
    }
    public String getProductName()
    {
        return this.productName.get();
    }
    public void setTotal(Double total)
    {

        this.total.set(total);
    }


    public Double getPrice()
    {

        return this.price.get();
    }
    public Double getDiscount()
    {
        return this.discount.get();
    }
    public void  setPrice(Double price)
    {
        this.price.set(price);
    }
    public void  setDiscount(Double discount)
    {

        this.discount.set(discount);
    }

    public Double getTotal()
    {

        return this.total.get();
    }
    public void setAmount(Integer amount)
    {

        this.amount.set(amount);
    }
    public Integer getAmount()
    {

        return this.amount.get();
    };

    public void setDescription(String description)
    {

        this.description.set(description);

    }
    public String getDescription()
    {
        return this.description.get();
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

