import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import java.util.List;
import java.util.Scanner;

public class CountryAdvisor {
    private String country;
    private double exchangeRate;

    // polymorphic destination object
    private TravelDestination destination;

    public TravelDestination getDestination() {
        return destination;
    }

    public CountryAdvisor() { }
    /**
      * Determines the travel country using a simple GUI flow.
      * Uses JavaFX dialogs for user-friendly interaction.
    */

    public void determineCountry(UserProfile user) {

    // Step 1 — Ask with YES/NO JavaFX alert
    Alert ask = new Alert(Alert.AlertType.CONFIRMATION,
            "Do you already know which country you will visit?",
            ButtonType.YES, ButtonType.NO);
    ask.setHeaderText(null);

    // Shows the YES/NO dialog and waits for the user's choice.
    // If the user closes the window, it defaults to NO.
    ButtonType result = ask.showAndWait().orElse(ButtonType.NO);

    // Step 2 — If they already know → let them pick from a list
    if (result == ButtonType.YES) {

        ChoiceDialog<String> dialog = new ChoiceDialog<>(
                "Argentina",
                "Argentina", "Colombia", "Peru", "Brazil"
        );
        dialog.setTitle("Choose Country");
        dialog.setHeaderText("Select your travel destination:");
        dialog.setContentText("Country:");
        // Shows the dropdown and returns the selected country.
        // Defaults to "Argentina" if no selection is made.
        String selected = dialog.showAndWait().orElse("Argentina");
        // Applies the chosen country and sets its exchange rate.
        setCountryAndRate(selected);
        return;  // skip automatic suggestion
        
    }

    // Step 3 — Otherwise → automatically suggest based on userProfile
    suggestCountry(user);
    assignDestination(); // assign the matching TravelDestination subclass
   }
   private void setCountryAndRate(String input) {
    if (input.equalsIgnoreCase("Argentina")) {
        country = "Argentina";
        exchangeRate = 950.0;
    } else if (input.equalsIgnoreCase("Colombia")) {
        country = "Colombia";
        exchangeRate = 4000.0;
    } else if (input.equalsIgnoreCase("Peru")) {
        country = "Peru";
        exchangeRate = 3.8;
    } else if (input.equalsIgnoreCase("Brazil")) {
        country = "Brazil";
        exchangeRate = 5.4;
    }
   
  }
   private void assignDestination() {
    if ("Argentina".equalsIgnoreCase(country)) destination = new ArgentinaDestination();
    else if ("Colombia".equalsIgnoreCase(country)) destination = new ColombiaDestination();
    else if ("Peru".equalsIgnoreCase(country)) destination = new PeruDestination();
    else if ("Brazil".equalsIgnoreCase(country)) destination = new BrazilDestination();
 }
 
    // --- If user already knows the country ---
     /* private void chooseExistingCountry(Scanner in) {
        while (true) {
            System.out.print("What country will you be visiting? (Argentina, Colombia, Peru, Brazil): ");
            String input = in.nextLine().trim();

            if (input.equalsIgnoreCase("Argentina")) {
                country = "Argentina";
                exchangeRate = 950.0;
                break;
            } else if (input.equalsIgnoreCase("Colombia")) {
                country = "Colombia";
                exchangeRate = 4000.0;
                break;
            } else if (input.equalsIgnoreCase("Peru")) {
                country = "Peru";
                exchangeRate = 3.8;
                break;
            } else if (input.equalsIgnoreCase("Brazil")) {
                country = "Brazil";
                exchangeRate = 5.4;
                break;
            } else {
                System.out.println("Please choose a valid country from the list.");
            }
        }
    } */

