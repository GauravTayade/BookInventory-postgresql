/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booksinventory;

import commons.ConnectionDatabase;
import commons.ConnectionProperty;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import commons.DB;
import commons.User;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javax.swing.JOptionPane;

/**
 *
 * @author tayad
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField txtUsername;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private ImageView imgLoginIcon;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ImageView imgUserIcon;
        
   
    User user = new User();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnLoginClick(ActionEvent event) {
        
        int result = user.login(txtUsername.getText(), txtPassword.getText());
        
        if(result == 1){
            
            try{
                Parent inventoryViewParent = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
                Scene inventoryScene = new Scene(inventoryViewParent);
                Stage inventoryWindow;
                inventoryWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                inventoryWindow.setScene(inventoryScene);
                inventoryWindow.show();
            
            }catch(IOException ioex){
                
                ioex.printStackTrace();
            
            }
            
        }else{
            
            JOptionPane.showConfirmDialog(null,
                "Wrong username password combination",
                "Try Again!!!!!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
            
            txtPassword.clear();
            
        } 
    }
}
