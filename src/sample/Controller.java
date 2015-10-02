package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Controller {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField positionId;
    @FXML private TextField explanation;
    @FXML private TextField gitHub;
    @FXML private TextField personalSite;
    @FXML private TextField projects;
    @FXML private TextField source;
    @FXML private Label fileSelected;
    @FXML private Label applicationSubmitted;

    private String resumeFile;

    public void handleJobSubmittal(ActionEvent actionEvent) throws Exception {

        // Convert the various projects into a single string
        String projectList = ("[\"" + gitHub.getText() +
                "\", \"" + personalSite.getText() +
                "\", \"" + projects.getText() + "\"]");

        //URL of API
        URL postUrl = new URL("https://api.perka.com/1/communication/job/apply");

        // JSON setup
        String appJSON = ("{\"first_name\":\"" + firstName.getText() +
                "\",\"last_name\":\"" + lastName.getText() +
                "\",\"email\":\"" + email.getText() +
                "\",\"position_id\":\"" + positionId.getText() +
                "\",\"explanation\":\"" + explanation.getText() +
                "\",\"projects\": " + projectList +
                ",\"source\":\"" + source.getText() +
                "\",\"resume\":\"" + ResumeHandler.encodeFileToBase64Binary(resumeFile) +
                "\"}");

        System.out.println(appJSON);

        try {
            //Ready -- Connection Setup
            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");

            //Set
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(appJSON.getBytes());

            //Go!
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String currentLine;
            String response = "";
            while ((currentLine = bufferedReader.readLine()) != null) {
                response = response + currentLine;
            }
            bufferedReader.close(); // STOP!!!

            //Check the response
            System.out.println(connection.getResponseCode());
            System.out.println(response);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        applicationSubmitted.setText("You just submitted your application information to Perka.com");
    }


    public void uploadResume(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf")); // limit fileChooser options to *.pdf files
        fileChooser.setTitle("Select Resume File");
        File selectedFile = fileChooser.showOpenDialog(fileSelected.getScene().getWindow());
        if (selectedFile != null) {
            fileSelected.setText(selectedFile.toPath().toString());
            resumeFile = selectedFile.toPath().toString();
        } else {
            fileSelected.setText("PDF Resume file selection cancelled.");
        }

    }
}
