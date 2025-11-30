import java.util.Scanner;

public class TravelDisplay {

    // Helper method to repeat characters in Java 8 or older
    private String repeatChar(char ch, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    // New Dynamic Shadow Box Method
    public void printDynamicShadow(String message) {
        int width = message.length() + 6;

        String border = repeatChar('■', width);
        //String shadow = repeatChar('▗', width - 4);

        System.out.println();
        System.out.println(border);
        System.out.println("■  " + message + "  ■");
        System.out.println(border);
        System.out.println();
    }

    public void showPrologue(UserProfile user, CountryAdvisor advisor, Scanner in) {
    double converted = advisor.getExchangeRate();
    String country = advisor.getCountry();

    System.out.println("\n=================================");

    //printDynamicShadow("Hello " + user.getName()
       //     + "! Welcome to your adventure in Brazil!");

    // Ask weekly plan (outside of the box borders)
    advisor.suggestWeeklyPlan(in, user);

    //  Box section for personal travel preferences
    //wrapInfoBox("Your budget converted to local currency: " + converted);
    //wrapInfoBox("Room type selected: " + user.getRoomType());
    //wrapInfoBox("Your favorite food: " + user.getFavoriteFood());

    // Friendly message
    wrapInfoBox("We are matching the best options for your" + user.getRoomType() + " room " + "\n" +
                "Accoring to your local currency budget :" + converted + "\n" + 
                "And we’ve found amazing spots for your favorite food!, " + user.getFavoriteFood() + "\n"
              + "An unforgettable experience in " + country + " is waiting for you.");
}

// Helper method: Simple box using * borders
private void wrapInfoBox(String message) {
    String[] lines = message.split("\n"); // Support multi-line content
    int maxLen = 0;

    for (String s : lines) {
        if (s.length() > maxLen) maxLen = s.length();
    }

    String border = repeatChar('*', maxLen + 4);

    System.out.println(border);
    for (String s : lines) {
        System.out.println("* " + padRight(s, maxLen) + " *");
    }
    System.out.println(border);
    System.out.println();
}

private String padRight(String s, int length) {
    while (s.length() < length) s += " ";
    return s;
}

}