    // --- If user doesn’t know, suggest one ---
    private void suggestCountry(UserProfile user) {
        System.out.println("\nOkay! Let's find the best country for you based on your preferences...");

        List<String> activities = user.getActivities();
        String favoriteFood = user.getFavoriteFood().toLowerCase();
        double budget = user.getBudget();
        String room = user.getRoomType().toLowerCase();
        

        int scoreArgentina = 0, scoreColombia = 0, scorePeru = 0, scoreBrazil = 0;

        String[] argentinaKeywords = {"soccer", "meat", "tango"};
        String[] colombiaKeywords = {"coffee", "arepa", "party"};
        String[] peruKeywords = {"inca", "machu", "history"};
        String[] brazilKeywords = {"beach", "samba", "tropical"};

        // Check activities
        for (String act : activities) {
            String actLower = act.toLowerCase();
            for (String k : argentinaKeywords) if (actLower.contains(k)) scoreArgentina++;
            for (String k : colombiaKeywords) if (actLower.contains(k)) scoreColombia++;
            for (String k : peruKeywords) if (actLower.contains(k)) scorePeru++;
            for (String k : brazilKeywords) if (actLower.contains(k)) scoreBrazil++;
        }

        // Check favorite food
        for (String k : argentinaKeywords) if (favoriteFood.contains(k)) scoreArgentina++;
        for (String k : colombiaKeywords) if (favoriteFood.contains(k)) scoreColombia++;
        for (String k : peruKeywords) if (favoriteFood.contains(k)) scorePeru++;
        for (String k : brazilKeywords) if (favoriteFood.contains(k)) scoreBrazil++;

        // Room preference
        if (room.contains("shared")) scoreBrazil++;
        if (room.contains("individual")) scorePeru++;

        // Budget influence
        if (budget < 800) { scorePeru++; scoreColombia++; }
        else if (budget <= 1500) { scoreArgentina++; scoreColombia++; }
        else { scoreBrazil++; scoreArgentina++; }

        // Determine best match
        if (scoreArgentina >= scoreColombia && scoreArgentina >= scorePeru && scoreArgentina >= scoreBrazil) {
            country = "Argentina"; exchangeRate = 950.0;
        } else if (scoreColombia >= scorePeru && scoreColombia >= scoreBrazil) {
            country = "Colombia"; exchangeRate = 4000.0;
        } else if (scorePeru >= scoreBrazil) {
            country = "Peru"; exchangeRate = 3.8;
        } else {
            country = "Brazil"; exchangeRate = 5.4;
        }

        System.out.println("\n=================================");
        System.out.println("We suggest you visit: " + country + "!");
    }

    // --- Weekly Plan Suggestion ---
    public void suggestWeeklyPlan(Scanner in, UserProfile user) {
        int daysToBe;
        do {
            System.out.println("\n=================================");
            System.out.print("How many days will you be there? (Max 30): ");
             System.out.println("\n=================================");
            while (!in.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                in.next();
            }
            daysToBe = in.nextInt();
            in.nextLine(); // consume newline
            if (daysToBe > 30) System.out.println("You cannot stay more than 30 days. Please enter a valid number.");
        } while (daysToBe > 30);

        int weeks = daysToBe / 7;
        int extraDays = daysToBe % 7;

        // Weekly plans
        String[] ARGENTINA_WEEKS = {
            "Week 1: Explore Buenos Aires – European architecture, tango, and local culture.",
            "Week 2: Visit Mendoza for wine tasting and stunning Andes landscapes.",
            "Week 3: Discover Patagonia – glaciers, mountains, and lakes.",
            "Week 4: Head north to Salta and Jujuy for colorful deserts and Andean culture."
        };
        String[] COLOMBIA_WEEKS = {
            "Week 1: Bogota and Medellin – culture, museums, and nightlife.",
            "Week 2: Relax on the beaches of Cartagena and the Caribbean coast.",
            "Week 3: Explore the Coffee Triangle and colonial villages.",
            "Week 4: Visit San Andres and Providencia – paradise islands in the Caribbean."
        };
        String[] PERU_WEEKS = {
            "Week 1: Lima and Cusco – history, gastronomy, and culture.",
            "Week 2: Sacred Valley and Machu Picchu.",
            "Week 3: Lake Titicaca and traditional highland villages.",
            "Week 4: Explore the Peruvian Amazon rainforest."
        };
        String[] BRAZIL_WEEKS = {
            "Week 1: Rio de Janeiro – beaches, samba, and Christ the Redeemer.",
            "Week 2: Salvador de Bahia – Afro-Brazilian heritage and music.",
            "Week 3: Discover the Amazon rainforest or Pantanal wetlands.",
            "Week 4: Relax on the northeastern beaches – Recife, Natal, and Fortaleza."
        };

        String[] selectedWeeks = null;

        if (country.equalsIgnoreCase("Argentina")) selectedWeeks = ARGENTINA_WEEKS;
        else if (country.equalsIgnoreCase("Colombia")) selectedWeeks = COLOMBIA_WEEKS;
        else if (country.equalsIgnoreCase("Peru")) selectedWeeks = PERU_WEEKS;
        else if (country.equalsIgnoreCase("Brazil")) selectedWeeks = BRAZIL_WEEKS;

        if (selectedWeeks != null) {
           //System.out.print("Hello "+ user.getName() +", welcome to your adventure in "+ country + "!" );
          // System.out.println("We have the following suggested plan for you:");
            // System.out.println("\n--- Suggested Weekly Plan for " + country + " ---");
            TravelDisplay display = new TravelDisplay();
            display.printDynamicShadow("Hello " + user.getName() +
        "! Welcome to your adventure in " + country + "!"+"\n" +"Here our suggested plan :");
            for (int i = 0; i < weeks && i < selectedWeeks.length; i++) {
                System.out.println(selectedWeeks[i]);
            }
            if (extraDays > 0) {
                System.out.println("You will have " + extraDays + " extra day(s) to relax or explore freely.");
            }
        }
    }

    // --- Getters ---
    public String getCountry() { return country; }
    public double getExchangeRate() { return exchangeRate; }
}
