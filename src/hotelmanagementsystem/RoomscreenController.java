/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author abhishek
 */
public class RoomscreenController implements Initializable {

    
    String status=null;
    @FXML
    private JFXTreeTableView<Room> treeView;
    @FXML
    private JFXCheckBox busy;
    @FXML
    private JFXCheckBox available;
    @FXML
    private JFXTextField search_text;
    @FXML
    private StackPane stackpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllRooms("select * from roomservice");
    }    
    public void loadAllRooms(String sql){
        JFXTreeTableColumn<Room,String>room_id=new JFXTreeTableColumn<>("Room ID");
        room_id.setPrefWidth(110);
        room_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
               return param.getValue().getValue().id;
            }
            
        });
        
          JFXTreeTableColumn<Room,String>room_Type=new JFXTreeTableColumn<>("Room Type");
        room_Type.setPrefWidth(110);
        room_Type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
               return param.getValue().getValue().roomType;
            }
            
        });
        
          JFXTreeTableColumn<Room,String>room_Number=new JFXTreeTableColumn<>("Room Number");
        room_Number.setPrefWidth(110);
        room_Number.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
               return param.getValue().getValue().roomNumber;
            }
            
        });
        
          JFXTreeTableColumn<Room,String>num_of_people=new JFXTreeTableColumn<>("Number of People");
        num_of_people.setPrefWidth(110);
        num_of_people.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
               return param.getValue().getValue().numberOfPeople;
            }
            
        });
        
          JFXTreeTableColumn<Room,String>floor_number=new JFXTreeTableColumn<>("Floor Number");
        floor_number.setPrefWidth(110);
        floor_number.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
               return param.getValue().getValue().FloorNumber;
            }
            
        });
         
          JFXTreeTableColumn<Room,String>room_phone=new JFXTreeTableColumn<>("Room Phone number");
        room_phone.setPrefWidth(120);
        room_phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
               return param.getValue().getValue().roomPhone;
            }
            
        });
        
          JFXTreeTableColumn<Room,String>room_price=new JFXTreeTableColumn<>("ROOM Price");
        room_price.setPrefWidth(110);
        room_price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
               return param.getValue().getValue().roomPrice;
            }
            
        });
        
          JFXTreeTableColumn<Room,String>room_status=new JFXTreeTableColumn<>("ROOM Status");
        room_status.setPrefWidth(120);
        room_status.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>(){   
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
               return param.getValue().getValue().roomStatus;
            }
            
        });
        
        ObservableList<Room>rooms=FXCollections.observableArrayList();
        Connection connection=DBconnection.getConnection();
           try {
               PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
               ResultSet rs=ps.executeQuery();
               while(rs.next()){
                   rooms.add(new Room(rs.getInt(1)+"",rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
               }
               
           } catch (SQLException ex) {
               Logger.getLogger(RoomscreenController.class.getName()).log(Level.SEVERE, null, ex);
           }
    final TreeItem<Room> root=new RecursiveTreeItem<Room>(rooms,RecursiveTreeObject::getChildren);
    treeView.getColumns().setAll(room_id,room_Type,room_Number,num_of_people,floor_number,room_phone,room_price,room_status);
    treeView.setRoot(root);
    treeView.setShowRoot(false);
    }

    @FXML
    private void searchByRoomNumber(MouseEvent event) {
        loadAllRooms("Select * from roomservice where roomNumber='"+search_text.getText().toString().trim()+"'");
    }

    @FXML
    private void makeItAvailable(MouseEvent event) {
        String text=search_text.getText().trim();
        int res=0;
        String sql="UPDATE roomservice SET roomStatus=? where roomNumber=? ";
        Connection connection=DBconnection.getConnection();
           try {
               PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
               ps.setString(1,"available");
               ps.setString(2,text);
               
               res=ps.executeUpdate();
           } catch (SQLException ex) {
               Logger.getLogger(RoomscreenController.class.getName()).log(Level.SEVERE, null, ex);
           }
           if(res>0)
           {
               Alert alert=new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Data Updated");
               alert.setHeaderText("Information Dialog");
               alert.setContentText("Record Updated Successfully!!");
               alert.showAndWait();
               loadAllRooms("select *from roomservice where 1");
           }
               else
           {
               Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR MESSAGE");
               alert.setHeaderText("Information Dialog");
               alert.setContentText("Enter Valid Room Number");
               alert.showAndWait();
            }
                       
     }

    @FXML
    private void searchByStatus(MouseEvent event) {
        loadAllRooms(status);
        
    }

    @FXML
    private void close(MouseEvent event) {
        JFXDialogLayout dialoglayout=new JFXDialogLayout();
        dialoglayout.setHeading(new Text("Closing Application"));
        dialoglayout.setBody(new Text("Do you want to EXIT"));
        
        JFXButton ok=new JFXButton("OK");
         JFXButton cancel=new JFXButton("CANCEL");
        
        JFXDialog dialog=new JFXDialog(stackpane,dialoglayout,JFXDialog.DialogTransition.CENTER);
        
        ok.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.exit(1);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            } 
        });
        cancel.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        dialoglayout.setActions(ok,cancel);
        dialog.show();

    }

    @FXML
    private void goBack(MouseEvent event) {
         Stage home=new Stage();
       Parent root=null;
        try {
             root=FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage current=(Stage)search_text.getScene().getWindow();
        Scene scene=new Scene(root);
        home.setScene(scene);
        home.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        home.show();
    }

    @FXML
    private void searchBusy(ActionEvent event) {
        available.setSelected(false);
        status="select * from roomservice where roomStatus='busy'";
    }

    @FXML
    private void searchAvailable(ActionEvent event) {
        busy.setSelected(false);
        status="select * from roomservice where roomStatus='available'";
    }
}
