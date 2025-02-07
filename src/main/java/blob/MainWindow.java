package blob;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Blob blob;

    private boolean newEntry = true;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/box.png"));
    private final Image blobImage = new Image(this.getClass().getResourceAsStream("/images/blob.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setOnAction(event -> handleUserInput());
    }

    /** Injects the Blob instance */
    public void setBlob(Blob b) {
        blob = b;

        if (newEntry) {
            dialogContainer.getChildren().add(
                    DialogBox.getBlobDialog(Ui.helloMessage(), blobImage)
            );
            newEntry = false;
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Blob's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = blob.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBlobDialog(response, blobImage)
        );
        userInput.clear();
    }
}

