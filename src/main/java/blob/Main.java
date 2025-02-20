package blob;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Blob using FXML.
 */
public class Main extends Application {

    private final Blob blob = new Blob();

    /**
     * Initializes and shows the main GUI window for the Blob application.
     * This method loads the FXML layout for the main window, sets up the
     * scene and stage for the JavaFX application, and injects the Blob instance
     * into the controller. It also handles any IOExceptions that may occur
     * during the loading of the FXML file.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Blob!!");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBlob(blob); // inject the Blob instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

