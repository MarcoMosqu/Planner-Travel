//////////////////////////////////////
// Class : Main
//////////////////////////////////////
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scanner in = new Scanner(System.in);

        UserProfile user = new UserProfile();
        user.collectUserInfo(in);
        // Uses JavaFX dialogs inside determineCountry()
        CountryAdvisor advisor = new CountryAdvisor();
        advisor.determineCountry(user);  

        TravelDisplay display = new TravelDisplay();
        display.showPrologue(user, advisor, in);

        in.close();

        // Close the JavaFX window when done 
        primaryStage.close();
    }

    public static void main(String[] args) {
        launch(args);  // Required to init JavaFX Toolkit
    }
}


