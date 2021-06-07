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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.time.LocalDate;
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
import javafx.scene.layout.AnchorPane;
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
public class ReservationscreenController implements Initializable {

 
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField duration;
    @FXML
    private JFXTextField numPeople;
    @FXML
    private JFXTextField payment;
    @FXML
    private JFXTextField roomType;
    @FXML
    private JFXTextField roomNumber;
    @FXML
    private JFXTextField price;
    @FXML
    private JFXTextField services;
    @FXML
    private JFXTextField total;
    @FXML
    private JFXDatePicker endDate;
     @FXML
    private JFXDatePicker startDate;
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
    private void book(MouseEvent event) {
        int res=0;
        String sql="Insert into customerdetails (name,email,address,phone,numPeople,paymentType,duration,roomType,roomNumber,startDate,endDate,price,services,total) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection=DBconnection.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
            ps.setString(1,name.getText().toString());
             ps.setString(2,email.getText().toString());
              ps.setString(3,address.getText().toString());
               ps.setString(4,phone.getText().toString());
                ps.setString(5,numPeople.getText().toString());
                 ps.setString(6,payment.getText().toString());
                  ps.setString(7,duration.getText().toString());
                   ps.setString(8,roomType.getText().toString());
                    ps.setString(9,roomNumber.getText().toString());
                     ps.setString(10,startDate.getValue().toString());
                      ps.setString(11,endDate.getValue().toString());
                       ps.setString(12,price.getText().toString());
                        ps.setString(13,services.getText().toString());
                         ps.setString(14,total.getText().toString());
                         res=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationscreenController.class.getName()).log(Level.SEVERE, null, ex);
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
                updateStatus();
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
    private void rest(MouseEvent event) {
        name.setText("");
          email.setText("");
            address.setText("");
              phone.setText("");
                numPeople.setText("");
                  payment.setText("");
                      roomType.setText("");
                        roomNumber.setText("");
                              price.setText("");
                                  total.setText("");
                                  duration.setText("");
                                  services.setText("");
                                  
                                
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
    private void back(MouseEvent event) {
         Stage home=new Stage();
       Parent root=null;
        try {
             root=FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage current=(Stage)roomNumber.getScene().getWindow();
        Scene scene=new Scene(root);
        home.setScene(scene);
        home.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        home.show();
        
    }
    
    @FXML
    private void print(MouseEvent event) {
        PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
    }
    
    private void updateStatus() {
        String text=roomNumber.getText().toString().trim();
        String sql="UPDATE roomservice SET roomStatus=? where roomNumber=? ";
        Connection connection=DBconnection.getConnection();
           try {
               PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
               ps.setString(1,"busy");
               ps.setString(2,text);
               
               ps.executeUpdate();
           } catch (SQLException ex) {
               Logger.getLogger(RoomscreenController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }
    
    public class BillPrintable implements Printable {
    
    public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
     
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 


            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
       
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

    
            
            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
            
            
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
            
                String  user_name=name.getText();
                String phone=email.getText();
                String add=address.getText();
                String payentType=payment.getText();
                String num=numPeople.getText();
                String service=services.getText();
                
             g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            g2d.drawString("-------------------------------------",12,y);y+=yShift;
            g2d.drawString("      Hotel Bill Receipt        ",12,y);y+=yShift;
            g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;
      
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("                                     ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
            g2d.drawString("  Name                    " +user_name+"  ",10,y);y+=yShift;
            g2d.drawString("  Address                 " +add+"  ",10,y);y+=yShift;
            g2d.drawString("  Payment                 " +payentType+"  ",10,y);y+=yShift;
            g2d.drawString("  Number Of People        " +num+"  ",10,y);y+=yShift;
            g2d.drawString("  Services                " +service+"  ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Total amount:      sum=" +total.getText().toString()+"           ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("          Hotel Phone Number         ",10,y);y+=yShift;
            g2d.drawString("             03111111111             ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("    THANKS TO VISIT OUR HOTEL        ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
                  
          

    }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }
    
 public PageFormat getPageFormat(PrinterJob pj)
    {
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =8.0;  
    double headerHeight = 2.0;                  
    double footerHeight = 2.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );   //define boarder size    after that print area width is about 180 points
            
    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
    pf.setPaper(paper);    

    return pf;
}
       
         protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}
    
}
