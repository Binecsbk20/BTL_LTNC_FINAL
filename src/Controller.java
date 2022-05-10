import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable{

    @FXML
    private Button AddBook;

    @FXML
    private Button Search;
    
    @FXML 
    private Button ChangePass;

    @FXML
    private Button Borrowers;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mousEvent)
    {
        if (mousEvent.getSource() == AddBook)
        {
            loadStage("AddBook.fxml");
        }
        else if (mousEvent.getSource() == Search)
        {
            loadStage("DeleteBook.fxml");
        }
        else if (mousEvent.getSource() == ChangePass)
        {
            loadStage("change.fxml");
        }
        else if (mousEvent.getSource() == Borrowers)
        {
            loadStage("borrower.fxml");
        }
    }

    private void loadStage(String fxml)
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Library Management");
            Image image = new Image("book_icon.png");
            stage.getIcons().add(image);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
