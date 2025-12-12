public class ArgentinaDestination implements TravelDestination {

    private final String[] WEEKS = {
        "Week 1: Buenos Aires – tango, architecture, culture.",
        "Week 2: Mendoza – wine tasting in the Andes.",
        "Week 3: Patagonia – glaciers and mountains.",
        "Week 4: Salta & Jujuy – colorful deserts and Andean culture."
    };

    @Override
    public String getWelcomeMessage() {
        return "Welcome to Argentina!";
    }

    @Override
    public String getWeeklyPlan(int weekIndex) {
        return WEEKS[weekIndex];
    }
}
