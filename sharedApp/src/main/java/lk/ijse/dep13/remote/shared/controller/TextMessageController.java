package lk.ijse.dep13.remote.shared.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class TextMessageController  {

    @FXML
    private AnchorPane ap_main;

    @FXML
    private Button button_send;

    @FXML
    private ScrollPane sp_main;

    @FXML
    private TextField tf_message;

    @FXML
    private VBox vbox_message;

    private Client client;






    public void initialize(Socket  socket) {
        try {
            socket = new Socket("127.0.0.1", 1234);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            client = new Client(socket);

            System.out.println("Connected to server");
        } catch (IOException e) {
            e.printStackTrace();
        }

        vbox_message.heightProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });

        client.receiveMessageFromServer(vbox_message);

        button_send.setOnAction(actionEvent -> {
            String messageToSend = tf_message.getText();
            if (!messageToSend.isEmpty()) {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 10));

                Text text = new Text(messageToSend);
                TextFlow textFlow = new TextFlow(text);
                textFlow.setStyle("-fx-text-fill: rgb(239,242,255);"
                        + "-fx-background-color: rgb(27,128,114);"
                        + "-fx-background-radius: 20px;");
                textFlow.setPadding(new Insets(5, 10, 5, 10));
                text.setFill(Color.color(0.934, 0.935, 0.996));

                hBox.getChildren().add(textFlow);
                vbox_message.getChildren().add(hBox);

                client.sendMessageToServer(messageToSend);
                tf_message.clear();
            }
        });
    }

    public static void addLabel( String messageFromServer,VBox vbox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(messageFromServer);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle("-fx-background-color: rgb(27,128,114);" + " -fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vbox.getChildren().add(hBox);
            }
        });
        System.out.println("Server msg printed");
    }
}
