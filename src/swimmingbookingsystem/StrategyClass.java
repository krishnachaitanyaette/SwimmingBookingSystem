
package swimmingbookingsystem;

public class StrategyClass {
    
    
     
    public static ReportInterface displayReport(String roleType) {
        switch (roleType) {
            case "registeredLearnerBookingReport" -> {
                return new RegisteredLearnerBookingReport();
            }
            case "coachRatingReport" -> {
                return new CoachRatingReport();
            }
            default -> throw new IllegalArgumentException("Role not found");
        }
    }
    
    
}
