package layout;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import testFactory.CloseObjectFactory;
import worldMap.WorldMap;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainLayout implements Initializable {

    @FXML
    private StackPane mainLayout;
    @FXML
    private StackPane firstLayout;
    @FXML
    private StackPane secondLayout;

    @FXML
    private VBox mapContainer;


    public MainLayout() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainAppLayout.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeWorldMap();
        System.out.println(mapContainer.getChildren().size());
        Platform.runLater(()->CloseObjectFactory.createWindowCloserObject(firstLayout, CloseObjectFactory.CloserType.STACKPANECLOSER));
    }


    private void initializeWorldMap() {
        WorldMap worldMap = new WorldMap(this.firstLayout);
        this.mapContainer.getChildren().add(worldMap.getRoot());
    }

    public StackPane getMainLayout() {
        return mainLayout;
    }
}
