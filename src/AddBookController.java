import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.Action;

public class AddBookController implements Initializable {
  
    @FXML
    private TableView<Book> table;

    @FXML
    private TableColumn<Book, Integer> idColumn;

    @FXML
    private TableColumn<Book, String> nameColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    private ObservableList<Book> bookList;

    @FXML
    private TextField nameText;

    @FXML
    private TextField authorText;

    @FXML
    private TextField quanText;

    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookList = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        table.setItems(bookList);
    }

    Library mylibrary = new Library();

    public void add (ActionEvent a){
        int quan = 0;

        String name = nameText.getText();
        String author = authorText.getText();
        String quantity = quanText.getText();
        
        if(name.equals("") || author.equals("") || quantity.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Bạn chưa nhập đầy đủ thông tin.");
            alert.show();
        }
        else {

            try {
                quan = Integer.parseInt(quanText.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Quantity phải là một số.");
                alert.show();        
            }
        }
        
        for(int i=0; i<quan; i++){
            Book newBook = new Book();
            newBook.setID(mylibrary.books.lastElement().getId()+1);
            newBook.setBookname(nameText.getText());
            newBook.setAuthor(authorText.getText());
            bookList.add(newBook);
            mylibrary.add_Book(newBook);
            mylibrary.saveData();
        }
    }

    public void delete (ActionEvent e){
        Book selected = table.getSelectionModel().getSelectedItem();
        bookList.remove(selected);
    }

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}