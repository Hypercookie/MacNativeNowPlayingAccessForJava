import Properties.CurrentSongProperty;
import Properties.CurrentSongTitleProperty;
import Properties.PropertyWrapper;
import com.dustinredmond.fxtrayicon.FXTrayIcon;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.*;

public class TestIcon extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ScheduledExecutorService eService = Executors.newScheduledThreadPool(1, r -> {
            var t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            return t;
        });
//        BorderPane root = new BorderPane();
//        stage.setScene(new Scene(root));
//
//        // By default, our FXTrayIcon will have an entry with our Application's title in bold font,
//        // when clicked, this MenuItem will call stage.show()
//        //
//        // This can be disabled by simply removing the MenuItem after instantiating the FXTrayIcon
//        // though, by convention, most applications implement this functionality.
//        stage.setTitle("FXTrayIcon test!");
//
//        // Instantiate the FXTrayIcon providing the parent Stage and a path to an Image file
//        final FXTrayIcon trayIcon =
//                new FXTrayIcon(stage, getClass().getResource("2x/outline_arrow_right_alt_black_24dp.png"));
//        trayIcon.show();
//
//        // By default the FXTrayIcon's tooltip will be the parent stage's title, that we used in the constructor
//        // This method can override this
//        trayIcon.setTrayIconTooltip("An alternative tooltip!");
//
//        // We can now add JavaFX MenuItems to the menu
//        MenuItem menuItemTest = new MenuItem("Create some JavaFX component!");
//        menuItemTest.setOnAction(e ->
//                new Alert(Alert.AlertType.INFORMATION,
//                        "We just ran some JavaFX code from an AWT MenuItem!").showAndWait());
//        trayIcon.addMenuItem(menuItemTest);
//        MenuItem close = new MenuItem("Close Me");
//        close.setOnAction(e -> {
//            Platform.exit();
//        });
//
//        // We can also nest menus, below is an Options menu with sub-items
//        Menu menuOptions = new Menu("Options");
//        MenuItem miOn = new MenuItem("On");
//        miOn.setOnAction(e -> System.out.println("Options -> On clicked"));
//        MenuItem miOff = new MenuItem("Off");
//        miOff.setOnAction(e -> System.out.println("Options -> Off clicked"));
//        menuOptions.getItems().addAll(miOn, miOff);
//        trayIcon.addMenuItem(menuOptions);
//
//        VBox vBox = new VBox(5);
//        vBox.getChildren().add(new Label("You should see a tray icon!\nTry closing this window " +
//                "and double-clicking the icon.\n" +
//                "Try single-clicking it."));
//        Button buttonRemoveTrayIcon = new Button("Remove TrayIcon");
//        vBox.getChildren().add(buttonRemoveTrayIcon);
//
//        // Removing the FXTrayIcon, this will also cause the JVM to terminate
//        // after the last JavaFX Stage is hidden
//        buttonRemoveTrayIcon.setOnAction(e -> trayIcon.hide());
//
//        Button buttonDefaultMsg = new Button("Show a \"Default\" message");
//        // showDefaultMessage uses the FXTrayIcon image in the notification
//        buttonDefaultMsg.setOnAction(e -> trayIcon.showMessage("A caption text", "Some content text."));
//
//        Button buttonInfoMsg = new Button("Show a \"Info\" message");
//        // other showXXX methods use an icon appropriate for the message type
//        buttonInfoMsg.setOnAction(e -> trayIcon.showInfoMessage("A caption text", "Some content text"));
//
//        Button buttonWarnMsg = new Button("Show a \"Warn\" message");
//        buttonWarnMsg.setOnAction(e -> trayIcon.showWarningMessage("A caption text", "Some content text"));
//
//        Button buttonErrorMsg = new Button("Show a \"Error\" message");
//        buttonErrorMsg.setOnAction(e -> trayIcon.showErrorMessage("A caption text", "Some content text"));
//
//        HBox hBox = new HBox(5, buttonDefaultMsg, buttonInfoMsg, buttonWarnMsg, buttonErrorMsg);
//        vBox.getChildren().add(hBox);

//        root.setCenter(vBox);
        Label title = new Label();
        Label album = new Label();
        Label artist = new Label();

        title.textProperty().bind(Bindings.concat("Title: ",PropertyWrapper.getC_song_title_prpty()));
        album.textProperty().bind(Bindings.concat("Album: ",PropertyWrapper.getC_song_album_prpty()));
        artist.textProperty().bind(Bindings.concat("Artist: ",PropertyWrapper.getC_song_artist_prpty()));


        VBox v = new VBox();
        v.getChildren().addAll(title, album, artist);
        Scene s = new Scene(v);
        stage.setScene(s);
        stage.sizeToScene();
        stage.show();
    }

    /**
     * Test icon used for FXTrayIcon runnable tests
     *
     * @return URL to an example icon PNG
     */
    public URL getIcon() {
        return getClass().getResource("2x/outline_arrow_right_alt_black_24dp.png");
    }
}
