package secondApproach;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import testFactory.CloseObjectFactory;
import worldMap.WorldMap;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SecondAproach implements Initializable {
    @FXML
    private StackPane mainLayout;
    @FXML
    private StackPane firstLayout;
    @FXML
    private StackPane secondLayout;

    @FXML
    private VBox mapContainer;
    @FXML
    private VBox text;
    @FXML
    private VBox icons;
    @FXML
    private HBox controlsContainer;
    private boolean isDisplayed = false;


    public SecondAproach() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondAproach.fxml"));
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
        Platform.runLater(() -> {
            CloseObjectFactory.createWindowCloserObject(firstLayout, CloseObjectFactory.CloserType.STACKPANECLOSER);
            CloseObjectFactory.createWindowCloserObject(secondLayout, CloseObjectFactory.CloserType.SECONDLAYOUT);
        });
    }


    private void initializeWorldMap() {
        WorldMap worldMap = new WorldMap(this.firstLayout);
        this.mapContainer.getChildren().add(worldMap.getRoot());
        addEnterListener(icons, text, controlsContainer);
        addExitListener(controlsContainer,controlsContainer,text);
    //    addExitListener(text,icons,controlsContainer,icons);
//        closeStackPaneMenu(icons,controls);
    }

    public StackPane getMainLayout() {
        return mainLayout;
    }
    private void addEnterListener(VBox vBox1, VBox vBox2, HBox hbox) {
        vBox1.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if (isDisplayed) {
                return;
            }
            vBox1.setMaxWidth(vBox1.getWidth());
            Timeline timeline = new Timeline();
            KeyValue kvHbox = new KeyValue(hbox.prefWidthProperty(), hbox.getWidth() + 120);
            KeyFrame kfHbox = new KeyFrame(Duration.millis(500), kvHbox);
            timeline.getKeyFrames().add(kfHbox);
            KeyValue kvVbox2 = new KeyValue(vBox2.prefWidthProperty(), vBox2.getWidth() + 100);
            KeyFrame kfVbox2 = new KeyFrame(Duration.millis(500), kvVbox2);
            timeline.getKeyFrames().add(kfVbox2);
            timeline.play();
            vBox2.setVisible(true);
            isDisplayed = true;
        });
    }

    private void addExitListener(Node exitStackPane, Node enterStackPane, HBox container, VBox vBox1) {
        exitStackPane.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            System.out.println("called");
            if (!enterStackPane.contains(event.getSceneX(), event.getSceneY())) {
                Timeline timeline = new Timeline();
                KeyValue kvHbox = new KeyValue(container.prefWidthProperty(), container.getWidth() - 120);
                KeyFrame kfHbox = new KeyFrame(Duration.millis(500), kvHbox);
                timeline.getKeyFrames().add(kfHbox);
                KeyValue kvVbox2 = new KeyValue(container.prefWidthProperty(), container.getWidth() - 100);
                KeyFrame kfVbox2 = new KeyFrame(Duration.millis(500), kvVbox2);
                timeline.getKeyFrames().add(kfVbox2);
                timeline.play();
                timeline.setOnFinished(e -> {
                    if (!vBox1.contains(event.getSceneX(), event.getSceneY())) {
                        container.setVisible(false);
                        isDisplayed = false;
                    }
                });
            }
        });
    }
    private void addExitListener( HBox vBox2, HBox hbox,VBox vbox2) {
        vBox2.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            if (!isDisplayed) {
                return;
            }
            Timeline timeline = new Timeline();
            KeyValue kvHbox = new KeyValue(hbox.prefWidthProperty(), hbox.getWidth() - 120);
            KeyFrame kfHbox = new KeyFrame(Duration.millis(500), kvHbox);
            timeline.getKeyFrames().add(kfHbox);
            KeyValue kvVbox2 = new KeyValue(vBox2.prefWidthProperty(), vBox2.getWidth() - 100);
            KeyFrame kfVbox2 = new KeyFrame(Duration.millis(500), kvVbox2);
            timeline.getKeyFrames().add(kfVbox2);
            timeline.play();
            vbox2.setVisible(false);
            isDisplayed = false;
        });
    }
    private void closeStackPaneMenu(Node currentHoverNode, Node stackPaneToClose) {
        currentHoverNode.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            javafx.geometry.Point2D localPoint = stackPaneToClose.screenToLocal(event.getScreenX(), event.getScreenY());
            if (localPoint != null && !stackPaneToClose.contains(localPoint.getX(), localPoint.getY())) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), stackPaneToClose);
                scaleTransition.setToX(1);
                scaleTransition.play();
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(500), stackPaneToClose);
                translateTransition1.setToX(stackPaneToClose.getLayoutX() - 100);
                translateTransition1.play();
                isDisplayed = false;
            }
        });
    }

}
