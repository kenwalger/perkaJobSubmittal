package sample;


import java.util.Base64;

/**
 * Created by Ken on 9/29/2015.
 */
public class Perka {

    static String firstName;
    static String lastName;
    static String email;
    static String positionID;
    static String explanation;
    static String[] projects;
    static String source;
    static String resume;

    String encodedResume = Base64.getEncoder().encodeToString(resume.getBytes());

    public static void postApp(String app) {

        String postUrl = "https://api.perka.com/1/communication/job/apply";


        try {
            //TODO: post app to Perka API

            System.out.println("Cool, you just submitted your application to Perka.com");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
