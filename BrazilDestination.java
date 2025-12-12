public class BrazilDestination implements TravelDestination {

    private final String[] WEEKS = {
        "Week 1: Rio de Janeiro – beaches, samba, and Christ the Redeemer.",
        "Week 2: Salvador de Bahia – Afro-Brazilian heritage and music.",
        "Week 3: Amazon rainforest or Pantanal wetlands.",
        "Week 4: Northeastern beaches – Recife, Natal, Fortaleza."
    };

    @Override
    public String getWelcomeMessage() {
        return "Welcome to Brazil!";
    }

    @Override
    public String getWeeklyPlan(int week) {
        return WEEKS[week];
    }
}
