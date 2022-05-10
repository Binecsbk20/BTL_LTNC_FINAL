import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class changeController {
    @FXML 
    private Button closeButton;
    @FXML 
    private Button changePassword;
    @FXML
    private PasswordField curpass;
    @FXML
    private PasswordField newPass;
    @FXML
    private PasswordField checkPass;
    
    @FXML 
    private TextField curname;

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    Library mylibrary = new Library();

    public void keyPressedSignIn(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
        handleChangePassword(keyEvent);
    }
    
    @FXML
    public void handleChangePassword(Event event){
        Admin real = new Admin(curname.getText(), curpass.getText());

        if(mylibrary.isAdmins(real))
        {
            String NewPassword = newPass.getText();
            

            if (NewPassword.equals(checkPass.getText()))
            {
                mylibrary.changePass(real, NewPassword);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Bạn đã thay đổi mật khẩu thành công");
                alert.show();
                Stage stage = (Stage) changePassword.getScene().getWindow();
                stage.close();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Mật khẩu mới không khớp");
                alert.show();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Mật khẩu hoặc tên đăng nhập không chính xác");
            alert.show();
        }
        mylibrary.saveData();
    }
}
