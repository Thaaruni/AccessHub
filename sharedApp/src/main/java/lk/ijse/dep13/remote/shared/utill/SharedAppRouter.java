package lk.ijse.dep13.remote.shared.utill;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SharedAppRouter {
    public enum Routes {
        TEXT_MESSAGE , AUDIO_CALL , VIDEO_CALL , FILE_TRANSFER , REMOTE_DESKTOP
    }

    public static FXMLLoader getContainer(Routes route){
        FXMLLoader fxmlLoader = null;
        if (route == Routes.TEXT_MESSAGE){
            fxmlLoader = new FXMLLoader(SharedAppRouter.class.getResource("/sharedScene/TextMessageScene.fxml"));
        }
        else if(route == Routes.AUDIO_CALL){
            fxmlLoader = new FXMLLoader(SharedAppRouter.class.getResource("/sharedScene/AudioCallScene.fxml"));
        }
        else if(route == Routes.VIDEO_CALL){
            fxmlLoader = new FXMLLoader(SharedAppRouter.class.getResource("/sharedScene/VideoCallScene.fxml"));
        }
        else if(route == Routes.FILE_TRANSFER){
            fxmlLoader = new FXMLLoader(SharedAppRouter.class.getResource("/sharedScene/FileTransferScene.fxml"));
        }
        else if(route == Routes.REMOTE_DESKTOP){
            fxmlLoader = new FXMLLoader(SharedAppRouter.class.getResource("/sharedScene/RemoteDesktopScene.fxml"));
        }


        return fxmlLoader;
    }
}
