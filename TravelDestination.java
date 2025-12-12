public interface TravelDestination {

    String getWelcomeMessage();
    String getWeeklyPlan(int weekIndex);

    default void printRecursivePlan(int totalWeeks) {
        if (totalWeeks <= 0) return;     // base case
        printRecursivePlan(totalWeeks - 1); // recursive call
        System.out.println(getWeeklyPlan(totalWeeks - 1));
    }
}

