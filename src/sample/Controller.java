package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.xml.soap.Text;
import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField positionId;
    @FXML private TextField explanation;
    @FXML private TextField gitHub;
    @FXML private TextField personalSite;
    @FXML private TextField projects;       // Array of strings for project links
    @FXML private TextField source;
    @FXML private Label fileSelected;

    private String resumeFile;

    public void handleJobSubmittal(ActionEvent actionEvent) throws IOException {
        System.out.println("\nCool, this seems to kinda-sorta be working");

        String projectList = ("{\"" + gitHub.getText() + "\", \"" + personalSite.getText() + "\", \"" + projects.getText() + "\"}");

        String appJSON = Perka.applicationJSONGenerator(firstName.getText(),
                lastName.getText(),
                email.getText(),
                positionId.getText(),
                explanation.getText(),
                projectList,
                source.getText(),
                ResumeHandler.encodeFileToBase64Binary(resumeFile));


        Perka.postApp(appJSON);
    }

    public void uploadResume(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setTitle("Select Resume File");
        File selectedFile = fileChooser.showOpenDialog(fileSelected.getScene().getWindow());

        if (selectedFile != null) {
            fileSelected.setText(selectedFile.toPath().toString());
            System.out.println("Path: " + selectedFile.toPath());
            System.out.println("File: " + selectedFile.getName());

            resumeFile = selectedFile.toPath().toString();

            try {
                String resume = ResumeHandler.encodeFileToBase64Binary(resumeFile);
                System.out.println( resume );
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            fileSelected.setText("PDF Resume file selection cancelled.");
        }

    }
}
