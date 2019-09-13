/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booksinventory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import models.Books;
import commons.DB;
import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.control.TableView;
import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author tayad
 */
public class InventoryController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Label lblHeader;
    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtTitle;
    @FXML
    private Label lblAuthour;
    @FXML
    private TextField txtAuthour;
    @FXML
    private Label lblYear;
    @FXML
    private TextField txtYear;
    @FXML
    private Label lblRating;
    @FXML
    private TextField txtRating;
    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtStatus;
    @FXML
    private Button btnSearch;
    @FXML
    private ImageView imgSearch;
    @FXML
    private TableColumn<Books, Integer> colId;
    @FXML
    private TableColumn<Books, String> colTitle;
    @FXML
    private TableColumn<Books, String> colAuthour;
    @FXML
    private TableColumn<Books, String> colYear;
    @FXML
    private TableColumn<Books, Float> colPrice;
    @FXML
    private TableColumn<Books, String> colRating;
    @FXML
    private TableColumn<Books, Integer> colStatus;

    DB db = new DB();
    @FXML
    private TableView<Books> tblBooksInventory;
    
    private ArrayList<Books> BooksList= new ArrayList<Books>();
    @FXML
    private Button btnAddBook;
    @FXML
    private ImageView imgAddBook;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnEdit;
    @FXML
    private ImageView imgEdit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colId.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
        colAuthour.setCellValueFactory(new PropertyValueFactory<Books,String>("authour"));
        colYear.setCellValueFactory(new PropertyValueFactory<Books,String>("year"));
        colRating.setCellValueFactory(new PropertyValueFactory<Books,String>("rating"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Books,Float>("price"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Books,Integer>("status"));
                
        ResultSet booksSet = db.select("SELECT * FROM books");
        
        try{
            if(booksSet.next()){
                do{
                    BooksList.add(new Books(booksSet.getInt("bid"),
                                            booksSet.getString("booktitle"),
                                            booksSet.getString("bookauthour"), 
                                            booksSet.getString("bookyear"),
                                            booksSet.getString("bookrating"),
                                            booksSet.getFloat("bookprice"), 
                                            booksSet.getInt("bookstatus")));
                }while(booksSet.next());
            }
            
            System.out.println(BooksList.size());
            tblBooksInventory.setItems(FXCollections.observableArrayList(BooksList));
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    
    }    

    @FXML
    private void btnSearchClick(ActionEvent event) {
        
        BooksList.clear();
        ResultSet searchBookSet;
        
        if(!txtTitle.getText().equals("")){
            searchBookSet = db.select("SELECT * FROM books where booktitle LIKE '%"+txtTitle.getText()+"%'");
        }else{
            searchBookSet = db.select("SELECT * FROM books");
        }
        try{
        if(searchBookSet.next()){
            do{
                BooksList.add(new Books(searchBookSet.getInt("bid"),
                                        searchBookSet.getString("booktitle"),
                                        searchBookSet.getString("bookauthour"), 
                                        searchBookSet.getString("bookyear"),
                                        searchBookSet.getString("bookrating"),
                                        searchBookSet.getFloat("bookprice"), 
                                        searchBookSet.getInt("bookstatus")));
            }while(searchBookSet.next());
            tblBooksInventory.setItems(FXCollections.observableArrayList(BooksList));
        }
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    @FXML
    private void btnAddBookClick(ActionEvent event) {
        
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("BookAdd.fxml"));
            Scene scene = new Scene(parent);
            Stage addBookWindows;
            addBookWindows = (Stage)((Node)event.getSource()).getScene().getWindow();
            addBookWindows.setScene(scene);
            addBookWindows.show();
            
        }catch(IOException ioex){
            ioex.printStackTrace();
        }
    }

    @FXML
    private void btnDeleteBookClick(ActionEvent event) {
                
        Alert userConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        userConfirmation.setTitle("Are you sure?");
        userConfirmation.setContentText("This action will revove the record from database!!!");
        
        Optional<ButtonType> userResponse = userConfirmation.showAndWait();
        
        if(userResponse.get() == ButtonType.OK){
            int id = tblBooksInventory.getSelectionModel().getSelectedItem().getId();
            
            String query = "DELETE FROM books where bid = "+id;
            int result  = db.delete(query);
            
            if(result ==1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("A selected record has been deleted from the database");
                alert.showAndWait();
            
                BooksList.clear();
            
                ResultSet booksSet = db.select("SELECT * FROM books");
                try{
                    if(booksSet.next()){
                        do{
                        BooksList.add(new Books(booksSet.getInt("bid"),
                                            booksSet.getString("booktitle"),
                                            booksSet.getString("bookauthour"), 
                                            booksSet.getString("bookyear"),
                                            booksSet.getString("bookrating"),
                                            booksSet.getFloat("bookprice"), 
                                            booksSet.getInt("bookstatus")));
                        }while(booksSet.next());
                    }
                    
                    tblBooksInventory.setItems(FXCollections.observableArrayList(BooksList));
                    
                }catch(SQLException sqlex){
                    
                    sqlex.printStackTrace();
                    
                }
            }
        
        }else{
            
            Alert cancelDelete = new Alert(Alert.AlertType.INFORMATION);
            cancelDelete.setTitle("Cancelled!!!");
            cancelDelete.setContentText("Your delete action has been cancelled");
            cancelDelete.show();
            
        }
    }

    @FXML
    private void btnEditBookClick(ActionEvent event) {
     
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("BookEdit.fxml"));
            Scene scene = new Scene(parent);
            BookEditController ctrl = new BookEditController();
            ctrl.loadData(tblBooksInventory.getSelectionModel().getSelectedItem().getId());
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setController(ctrl);
            Stage addBookWindows;
            addBookWindows = (Stage)((Node)event.getSource()).getScene().getWindow();
            addBookWindows.setScene(scene);
            addBookWindows.show();
            
        }catch(IOException ioex){
            ioex.printStackTrace();
        }
        
    }
}
