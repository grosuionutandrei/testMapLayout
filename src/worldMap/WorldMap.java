package worldMap;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import org.controlsfx.control.WorldMapView;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class WorldMap implements Initializable {

  private   Map<String, Double> countryData;

  private List<WorldMapView.Country> operationalCountries;
    @FXML
    private WorldMapView worldMap;

    public WorldMap() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WorldMap.fxml"));
        loader.setController(this);
        countryData=new HashMap<>();
        try {
            worldMap = loader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WorldMapView getRoot() {
        return worldMap;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
populateCountries();
addCountryClickHandler();
showMarker();
populateCountries();
changeColor(operationalCountries);
        Platform.runLater(()->{
            for (WorldMapView.Country c : WorldMapView.Country.values())
                System.out.println(c.getLocale().getDisplayCountry());
        });

    }
 private void populateCountries(){
        operationalCountries= new ArrayList<>();
operationalCountries.add(WorldMapView.Country.DK);
 }

        private void addCountryClickHandler() {

            worldMap.setOnMouseClicked(event -> {
                if(!worldMap.getSelectedCountries().isEmpty()){
                    List<WorldMapView.Country> countries =  worldMap.getSelectedCountries();
                    List<WorldMapView.Location> locations = worldMap.getLocations();
                    System.out.println(locations);
                    countries.forEach(System.out::println);
                    System.out.println(worldMap.getCountrySelectionMode());
                }

                //System.out.println(country.name());
//                if (country != null) {
//                    Double data = countryData.get(country.name());
//                    if (data != null) {
//                        // Display information about the country
//                        System.out.println("Country: " + country.name() + ", Data: " + data);
//                    }
//                }
            });
        }


    private void showMarker(){
                worldMap.setLocationViewFactory(new Callback<WorldMapView.Location, Node>() {
                    @Override
                    public Node call(WorldMapView.Location location) {
                        Circle circle = new Circle();
                        circle.getStyleClass().add("location");
                        circle.setRadius(4);
                        circle.setTranslateX(-4);
                        circle.setTranslateY(-4);
                        return circle;
                    }
                });
                worldMap.setShowLocations(true);
            }
private void changeColor(List<WorldMapView.Country> countries){
  worldMap.setCountryViewFactory(new Callback<WorldMapView.Country, WorldMapView.CountryView>() {

      @Override
      public WorldMapView.CountryView call(WorldMapView.Country param) {
          if(param.equals(countries.get(0))){
              WorldMapView.CountryView operationalCountry =  new WorldMapView.CountryView(WorldMapView.Country.DK);
              operationalCountry.getStyleClass().add("country_operational");
              return operationalCountry;
          }
        return new WorldMapView.CountryView(param);
      }
  });
}




    }




