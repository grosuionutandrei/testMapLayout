import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.WorldMapView;
import worldMap.WorldMap;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends Application {
    public static void main(String[] args) {
launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WorldMap worldMapView = new WorldMap();
        Scene scene = new Scene(worldMapView.getRoot());
        System.out.println(worldMapView.getRoot().getCountries());
        primaryStage.setTitle("WorldMapTest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}