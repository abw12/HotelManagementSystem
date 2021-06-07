/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author abhishek
 */
public class SplashScreenController implements Initializable {
    
   
    @FXML
    private ImageView Image;
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadetransition;
        fadetransition = new FadeTransition(Duration.millis(4000),Image);
        fadetransition.setFromValue(1.0);
        fadetransition.setToValue(0);
        
        
        fadetransition.setOnFinished(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Stage loginScreen=new Stage();
                Parent root=null;
                try{
                    root=FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));  
                }catch(IOException e){
                    Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, e);
                }
                
                
                Stage current=(Stage)Image.getScene().getWindow();
                Scene scene=new Scene(root,720,600);
                
                loginScreen.setScene(scene);
                loginScreen.initStyle(StageStyle.TRANSPARENT);
                current.hide();
                loginScreen.show();
            
            }
            
        });
        fadetransition.play();
    }    
    
}