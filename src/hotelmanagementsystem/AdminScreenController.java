/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static javafx.scene.AccessibleAttribute.TEXT;
import static javafx.scene.AccessibleRole.TEXT;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author abhishek
 */
public class AdminScreenController implements Initializable {

    @FXML
    private Pane pane_1;
    @FXML
    private Pane pane_2;
    @FXML
    private Pane pane_3;
    @FXML
    private Pane pane_4;
    @FXML
    private Pane pane_5;
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
    private void mouse_exit_1(MouseEvent event) {
         pane_1.setStyle("-fx-background-color:white;-fx-background-radius:6px");
    }

    @FXML
    private void mouse_hover_1(MouseEvent event) {
         pane_1.setStyle("-fx-background-color:#377ce8;-fx-background-radius:6px");
    }

    @FXML
    private void mouse_exit_2(MouseEvent event) {
         pane_2.setStyle("-fx-background-color:white;-fx-background-radius:6px");
    }

    @FXML
    private void mouse_hover_2(MouseEvent event) {
         pane_2.setStyle("-fx-background-color:#377ce8;-fx-background-radius:6px");
    }

    @FXML
    private void mouse_exit_3(MouseEvent event) {
         pane_3.setStyle("-fx-background-color:white;-fx-background-radius:6px");
    }

    @FXML
    private void mouse_hover_3(MouseEvent event) {
     pane_3.setStyle("-fx-background-color:#377ce8;-fx-background-radius:6px");
    }

    @FXML
    private void mouse_exit_4(MouseEvent event) {
     pane_4.setStyle("-fx-background-color:white;-fx-background-radius:6px");
    }

    @FXML
    private void mouse_hover_4(MouseEvent event) {
         pane_4.setStyle("-fx-background-color:#377ce8;-fx-background-radius:6px");
    }

    @FXML
    private void mouse_exit_5(MouseEvent event) {
     pane_5.setStyle("-fx-background-color:white;-fx-background-radius:6px");
    }

    @FXML
    private void mouse_hover_5(MouseEvent event) {
         pane_5.setStyle("-fx-background-color:#377ce8;-fx-background-radius:6px");
    }

    @FXML
    private void customerscreen(MouseEvent event) {
            Stage customer=new Stage();
       Parent root=null;
        try {
             root=FXMLLoader.load(getClass().getResource("customerscreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage current=(Stage)pane_1.getScene().getWindow();
        Scene scene=new Scene(root);
        customer.setScene(scene);
        customer.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        customer.show();
    }

    @FXML
    private void logout(MouseEvent event) {
         JFXDialogLayout dialoglayout=new JFXDialogLayout();
        dialoglayout.setHeading(new Text("Logging out."));
        dialoglayout.setBody(new Text("Do you want to Logout?"));
        
        JFXButton ok=new JFXButton("OK");
         JFXButton cancel=new JFXButton("CANCEL");
        
        JFXDialog dialog=new JFXDialog(stackpane,dialoglayout,JFXDialog.DialogTransition.CENTER);
        
        ok.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Stage LoginScreen=new Stage();
                Parent root=null;
                try{
                    root=FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));  
                }catch(Exception e){
                }
                
                
                Stage current=(Stage)pane_1.getScene().getWindow();
                Scene scene=new Scene(root);
                
                LoginScreen.setScene(scene);
                
                current.hide();
                LoginScreen.show();
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
    private void homescreen(MouseEvent event) {
        Stage home=new Stage();
       Parent root=null;
        try {
             root=FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage current=(Stage)pane_1.getScene().getWindow();
        Scene scene=new Scene(root);
        home.setScene(scene);
        home.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        home.show();
    }

    @FXML
    private void employeescreen(MouseEvent event) {
          Stage employee=new Stage();
       Parent root=null;
        try {
             root=FXMLLoader.load(getClass().getResource("employeescreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage current=(Stage)pane_1.getScene().getWindow();
        Scene scene=new Scene(root);
        employee.setScene(scene);
        employee.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        employee.show();
    }

    @FXML
    private void exit(MouseEvent event) {
        JFXDialogLayout dialoglayout=new JFXDialogLayout();
        dialoglayout.setHeading(new Text("Logging out."));
        dialoglayout.setBody(new Text("Do you want to Logout?"));
        
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
    
}
