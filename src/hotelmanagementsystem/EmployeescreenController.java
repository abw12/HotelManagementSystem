/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author abhishek
 */
public class EmployeescreenController implements Initializable {

    
    @FXML
    private StackPane stackpane;

    @FXML
    private JFXTreeTableView<employee> treeView;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXTextField fullname;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXTextField salary;

    @FXML
    private JFXTextField userType;

    @FXML
    private JFXTextField search_txt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllEmployee("select * from employeedetails");
        
    }   
    
    public void loadAllEmployee(String sql){
        
       JFXTreeTableColumn<employee,String>id=new JFXTreeTableColumn<>("ID");
        id.setPrefWidth(60);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<employee,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<employee, String> param) {
               return param.getValue().getValue().id;
            }
        });
        
        
        JFXTreeTableColumn<employee,String>username=new JFXTreeTableColumn<>("UserName");
        username.setPrefWidth(100);
        username.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<employee,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<employee, String> param) {
               return param.getValue().getValue().username;
            }
        });
        
        JFXTreeTableColumn<employee,String>password=new JFXTreeTableColumn<>("Password");
        password.setPrefWidth(100);
        password.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<employee,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<employee, String> param) {
        return param.getValue().getValue().password;
            }
        });
        
        JFXTreeTableColumn<employee,String>fullname=new JFXTreeTableColumn<>("FullName");
        fullname.setPrefWidth(100);
        fullname.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<employee,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<employee, String> param) {
        return param.getValue().getValue().fullname;
            }
        });
        
        JFXTreeTableColumn<employee,String>address=new JFXTreeTableColumn<>("Address");
        address.setPrefWidth(100);
        address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<employee,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<employee, String> param) {
        return param.getValue().getValue().address;
            }
        });
        
        JFXTreeTableColumn<employee,String>phone=new JFXTreeTableColumn<>("Mobile NO.");
        phone.setPrefWidth(100);
        phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<employee,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<employee, String> param) {
        return param.getValue().getValue().phone;
            }
        });
        
        JFXTreeTableColumn<employee,String>startDate=new JFXTreeTableColumn<>("StartDate");
        startDate.setPrefWidth(100);
        startDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<employee,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<employee, String> param) {
        return param.getValue().getValue().startDate;
            }
        });
        
        JFXTreeTableColumn<employee,String>salary=new JFXTreeTableColumn<>("Salary");
        salary.setPrefWidth(100);
        salary.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<employee,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<employee, String> param) {
        return param.getValue().getValue().salary;
            }
        });
        
          JFXTreeTableColumn<employee,String>userType=new JFXTreeTableColumn<>("UserType");
        userType.setPrefWidth(100);
        userType.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<employee,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<employee, String> param) {
        return param.getValue().getValue().userType;
            }
        });
        
        ObservableList<employee>emp=FXCollections.observableArrayList();
        Connection connection=DBconnection.getConnection();
        try {
               PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
               ResultSet rs=ps.executeQuery();
               while(rs.next()){
                   emp.add(new employee(rs.getInt(1)+"",rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
               }
               
           } catch (SQLException ex) {
               Logger.getLogger(CustomerscreenController.class.getName()).log(Level.SEVERE, null, ex);
           }
    final TreeItem<employee> root=new RecursiveTreeItem<employee>(emp,RecursiveTreeObject::getChildren);
    treeView.getColumns().setAll(id,username,password,fullname,address,phone,startDate,salary,userType);
    treeView.setRoot(root);
    treeView.setShowRoot(false);
    }
      
        
    
    
    
      @FXML
    void back(MouseEvent event) {
        Stage home=new Stage();
       Parent root=null;
        try {
             root=FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage current=(Stage)search_txt.getScene().getWindow();
        Scene scene=new Scene(root);
        home.setScene(scene);
        home.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        home.show();

    }

    @FXML
    void clear(MouseEvent event) {
        username.setText("");
        password.setText("");
        fullname.setText("");
        address.setText("");
        phone.setText("");
        salary.setText("");
        userType.setText("");
        search_txt.setText("");
    }

    @FXML
    void close(MouseEvent event) {
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
    void delete(MouseEvent event) {
        int res=0; 
        String sql="delete from employeedetails where id=?";
       Connection connection=DBconnection.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
            ps.setString(1,search_txt.getText().toString());
            res=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeescreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(res>0){
            Image image=new Image("Images/img/mooo.png");
                Notifications notification=Notifications.create()
                    .title("Deleted").text("Data Deleted Successfully!!")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
                notification.darkStyle();
                notification.show();
                updateTable();
        }else
        {
                Image image=new Image("Images/img/delete.png");
                Notifications notification=Notifications.create()
                    .title("ERROR").text("Something Went Wrong")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
                notification.darkStyle();
                notification.show();
        }
    }

    @FXML
    void insert(MouseEvent event) {
        int res=0;
        String sql="Insert into employeedetails (username,password,fullname,address,phone,startDate,salary,userType)values(?,?,?,?,?,?,?,?)";
        Connection connection=DBconnection.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
              ps.setString(1,username.getText().toString());
                ps.setString(2,password.getText().toString());
                  ps.setString(3,fullname.getText().toString());
                    ps.setString(4,address.getText().toString());
                      ps.setString(5,phone.getText().toString());
                        ps.setString(6,startDate.getValue().toString());
                          ps.setString(7,salary.getText().toString());
                            ps.setString(8,userType.getText().toString());
                           res=ps.executeUpdate();
                      
        } catch (SQLException ex) {
            Logger.getLogger(EmployeescreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(res>0){
            Image image=new Image("Images/img/mooo.png");
                Notifications notification=Notifications.create()
                    .title("Done").text("Data Inserted Successfully!!")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
                notification.darkStyle();
                notification.show();
                updateTable();
        }else
        {
                Image image=new Image("Images/img/delete.png");
                Notifications notification=Notifications.create()
                    .title("ERROR").text("Something Went Wrong")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
                notification.darkStyle();
                notification.show();
        }
    }

    @FXML
    void update(MouseEvent event) {
        String text=search_txt.getText().toString().trim();
        String sql="UPDATE employeedetails SET Address=?,Username=?,FullName=?,MobileNo=?,Salary=?,UserType=? where ID=? ";
        Connection connection=DBconnection.getConnection();
           try {
               PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
               ps.setString(1,address.getText());
               ps.setString(2,username.getText());
               ps.setString(3,fullname.getText());
               ps.setString(4,phone.getText());
               ps.setString(5,salary.getText());
               ps.setString(6,userType.getText());
               ps.setString(7,text);
               
               ps.executeUpdate();
           } catch (SQLException ex) {
               Logger.getLogger(RoomscreenController.class.getName()).log(Level.SEVERE, null, ex);
           }
           updateTable();

    }

    @FXML
    private void search(MouseEvent event) {
        loadAllEmployee("Select * from employeedetails where ID='"+search_txt.getText().toString().trim()+"'");
    }

    private void updateTable() {
        loadAllEmployee("Select * from employeedetails");
    }
    
}
