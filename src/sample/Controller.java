package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public void handleJobSubmittal(ActionEvent actionEvent) throws IOException {
        String projectList = ("{\"" + gitHub.getText() +
                "\", \"" + personalSite.getText() +
                "\", \"" + projects.getText() + "\"}");


        CloseableHttpClient httpClient = HttpClients.createDefault();
        String postUrl = "https://api.perka.com/1/communication/job/apply";

        HttpPost httpPost = new HttpPost(postUrl);

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("first_name", firstName.getText()));
        nvps.add(new BasicNameValuePair("last_name", lastName.getText()));
        nvps.add(new BasicNameValuePair("email", email.getText()));
        nvps.add(new BasicNameValuePair("position_id", positionId.getText()));
        nvps.add(new BasicNameValuePair("explanation", explanation.getText()));
        nvps.add(new BasicNameValuePair("projects", projectList));
        nvps.add(new BasicNameValuePair("source", source.getText()));
        nvps.add(new BasicNameValuePair("resume", ResumeHandler.encodeFileToBase64Binary(resumeFile)));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps));

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);
        }

        applicationSubmitted.setText("You just submitted your application information to Perka.com");
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
