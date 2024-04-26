package testFactory;

import javafx.scene.layout.StackPane;
import testePage.CloseStackPane;

import java.io.Closeable;

public class CloseSecondLayout implements CloseStackPane {
    private StackPane secondLayout;

    public CloseSecondLayout(StackPane secondLayout) {
        this.secondLayout = secondLayout;
    }

    public CloseSecondLayout(){

    }
    @Override
    public void closeWindow() {
        this.secondLayout.setVisible(true);
        this.secondLayout.setDisable(false);
        System.out.println("From the second");
    }
}
