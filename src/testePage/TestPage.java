package testePage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import testFactory.CloseObjectFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestPage implements Initializable {

@FXML
    private GridPane teamsLayout;
@FXML
private VBox closeButton;

    public TestPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestPage.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
addcloselistener(this.closeButton);
    }

    public GridPane getRoot() {
        return teamsLayout;
    }

    private void addcloselistener(VBox vBox){
        vBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
            CloseObjectFactory.getWindowCloser().closeWindow();
        });
    }
}
