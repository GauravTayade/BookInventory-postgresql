/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booksinventory;

import commons.DB;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tayad
 */
public class BookEditController implements Initializable {

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
    private Button btnEditBook;
    @FXML
    private ImageView imgBtnSave;
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
    private Button btnCancel;
    @FXML
    private ImageView imgBtnCancel;
    
    private int bookid;
    
    DB db = new DB();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
        
    public void loadData(int id){
        
        bookid = id;
        
        ResultSet selectedSet = db.select("SELECT * FROM books where bid='"+bookid+"' LIMIT 1");
   
        try{
            do{
                System.out.println(selectedSet.getRow());
                txtBookTitle.textProperty().set(selectedSet.getString("booktitle"));
                txtBookAuthour.textProperty().set(selectedSet.getString("bookauthour"));
                txtBookPrice.textProperty().set(selectedSet.getString("bookprice"));
                txtBookRating.textProperty().set(selectedSet.getString("bookrating"));
                txtBookStatus.textProperty().set(selectedSet.getString("bookstatus"));
                txtBookYear.textProperty().set(selectedSet.getString("bookyear"));
                
            }while(selectedSet.next());
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    @FXML
    private void btnEditBookClick(ActionEvent event) {
        
        Alert editConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        editConfirmation.setTitle("Warning");
        editConfirmation.setContentText("This will override current data and you won't be able to retrive it back");
        
        Optional<ButtonType> userResponse = editConfirmation.showAndWait();
        
        if(userResponse.get() == ButtonType.OK){
            
            String query ="UPDATE books SET booktitle="+txtBookTitle.getText()+", "
                    + "bookauthour="+txtBookAuthour.getText()+", "
                    + "bookyear="+txtBookYear.getText()+", "
                    + "bookprice="+Float.parseFloat(txtBookPrice.getText())+", "
                    + "bookrating="+txtBookRating.getText()+", "
                    + "bookstatus="+Integer.parseInt(txtBookStatus.getText())+" WHERE bid="+bookid;
        
            int result = db.edit(query);
        
            if(result == 1){
                    
                Alert resultConfirmation = new Alert(Alert.AlertType.INFORMATION);
                resultConfirmation.setTitle("Success");
                resultConfirmation.setContentText("You Record has been successfully updated");
                resultConfirmation.showAndWait();
                        
                try{
                    
                    Parent parent = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
                    Scene scene = new Scene(parent);
                    Stage addBookWindows;
                    addBookWindows = (Stage)((Node)event.getSource()).getScene().getWindow();
                    addBookWindows.setScene(scene);
                    addBookWindows.show();
            
                }catch(IOException ioex){
                    ioex.printStackTrace();
                }
                
            }else{
            
                Alert errorConfirmation = new Alert(Alert.AlertType.ERROR);
                errorConfirmation.setTitle("Error");
                errorConfirmation.setContentText("Somwthing went wrong please trty again!!!");
                errorConfirmation.showAndWait();
            
            }
        }else{
        
            Alert actionCancelConfirmation = new Alert(Alert.AlertType.ERROR);
            actionCancelConfirmation.setTitle("Error");
            actionCancelConfirmation.setContentText("Somwthing went wrong please trty again!!!");
            actionCancelConfirmation.showAndWait();
        
        }        
    }

    @FXML
    private void btnCancelClick(ActionEvent event) {
    
         try{
            Parent parent = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
            Scene scene = new Scene(parent);
            Stage addBookWindows;
            addBookWindows = (Stage)((Node)event.getSource()).getScene().getWindow();
            addBookWindows.setScene(scene);
            addBookWindows.show();
            
        }catch(IOException ioex){
            ioex.printStackTrace();
        }
    
    }
    
}
