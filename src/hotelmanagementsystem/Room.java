/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author abhishek
 */
public class Room extends RecursiveTreeObject<Room>{
    StringProperty id;
    StringProperty roomType;
    StringProperty roomNumber;
    StringProperty numberOfPeople;
    StringProperty FloorNumber;
    StringProperty  roomPhone;
    StringProperty roomPrice;
    StringProperty roomStatus;
                            
    public  Room(){
        super();
    }

    public Room(String id, String roomType, String roomNumber, String numberOfPeople, String FloorNumber, String roomPhone, String roomPrice, String roomStatus) {
        this.id = new SimpleStringProperty(id);
        this.roomType = new SimpleStringProperty(roomType);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.numberOfPeople = new SimpleStringProperty(numberOfPeople);
        this.FloorNumber = new SimpleStringProperty(FloorNumber);
        this.roomPhone = new SimpleStringProperty(roomPhone);
        this.roomPrice = new SimpleStringProperty(roomPrice);
        this.roomStatus = new SimpleStringProperty(roomStatus);
    }
    
}
