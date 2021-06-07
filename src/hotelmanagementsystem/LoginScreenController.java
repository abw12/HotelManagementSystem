/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;



/**
 * FXML Controller class
 *
 * @author abhishek
 */
public class LoginScreenController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private StackPane stackpane;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(MouseEvent event) {
        
        if(username.getText().toString().trim().equals(""))
        {
            Image image=new Image("Images/img/delete.png");
                Notifications notification=Notifications.create()
                    .title("ERROR").text("Username Field is Empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
                notification.darkStyle();
                notification.show();
            }
        else if(password.getText().toString().trim().equals("")){
            Image image=new Image("Images/img/delete.png");
                Notifications notification=Notifications.create()
                    .title("ERROR").text("Password Field is Empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
                notification.darkStyle();
                notification.show();
        }
        else
        {    
            boolean isExist=false;
            String userPass="";
            String userType="";
        try {
            String sql="Select * from employeedetails where username='"+username.getText().toString().trim()+"'";
            Connection connection=DBconnection.getConnection();
            
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                isExist=true;
                userPass=rs.getString(3);
                userType=rs.getString(9);
            }
            
            if(isExist){
                
                    if(password.getText().toString().trim().equals(userPass)){
                        if(userType.equals("admin"))                            ////for user-admin---->admin screen
                        {
                         Stage adminScreen=new Stage();
                         Parent root=null;
                            try {
                                root=FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
                            } catch (IOException ex) {}
                         Stage current=(Stage)username.getScene().getWindow();
                         Scene scene=new Scene(root,1366,730);
                         
                         adminScreen.setScene(scene);
                         adminScreen.initStyle(StageStyle.TRANSPARENT);
                         
                         current.hide();
                         adminScreen.show();
                        }else
                            /////////////////user-normal--------->homeScreen
                        {
                          Stage homeScreen=new Stage();
                         Parent root;
                            root = null;
                            try {
                                root=FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
                            } catch (IOException ex) {}
                         Stage current=(Stage)username.getScene().getWindow();
                         Scene scene=new Scene(root,1366,730);
                         
                         homeScreen.setScene(scene);
                         homeScreen.initStyle(StageStyle.TRANSPARENT);
                         
                         current.hide();
                         homeScreen.show();   
                        }       
                        
                    }      
            }
            else{
            Image image=new Image("Images/img/delete.png");
                Notifications notification=Notifications.create()
                    .title("ERROR").text("Invalid Username or Password!!!")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
                notification.darkStyle();
                notification.show();
        }
        } catch (SQLException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        }
        

    @FXML
    private void cancel(MouseEvent event) {
        
        JFXDialogLayout dialoglayout=new JFXDialogLayout();
        dialoglayout.setHeading(new Text("Close"));
        dialoglayout.setBody(new Text("Do You Want To EXIT?"));
        
        JFXButton ok=new JFXButton("OK");
        JFXButton cancel=new JFXButton("CANCEL");
        
        JFXDialog dialog=new JFXDialog(stackpane,dialoglayout,JFXDialog.DialogTransition.CENTER);
        
        ok.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
            
        });
        dialoglayout.setActions(ok,cancel);
        dialog.show();
    }
    
}
