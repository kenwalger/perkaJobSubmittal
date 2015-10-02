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
    static String projects;
    static String source;
    static String resume;


    public static void postApp(String app) {
        System.out.println("[PERKA] - postApp()");
        String postUrl = "https://api.perka.com/1/communication/job/apply";


        try {
            //TODO: post app to Perka API
            System.out.printf("Here is the data you are wanting to send: %n %s %n", app);

            System.out.println("\nCool, you just submitted your application to Perka.com");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * applicationJSONGenerator converts the inputted data into JSON format
     *
     * @param firstName     First Name
     * @param lastName      Last Name
     * @param email         Email address
     * @param positionID    The ID of the position to which you are applying
     * @param explanation   How the API request was made
     * @param projects      Array of links to projects, GitHub, etc
     * @param source        How did you find Perka?
     * @param resume        Base64 encoded resume from ResumeHandler
     * @return              JSON version of the application data
     */

    public static String applicationJSONGenerator(String firstName,
                                            String lastName,
                                            String email,
                                            String positionID,
                                            String explanation,
                                            String projects,
                                            String source,
                                            String resume) {


        String app = ("{\"first_name\":\"" + firstName +
                        "\",\"last_name\":\"" + lastName +
                        "\",\"email\":\"" + email +
                        "\",\"positionID\":\"" + positionID +
                        "\",\"explanation\":\"" + explanation +
                        "\",\"projects\":\"" + projects +
                        "\",\"source\":\"" + source +
                        "\",\"resume\":\"" + resume +
                        "\"}");

        return app;
    }

}
