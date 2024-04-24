package testFactory;

import javafx.scene.layout.StackPane;
import testePage.CloseStackPane;

public class TestObjectCloser implements CloseStackPane {
    private  StackPane firstLayout;


    public TestObjectCloser(StackPane firstLayout) {
        this.firstLayout = firstLayout;
    }

    public TestObjectCloser() {
    }

    @Override
    public void closeWindow() {
        firstLayout.setVisible(false);
        firstLayout.setDisable(true);
        firstLayout.getChildren().clear();
    }

}
