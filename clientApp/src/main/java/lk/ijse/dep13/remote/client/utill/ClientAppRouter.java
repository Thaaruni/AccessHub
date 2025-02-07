package lk.ijse.dep13.remote.client.utill;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ClientAppRouter {
    public enum Routes {
        MAIN
    }


    public static FXMLLoader getContainer(Routes route) {
        FXMLLoader fxmlLoader = null;
        if (route == Routes.MAIN) {
            fxmlLoader = new FXMLLoader(lk.ijse.dep13.remote.client.utill.ClientAppRouter.class.getResource("/clientScene/clientScene.fxml"));
        }
        return fxmlLoader;
    }



}