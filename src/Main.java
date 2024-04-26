import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import layout.MainLayout;
import secondApproach.SecondAproach;
import worldMap.WorldMap;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends Application {
    public static void main(String[] args) {
launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
      MainLayout mainLayout = new MainLayout();
        SecondAproach secondAproach = new SecondAproach();
        Scene scene = new Scene(mainLayout.getMainLayout());

        primaryStage.setTitle("WorldMapTest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}