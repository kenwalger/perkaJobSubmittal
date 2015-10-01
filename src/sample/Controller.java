package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;

public class Controller {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField positionId;
    @FXML private TextField explanation;
    @FXML private TextField projects;       // Array of strings for project links
    @FXML private TextField source;
    @FXML private FileChooser resume;

    public void handleJobSubmittal(ActionEvent actionEvent) {
        System.out.println("\nCool, this seems to kinda-sorta be working");

        String projectList = projects.getText();
        //TODO: convert to JSON object


        String appJSON = Perka.applicationJSONGenerator(firstName.getText(),
                lastName.getText(),
                email.getText(),
                positionId.getText(),
                explanation.getText(),
                projectList,
                source.getText(),
//                resume.getInitialFileName());
                "resume.pdf");

        Perka.postApp(appJSON);
    }

    public void uploadResume(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select your Resume");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

    }
}
