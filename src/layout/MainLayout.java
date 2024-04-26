package layout;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
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
    @FXML
    private StackPane controls;
    @FXML
    private VBox icons;
    private boolean isDisplayed = false;


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
        Platform.runLater(()->{CloseObjectFactory.createWindowCloserObject(firstLayout, CloseObjectFactory.CloserType.STACKPANECLOSER);
        CloseObjectFactory.createWindowCloserObject(secondLayout, CloseObjectFactory.CloserType.SECONDLAYOUT);
        });
    }


    private void initializeWorldMap() {
        WorldMap worldMap = new WorldMap(this.firstLayout);
        this.mapContainer.getChildren().add(worldMap.getRoot());
        addEnterListener(icons,controls);
        addExitListener(controls,icons);
        closeStackPaneMenu(icons,controls);
    }

    public StackPane getMainLayout() {
        return mainLayout;
    }
    private void addEnterListener(VBox vBox, StackPane stackPane){
        vBox.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {
             if(isDisplayed){
                 return;
             }
            TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(300),stackPane);
            translateTransition1.setFromY(stackPane.getLayoutX());
            translateTransition1.setToX(stackPane.getLayoutX()+100);
            translateTransition1.play();
            ScaleTransition translateTransition =new ScaleTransition(Duration.millis(500),stackPane);
            translateTransition.setFromX(1);
            translateTransition.setToX(3);
            translateTransition.play();

            isDisplayed= true;
        });
    }
    private void addExitListener(Node exitStackPane, Node enterStackPane){
        exitStackPane.addEventHandler(MouseEvent.MOUSE_EXITED,event -> {
            if (!enterStackPane.contains(event.getSceneX(), event.getSceneY())) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), exitStackPane);
                scaleTransition.setToX(1);
                scaleTransition.play();
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(500),exitStackPane);
                translateTransition1.setToX(exitStackPane.getLayoutX()-100);
                translateTransition1.play();
                isDisplayed=false;
            }
        });
    }

    private void closeStackPaneMenu(Node currentHoverNode, Node stackPaneToClose){
        currentHoverNode.addEventHandler(MouseEvent.MOUSE_EXITED,event -> {
            javafx.geometry.Point2D localPoint = stackPaneToClose.screenToLocal(event.getScreenX(), event.getScreenY());
            if(localPoint != null && !stackPaneToClose.contains(localPoint.getX(), localPoint.getY())){
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), stackPaneToClose);
                scaleTransition.setToX(1);
                scaleTransition.play();
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(500),stackPaneToClose);
                translateTransition1.setToX(stackPaneToClose.getLayoutX()-100);
                translateTransition1.play();
                isDisplayed=false;
            }
        });
    }

}
