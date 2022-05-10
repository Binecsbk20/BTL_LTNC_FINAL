import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.text.*;

public class DeleteBookController implements Initializable {
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> nameColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, Boolean> borrowColum;
    @FXML
    private TextField nameText;
    @FXML
    private TextField authorText;
    @FXML
    private TextField idText;
    @FXML
    private TextField UserText;
    @FXML
    private TextField DateText;
    @FXML
    private Button DeleteBookBtn;
    @FXML
    private Button closeButton;
    @FXML
    private Button SearchID;
    @FXML
    private Button SearchName;
    @FXML
    private Button SearchAuthor;
    @FXML 
    private Button Borrow;

    Library mylibrary = new Library();
    private ObservableList<Book> BookList;

    public void pushdata(){
        for(int i=0; i<mylibrary.getBooks().size(); i++){
            BookList.add(mylibrary.getBooks().get(i));
        }
    
    }

    public void keyPressedsearchID(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
            searchID(keyEvent);
    }

    public void searchID (Event e){
        int id = 0;
        try {
            id = Integer.parseInt(idText.getText());
        } catch (NumberFormatException a) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("ID must be a number.");
            alert.show();        
        }
        Vector<Book> listneed = mylibrary.search_Book_UseID(id);
        table.getItems().clear();
        for(int i=0; i<listneed.size(); i++){
            BookList.add(listneed.get(i));
        }
    }
    public void keyPressedsearchName(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
            searchName(keyEvent);
    }

    public void searchName (Event e){
        String name = nameText.getText();
        System.out.println(name);
        Vector<Book> listneed = mylibrary.search_Book_Name(name);
        table.getItems().clear();
        for(int i=0; i<listneed.size(); i++){
            BookList.add(listneed.get(i));
        }
    }

    public void keyPressedsearchAuthor(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
            searchAuthor(keyEvent);
    }

    public void searchAuthor (Event e){
        String author = authorText.getText();
        Vector<Book> listneed = mylibrary.search_Book_Author(author);
        table.getItems().clear();
        for(int i=0; i<listneed.size(); i++){
            BookList.add(listneed.get(i));
        }
    }

    public void keyPressedLent(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
            LendBook(keyEvent);
    }
    

    public void LendBook (Event e){
        Book selected = table.getSelectionModel().getSelectedItem();
        
        String id = idText.getText();
        String username = UserText.getText();
        String authorname = authorText.getText();

        if(id.equals("") && username.equals("") && authorname.equals("")){
            if(selected != null && username.equals("") == false && mylibrary.borrowBook(UserText.getText(), selected.getId()) == true){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Mượn sách thành công.");
                alert.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Mượn sách không thành công.");
                alert.show();
            }
        }
        else if(username.equals("") == true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Mượn sách không thành công.");
            alert.show();
        }
        else if(selected == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Mượn sách không thành công.");
            alert.show();
        }
        else if(mylibrary.borrowBook(UserText.getText(), selected.getId()) == false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Mượn sách không thành công.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Mượn sách thành công.");
            alert.show();
        }
        mylibrary.saveData();
        table.getItems().clear();
        pushdata();
        table.setItems(BookList);
    }
    

    public void delete (ActionEvent e){
        Book selected = table.getSelectionModel().getSelectedItem();
        BookList.remove(selected);
        mylibrary.delete_Book(selected);
    }

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BookList = FXCollections.observableArrayList(        );
        idColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        borrowColum.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("borrowed"));
        pushdata();
        table.setItems(BookList);
    }

}
