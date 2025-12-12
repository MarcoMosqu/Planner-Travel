public class PeruDestination implements TravelDestination {

    private final String[] WEEKS = {
        "Week 1: Lima & Cusco – culture and gastronomy.",
        "Week 2: Sacred Valley & Machu Picchu.",
        "Week 3: Lake Titicaca – traditional highland villages.",
        "Week 4: Peruvian Amazon rainforest."
    };

    @Override
    public String getWelcomeMessage() {
        return "Welcome to Peru!";
    }

    @Override
    public String getWeeklyPlan(int weekIndex) {
        return WEEKS[weekIndex];
    }
}
