package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField positionId;
    @FXML private TextField explanation;
    @FXML private TextField projects;       // Array of strings for project links
    @FXML private TextField source;
    @FXML private TextField resume;         // base64 String of PDF resume

    public void handleJobSubmittal(ActionEvent actionEvent) {
        System.out.println("Cool, this seems to kinda-sorta be working");

        Perka.postApp(appJSON);
    }

    public void uploadResume(ActionEvent actionEvent) {

    }
}
