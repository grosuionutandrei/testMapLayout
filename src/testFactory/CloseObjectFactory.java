package testFactory;

import javafx.scene.layout.StackPane;
import testePage.CloseStackPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CloseObjectFactory {
    private static final CloseStackPane[] objectPool =  new CloseStackPane[1];

    //is just a test , the objects needs to be created here;
    public static void  createWindowCloserObject(StackPane stackPane ,CloserType closerType){
        switch(closerType){
            case STACKPANECLOSER -> objectPool[0]=new TestObjectCloser(stackPane);
        }
    }

    public enum  CloserType{
      FIRSTLAYOUT,STACKPANECLOSER,
    }


    public static  CloseStackPane  getWindowCloser(){
        if(objectPool[0]==null){
            CloseStackPane stackPane = new TestObjectCloser();
           objectPool[0]=stackPane;
        }
        return objectPool[0];
    }
}
