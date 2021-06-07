/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


 
public class employee extends RecursiveTreeObject<employee>{
     StringProperty id;
    StringProperty username;
    StringProperty password;
      StringProperty fullname;
       StringProperty address;
        StringProperty phone;
         StringProperty startDate;
          StringProperty salary;
           StringProperty userType;
           
           public employee(){
               super();
           }

    public employee(String id,String username, String password, String fullname, String address, String phone, String startDate, String salary, String userType) {
        this.id = new SimpleStringProperty(id);
        this.username = new SimpleStringProperty(username);
        this.password =new SimpleStringProperty(password);
        this.fullname = new SimpleStringProperty(fullname);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.startDate = new SimpleStringProperty(startDate);
        this.salary = new SimpleStringProperty(salary);
        this.userType = new SimpleStringProperty(userType);
    }
     
           
           
           
           
          
       
    
}
