
package swimmingbookingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ManagingStaffs {
    
    
    private int staffRegID;
    private String name;
    private String contact;
    private String gender;
    private String password;
    
    public static List <ManagingStaffs> managingStaffs = new ArrayList<>();

    public ManagingStaffs(int staffRegID, String name, String contact, String gender, String password) {
        this.staffRegID = staffRegID;
        this.name = name;
        this.contact = contact;
        this.gender = gender;
        this.password = password;
    }

    public int getStaffRegID() {
        return staffRegID;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public static List<ManagingStaffs> getManagingStaffs() {
        ManagingStaffs staff1 = new ManagingStaffs(1,"Staff","0131 557 9510","Male","staff@55");
        ManagingStaffs.managingStaffs.add(staff1);        
        return managingStaffs;
    }

    
     
    //Managing Staff dashboard Option
    public static int managingStaffDashboardOptions(){     
        Scanner input = new Scanner(System.in);

        System.out.println("\n\nSelect your choice : ");
        System.out.println("1. Added Reviews");
        System.out.println("2. Registered Learners");
        System.out.println("3. Bookings");
        System.out.println("4. Learner Bookings Report");
        System.out.println("5. Coach Reviews Report");
        System.out.println("6. Logout");
        System.out.print("\nEnter Your Choice : ");

        String choiceOption = input.nextLine();
        while (choiceOption.equals("") || !SwimmingBookingSystem.isDigit(choiceOption))
        {
            System.out.print("\nPlease Enter Correct Choice : ");
            choiceOption = input.nextLine();
        }
        return Integer.parseInt(choiceOption);
    }
    
    
    
    
   //Managing Staff dashboard 
    public static void managingStaffDashboard(){     
        int selectedOption;
        do {
            selectedOption = managingStaffDashboardOptions();
            switch (selectedOption) {
                case 1 -> LessonReviews.displayReviews();
                case 2 -> RegisteredLearners.displayLearners();
                case 3 -> ClassBookings.displayAllBookings();
                case 4 -> {
                            String roleType = "registeredLearnerBookingReport"; 
                            ReportInterface report = StrategyClass.displayReport(roleType);
                            report.viewReport();
                          }
                case 5 -> {
                            String roleType = "coachRatingReport"; 
                            ReportInterface report = StrategyClass.displayReport(roleType);
                            report.viewReport();
                           }
                case 6 -> {
                            LoggedInDetails.logout();
                            return;
                        }
                default -> System.out.println("\nPlease input a valid choice (1-6)");
            }
        } while (selectedOption != 6);
    }

    
}
