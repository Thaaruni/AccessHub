package lk.ijse.dep13.remote.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.dep13.remote.client.utill.ClientAppRouter;

import java.io.IOException;

public class ClientAppInitializer extends Application {

    public static void main ( String[] args ) {
        launch ( args );
    }

    @Override
    public void start ( Stage primaryStage ) throws IOException {
        Scene scene = new Scene(ClientAppRouter.getContainer(ClientAppRouter.Routes.MAIN).load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Client Text Message");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
