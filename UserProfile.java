import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserProfile {
    private String name;
    private double budget;
    private String favoriteFood;
    private String roomType;
    private List<String> activities;

    // --- Constructors ---
    public UserProfile() {
        activities = new ArrayList<>();
    }

    public UserProfile(String name, double budget, String favoriteFood, String roomType, List<String> activities) {
        this.name = name;
        this.budget = budget;
        this.favoriteFood = favoriteFood;
        this.roomType = roomType;
        this.activities = new ArrayList<>(activities);
    }

    // --- Collect user info ---
    public void collectUserInfo(Scanner in) {
        System.out.print("Enter your name: ");
        name = in.nextLine();

        System.out.print("Enter your budget in Dollars $: ");
        budget = in.nextDouble();
        in.nextLine(); // consume newline

        System.out.print("Tell us about your favorite food: ");
        favoriteFood = in.nextLine();

        System.out.print("Is your room individual or shared?: ");
        roomType = in.nextLine();

        System.out.println("Enter your activities (type 'done' to finish):");
        activities.clear();
        while (true) {
            String input = in.nextLine();
            if (input.equalsIgnoreCase("done")) break;
            activities.add(input);
        }
    }

    // --- Getters ---
    public String getName() { return name; }
    public double getBudget() { return budget; }
    public String getFavoriteFood() { return favoriteFood; }
    public String getRoomType() { return roomType; }
    public List<String> getActivities() { return activities; }
}
