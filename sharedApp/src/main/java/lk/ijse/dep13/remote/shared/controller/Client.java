package lk.ijse.dep13.remote.shared.controller;



import javafx.scene.layout.VBox;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private List<String> messageQueue = new ArrayList<>();

    public Client(Socket socket) {
        //new Thread(() -> {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            sendQueuedMessages();
        } catch (IOException e) {
            System.out.println("Error creating client socket");
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        //}).start();
    }




    public void sendMessageToServer(String messageToServer) {
        if (socket != null && socket.isConnected()) {
            try {
                bufferedWriter.write(messageToServer);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("Message send to server");// Ensure the message is sent immediately
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error sending message to the client");
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        } else {
            messageQueue.add(messageToServer);
        }
    }


    private void sendQueuedMessages() {
        try {
            for (String msg : messageQueue) {
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();


            }
            messageQueue.clear();
            System.out.println("Client Queued");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error sending messages from client");
        }
    }

    public void receiveMessageFromServer(VBox vBox) {
        new Thread(() -> {
            while (/*socket != null &&*/ socket.isConnected()) {
                try {
                    String messageFromServer = bufferedReader.readLine();
                    TextMessageController.addLabel(messageFromServer,vBox);
                    System.out.println("Message received from server");
                    /*if (messageFromServer != null) {
                        Platform.runLater(() -> ClientMainSceneController.addLabel(messageFromServer, vBox));
                        System.out.println("Message received from server: " + messageFromServer);
                    }*/
                } catch (IOException e) {
                    e.printStackTrace();
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

