public class ColombiaDestination implements TravelDestination {

    private final String[] WEEKS = {
        "Week 1: Bogota and Medellin – museums and nightlife.",
        "Week 2: Cartagena – colonial city and beaches.",
        "Week 3: Coffee Triangle – rural landscapes and culture.",
        "Week 4: San Andres – Caribbean paradise."
    };

    @Override
    public String getWelcomeMessage() {
        return "Welcome to Colombia!";
    }

    @Override
    public String getWeeklyPlan(int weekIndex) {
        return WEEKS[weekIndex];
    }
}
