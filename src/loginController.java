import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class loginController {
    @FXML
    private Button LoginBtn;
    @FXML
    private Button closeButton;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    Library mylibrary = new Library();

    public void keyPressedSignIn(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
            handleLoginButton(keyEvent);
    }

    public void handleLoginButton(Event event){
        String name = UserName.getText();
        String password = Password.getText();
        Admin reallyadmin = new Admin(name, password);

        if(mylibrary.isAdmins(reallyadmin) == true){
            loadHomePage(event);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Tên đăng nhập hoặc mặc khẩu không chính xác");
            alert.show();
        }

    }

    private void loadHomePage(Event event)
    {
        try
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
            Scene scene = new Scene(root);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();

            Stage stage = new Stage() ;
            stage.setTitle("Library Management");
            stage.setResizable(false);
            stage.setScene(scene);
            Image image = new Image("book_icon.png");
            stage.getIcons().add(image);
            
            stage.show() ;
            window.close() ;
        }
        catch(Exception e)
        {
            e.printStackTrace() ;
        }
    }
}
