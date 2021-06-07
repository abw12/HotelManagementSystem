/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;


    



public class CustomerscreenController implements Initializable {


    @FXML
    private JFXTreeTableView<Customer> treeView;

    @FXML
    private JFXTextField searchText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllCustomer("SELECT * from customerdetails");
    }    
    
     public void loadAllCustomer(String sql){
        JFXTreeTableColumn<Customer,String>id=new JFXTreeTableColumn<>("ID");
        id.setPrefWidth(60);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
               return param.getValue().getValue().id;
            }
            
        });
        
          JFXTreeTableColumn<Customer,String>name=new JFXTreeTableColumn<>("Name");
        name.setPrefWidth(80);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
               return param.getValue().getValue().name;
            }
            
        });
        
          JFXTreeTableColumn<Customer,String>email=new JFXTreeTableColumn<>("Email_ID");
        email.setPrefWidth(100);
        email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
               return param.getValue().getValue().email;
            }
            
        });
        
          JFXTreeTableColumn<Customer,String>address=new JFXTreeTableColumn<>("Address");
        address.setPrefWidth(100);
        address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
               return param.getValue().getValue().address;
            }
            
        });
        
          JFXTreeTableColumn<Customer,String>phone=new JFXTreeTableColumn<>("Phone Number");
        phone.setPrefWidth(80);
        phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
               return param.getValue().getValue().phone;
            }
            
        });
         
          JFXTreeTableColumn<Customer,String>numPeople=new JFXTreeTableColumn<>("Num_Of_People");
        numPeople.setPrefWidth(60);
        numPeople.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
               return param.getValue().getValue().numPeople;
            }
            
        });
        
          JFXTreeTableColumn<Customer,String>paymentType=new JFXTreeTableColumn<>("Payment_Type");
        paymentType.setPrefWidth(90);
        paymentType.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
               return param.getValue().getValue().paymentType;
            }
            
        });
          JFXTreeTableColumn<Customer,String>duration=new JFXTreeTableColumn<>("Duration");
        duration.setPrefWidth(100);
        duration.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
               return param.getValue().getValue().duration;
            }
            
        });
        
        
          JFXTreeTableColumn<Customer,String>roomType=new JFXTreeTableColumn<>("ROOM Type");
        roomType.setPrefWidth(100);
        roomType.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
               return param.getValue().getValue().roomType;
            }
            
        });
        
        JFXTreeTableColumn<Customer,String>room_Number=new JFXTreeTableColumn("Room Number");
        room_Number.setPrefWidth(100);
        room_Number.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().roomNumber;
            }
        });
        
          JFXTreeTableColumn<Customer,String>start_Date=new JFXTreeTableColumn("Start Date");
        start_Date.setPrefWidth(100);
        start_Date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().startDate;
            }
        });
        
          JFXTreeTableColumn<Customer,String>end_Date=new JFXTreeTableColumn("End Date");
        end_Date.setPrefWidth(100);
        end_Date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().endDate;
            }
        });
        
          JFXTreeTableColumn<Customer,String>Price=new JFXTreeTableColumn("Price");
        Price.setPrefWidth(80);
        Price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().price;
            }
        });
        
          JFXTreeTableColumn<Customer,String>Services=new JFXTreeTableColumn("Services");
        Services.setPrefWidth(90);
        Services.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().services;
            }
        });
        
          JFXTreeTableColumn<Customer,String>Total=new JFXTreeTableColumn("Total");
        Total.setPrefWidth(80);
        Total.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().total;
            }
        });
        
        ObservableList<Customer>customer=FXCollections.observableArrayList();
        Connection connection=DBconnection.getConnection();
           try {
               PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
               ResultSet rs=ps.executeQuery();
               while(rs.next()){
                   customer.add(new Customer(rs.getInt(1)+"",rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15)));
               }
               
           } catch (SQLException ex) {
               Logger.getLogger(CustomerscreenController.class.getName()).log(Level.SEVERE, null, ex);
           }
    final TreeItem<Customer> root=new RecursiveTreeItem<Customer>(customer,RecursiveTreeObject::getChildren);
    treeView.getColumns().setAll(id,name,email,address,phone,numPeople,paymentType,roomType,duration,room_Number,start_Date,end_Date,Price,Services,Total);
    treeView.setRoot(root);
    treeView.setShowRoot(false);
    }

    @FXML
    private void back(MouseEvent event) {
          Stage home=new Stage();
       Parent root=null;
        try {
             root=FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage current=(Stage)searchText.getScene().getWindow();
        Scene scene=new Scene(root);
        home.setScene(scene);
        home.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        home.show();
        
    }

    @FXML
    private void searchByRoomNumber(MouseEvent event) {
        loadAllCustomer("Select * from customerdetails where roomNumber='"+searchText.getText().toString().trim()+"'");
    }
}
