package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
        String projectList = ("{\"" + gitHub.getText() +
                "\", \"" + personalSite.getText() +
                "\", \"" + projects.getText() + "\"}");

        String postUrl = "https://api.perka.com/1/communication/job/apply";

        try {
//            HttpClient httpClient = HttpClients.createDefault();
//            String postUrl = "https://api.perka.com/1/communication/job/apply";
//
//            HttpPost httpPost = new HttpPost(postUrl);
//
//            List<NameValuePair> nvps = new ArrayList<>();
//            nvps.add(new BasicNameValuePair("first_name", firstName.getText()));
//            nvps.add(new BasicNameValuePair("last_name", lastName.getText()));
//            nvps.add(new BasicNameValuePair("email", email.getText()));
//            nvps.add(new BasicNameValuePair("position_id", positionId.getText()));
//            nvps.add(new BasicNameValuePair("explanation", explanation.getText()));
//            nvps.add(new BasicNameValuePair("projects", projectList));
//            nvps.add(new BasicNameValuePair("source", source.getText()));
//            nvps.add(new BasicNameValuePair("resume", ResumeHandler.encodeFileToBase64Binary(resumeFile)));
//
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//
//            HttpResponse response = httpClient.execute(httpPost);
//
//
//                System.out.println(response.getStatusLine());
//                HttpEntity entity = response.getEntity();
//                EntityUtils.consume(entity);

            String appJSON = ("{\"first_name\":\"" + firstName.getText() +
                    "\",\"last_name\":\"" + lastName.getText() +
                    "\",\"email\":\"" + email.getText() +
                    "\",\"positionID\":\"" + positionId.getText() +
                    "\",\"explanation\":\"" + explanation.getText() +
                    "\",\"projects\":\"" + projectList +
                    "\",\"source\":\"" + source.getText() +
                    "\",\"resume\":\"" + ResumeHandler.encodeFileToBase64Binary(resumeFile) +
                    "\"}");

            Request.Post(postUrl)
                    .bodyForm(Form.form().add(appJSON, "")
                            .build())
                    .execute().returnContent();


            applicationSubmitted.setText("You just submitted your application information to Perka.com");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void uploadResume(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
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
