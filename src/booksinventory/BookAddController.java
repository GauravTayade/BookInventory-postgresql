/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booksinventory;

import javafx.scene.control.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import commons.DB;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author tayad
 */
public class BookAddController implements Initializable {

    @FXML
    private AnchorPane ttPnAddBook;
    @FXML
    private ImageView imgAddBookHeader;
    @FXML
    private Label lblAddBookHeader;
    @FXML
    private Label lblBookTitle;
    @FXML
    private TextField txtBookTitle;
    @FXML
    private Label lblBookAuthour;
    @FXML
    private TextField txtBookAuthour;
    @FXML
    private Label lblBookYear;
    @FXML
    private TextField txtBookYear;
    @FXML
    private Button btnAddBook;
    @FXML
    private Label lblBookPrice;
    @FXML
    private TextField txtBookPrice;
    @FXML
    private Label lblBookRating;
    @FXML
    private TextField txtBookRating;
    @FXML
    private Label lblBookStatus;
    @FXML
    private TextField txtBookStatus;
    @FXML
    private ImageView imgBtnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private ImageView imgBtnCancel;
    
    DB db = new DB();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void btnAddBookClick(ActionEvent event) {
        
        String query = "INSERT INTO books (\"booktitle\",\"bookauthour\",\"bookyear\",\"bookprice\",\"bookrating\",\"bookstatus\") "
                        + "VALUES ('"+txtBookTitle.getText()+"',"
                        + "'"+txtBookAuthour.getText()+"',"
                        + "'"+txtBookYear.getText()+"',"
                        + ""+Float.parseFloat(txtBookPrice.getText())+","
                        + "'"+txtBookRating.getText()+"',"
                        + ""+Integer.parseInt(txtBookStatus.getText())+");";
        int result = db.insert(query);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("A record has been successfully added in to the database");
        alert.showAndWait();
        
        txtBookTitle.clear();
        txtBookAuthour.clear();
        txtBookPrice.clear();
        txtBookRating.clear();
        txtBookStatus.clear();
        txtBookYear.clear();
    }

    @FXML
    private void btnCancelClick(ActionEvent event) {
        
        try{
            
            Parent parent =  FXMLLoader.load(getClass().getResource("Inventory.fxml"));
            Scene scene = new Scene(parent);
            Stage inventoryWindow;
            inventoryWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            inventoryWindow.setScene(scene);
            inventoryWindow.show();
            
        }catch(IOException ioex){
            ioex.printStackTrace();
        }        
    }
    
}
