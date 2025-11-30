//////////////////////////////////////
// Class 4: Main
//////////////////////////////////////
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        UserProfile user = new UserProfile();
        user.collectUserInfo(in);

        CountryAdvisor advisor = new CountryAdvisor();
        advisor.determineCountry(in, user);

        TravelDisplay display = new TravelDisplay();
        display.showPrologue(user, advisor, in);

        //advisor.suggestWeeklyPlan(in, user);

        in.close();
    }
}
