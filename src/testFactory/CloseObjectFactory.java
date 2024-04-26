package testFactory;

import javafx.scene.layout.StackPane;
import testePage.CloseStackPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CloseObjectFactory {
    private static final CloseStackPane[] objectPool = new CloseStackPane[2];

    //is just a test , the objects needs to be created here;
    public static void createWindowCloserObject(StackPane stackPane, CloserType closerType) {
        switch (closerType) {
            case STACKPANECLOSER -> objectPool[0] = new TestObjectCloser(stackPane);
            case SECONDLAYOUT -> objectPool[1] = new CloseSecondLayout(stackPane);
        }
    }

    public enum CloserType {
        SECONDLAYOUT, STACKPANECLOSER,
    }


    public static CloseStackPane getWindowCloser(CloserType closerType) {
        if (objectPool[0] == null) {
            CloseStackPane stackPane = new TestObjectCloser();
            objectPool[0] = stackPane;
        } else if (objectPool[1] == null) {
            CloseStackPane stackPane = new CloseSecondLayout();
            objectPool[1] = stackPane;

        }
        switch (closerType) {
            case STACKPANECLOSER -> {
                return objectPool[0];
            }
            case SECONDLAYOUT -> {
                return objectPool[1];
            }

        }
        return null;
    }
}
