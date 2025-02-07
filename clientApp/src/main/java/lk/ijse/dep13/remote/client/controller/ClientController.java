package lk.ijse.dep13.remote.client.controller;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.dep13.remote.shared.controller.TextMessageController;
import lk.ijse.dep13.remote.shared.utill.SharedAppRouter;

import java.io.IOException;
import java.net.Socket;

public class ClientController {

    public ImageView imgMenu;
    public Label lblAudio;
    public Label lblBack;
    public Label lblFileTransfer;
    public Label lblRemoteDesktop;
    public Label lblTextMsg;
    public Label lblVideo;
    public AnchorPane mainMenu;
    public AnchorPane o;
    private Socket textMessageSocket;

    public void initialize() {


        Platform.runLater(()->{
            System.out.println("Initialized");
            System.out.println(mainMenu.getWidth());
            System.out.println(mainMenu.getHeight());
        });

        System.out.println("Initializing");
        // You can't obtain dimensions of a control here since the UI is still being initialized
        System.out.println(mainMenu.getWidth());
        System.out.println(mainMenu.getHeight());
    }


    public void  lblAudioOnMouseClicked(MouseEvent event) {

    }


    public void  lblFileTransferOnMouseClicked(MouseEvent event) {

    }

    public void  lblRemoteDesktopOnMouseClicked(MouseEvent event) {

    }

    public void  lblTextMsgOnMouseClick(MouseEvent event) throws IOException {
        Stage stage = new Stage(StageStyle.UTILITY);
        FXMLLoader loader = SharedAppRouter.getContainer(SharedAppRouter.Routes.TEXT_MESSAGE);
        Scene scene = new Scene(loader.load());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();

//        if (sessionActive){
            try {
                TextMessageController controller = loader.getController();
                controller.initialize(textMessageSocket);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }
//       Stage stage = new Stage();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/scene/TextMessageClientScene.fxml"))));
//        stage.setTitle("New Main Window");
//        stage.show();
    }

    public void  lblVideoOnMouseClicked(MouseEvent event) {

    }

//    public void imgMenuOnMouseClicked(MouseEvent event) {
//        TranslateTransition tt = new TranslateTransition(Duration.millis(500), mainMenu);
//        tt.setFromX(-mainMenu.getPrefWidth() - 2);
//        tt.setToX(0);
//        tt.playFromStart();
//        event.consume();
//    }

//    public void rootOnMouseClicked(MouseEvent event) {
//        hideMenu();
//    }

//    public void rootOnKeyPressed(KeyEvent keyEvent) {
//        if (keyEvent.getCode() == KeyCode.ESCAPE) hideMenu();
//    }

//    private void hideMenu(){
//        if (mainMenu.getTranslateX() != 0) return;
//        TranslateTransition tt = new TranslateTransition(Duration.millis(500), mainMenu);
//        tt.setFromX(0);
//        tt.setToX(-mainMenu.getPrefWidth() - 2);
//        tt.playFromStart();
//    }

    public void lblMenuOnMouseEntered(MouseEvent mouseEvent) {
        Label lblMenu = (Label) mouseEvent.getTarget();
        lblMenu.setTextFill(Color.AQUA);
    }

    public void lblMenuOnMouseExited(MouseEvent mouseEvent) {
        Label lblMenu = (Label) mouseEvent.getTarget();
        lblMenu.setTextFill(Color.WHITE);
    }

    public void btnConnectOnAction(ActionEvent actionEvent) {

    }


}
