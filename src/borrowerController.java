import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class borrowerController implements Initializable{
    @FXML
    private Button closeButton;
    @FXML
    private TableView<User_Book_Lent> table;
    @FXML
    private TableColumn<User_Book_Lent, Integer> idColumn;
    @FXML
    private TableColumn<User_Book_Lent, String> UsersColumn;
    @FXML
    private TableColumn<User_Book_Lent, String> dateColumn;
    @FXML
    private Button DeleteUser;

    private ObservableList<User_Book_Lent> studentList;
    Library mylibrary = new Library();

    public void pushdata(){
        for(int i=0; i<mylibrary.getlist_ULents().size(); i++){
            studentList.add(mylibrary.getlist_ULents().get(i));
            System.out.println(mylibrary.getlist_ULents().get(i).getId());
            System.out.println("success");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentList = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<User_Book_Lent, Integer>("id"));
        UsersColumn.setCellValueFactory(new PropertyValueFactory<User_Book_Lent, String>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<User_Book_Lent, String>("Date"));
        pushdata();
        table.setItems(studentList);
    }

    public void delete (ActionEvent e){
        User_Book_Lent selected = table.getSelectionModel().getSelectedItem();
        studentList.remove(selected);
        mylibrary.delete_User_Lent(selected);
    }

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
