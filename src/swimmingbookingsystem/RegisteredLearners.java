
package swimmingbookingsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class RegisteredLearners {
    
     
    private int registeredID;
    private String name;
    private int gradeLevel;
    private String contact;
    private String gender;
    private String password;
    private int age;

    
    public static final int MIN_GRADE_VAL = 0;
    public static final int MAX_GRADE_VAL = 5;
    public static final int MIN_AGE_VAL = 4;
    public static final int MAX_AGE_VAL = 11;
    
    public static List <RegisteredLearners> regsiteredLearners = new ArrayList<>();

    public RegisteredLearners(int registeredID, String name, int gradeLevel, String contact, String gender, String password, int age) {
        this.registeredID = registeredID;
        this.name = name;
        this.gradeLevel = gradeLevel;
        this.contact = contact;
        this.gender = gender;
        this.password = password;
        this.age = age;
    }

    public int getRegisteredID() {
        return registeredID;
    }

    public String getName() {
        return name;
    }

    public int getGradeLevel() {
        return gradeLevel;
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

    public int getAge() {
        return age;
    }

    public static List<RegisteredLearners> getRegisteredLearners() {
        regiteredLearners();
        return regsiteredLearners;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

      
    //regiteredLearners
    public static void regiteredLearners(){
        
        RegisteredLearners reglearner1 = new RegisteredLearners(1,"Lee Ellis",1,"01253 821392",
                "Male","password1",5);
       
        RegisteredLearners reglearner2 = new RegisteredLearners(2,"Lily Miller",2,"0870 333 1181",
                "Female","password2",9);
       
        RegisteredLearners reglearner3 = new RegisteredLearners(3,"Rosie Hall",0,"07974 319822",
                "Male","password3",7);
       
        RegisteredLearners reglearner4 = new RegisteredLearners(4,"Stephen Patel",4,"0121 440 1954",
                "Male","password4",5);
       
        RegisteredLearners reglearner5 = new RegisteredLearners(5,"Steve Campbell",5,"0131 443 6898",
                "Male","password1",6);
       
        RegisteredLearners reglearner6 = new RegisteredLearners(6,"Edward Evans",1,"020 7723 1026",
                "Male","password5",10);
       
        RegisteredLearners reglearner7 = new RegisteredLearners(7,"Jim Bailey",2,"028 9031 4905",
                "Male","password6",11);
       
        RegisteredLearners reglearner8 = new RegisteredLearners(8,"James Edwards",3,"01684 581500",
                "Male","password7",7);
       
        RegisteredLearners reglearner9 = new RegisteredLearners(9,"Georgia Lee",4,"01384 817817",
                "Male","password8",6);
       
        RegisteredLearners reglearner10 = new RegisteredLearners(10,"Bethany",1,"01733 322377",
                "Female","password9",5);
       
        RegisteredLearners reglearner11 = new RegisteredLearners(11,"Marshall",2,"023 9245 4333",
                "Female","password10",4);
       
        RegisteredLearners reglearner12 = new RegisteredLearners(12,"Selina",1,"01322 836303",
                "Female","password11",8);
       
        RegisteredLearners reglearner13 = new RegisteredLearners(13,"Kelly",4,"0191 587 8650",
                "Female","password12",9);
       
        RegisteredLearners reglearner14 = new RegisteredLearners(14,"Murphy",5,"01926 863061",
                "Female","password13",10);
       
        
        RegisteredLearners.regsiteredLearners.add(reglearner1);
        RegisteredLearners.regsiteredLearners.add(reglearner2);
        RegisteredLearners.regsiteredLearners.add(reglearner3);
        RegisteredLearners.regsiteredLearners.add(reglearner4);
        RegisteredLearners.regsiteredLearners.add(reglearner5);
        RegisteredLearners.regsiteredLearners.add(reglearner6);
        RegisteredLearners.regsiteredLearners.add(reglearner7);
        RegisteredLearners.regsiteredLearners.add(reglearner8);
        RegisteredLearners.regsiteredLearners.add(reglearner9);
        RegisteredLearners.regsiteredLearners.add(reglearner10);
        RegisteredLearners.regsiteredLearners.add(reglearner11);
        RegisteredLearners.regsiteredLearners.add(reglearner12);
        RegisteredLearners.regsiteredLearners.add(reglearner13);
        RegisteredLearners.regsiteredLearners.add(reglearner14);
        
        
        
    }
    
    
    
    //Display Learners
    public static void displayLearners(){
        System.out.println("\n\n----------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-10s | %-10s | \n",
                "RegisteredID","Name", "Grade Level","Contact","Age", "Gender");
        System.out.println("----------------------------------------------------------------------------------------------------");
        Set<Integer> uniqueRecords = new HashSet<>(); 
         List<RegisteredLearners> learnerData = RegisteredLearners.getRegisteredLearners();
        for(RegisteredLearners obj : learnerData){
            if (!uniqueRecords.contains(obj.getRegisteredID())){
                uniqueRecords.add(obj.getRegisteredID());     

                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-10s | %-10s | \n",
                obj.getRegisteredID(),obj.getName(), obj.getGradeLevel(),obj.getContact(),obj.getAge()+"yrs old", obj.getGender());
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
    
    
    
    //Select class is allowe dor not
    public static boolean allowedSelectedLesson(int lessonNo){
        boolean allowed = false;
        
        int userGrade  = 0;
        int classGrade  = 0;
        
        //Class Grade
         List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
        for(SwimmingLessons obj : swimmingLessons){
            if(obj.getLessonNo() == lessonNo){
                classGrade = obj.getGradeLevel();
                break;
            }
        }
        //Student current  Grade
        List<RegisteredLearners> learnerData = RegisteredLearners.getRegisteredLearners();
        for(RegisteredLearners obj : learnerData){
            if(obj.getRegisteredID() == LoggedInDetails.id){
                userGrade = obj.getGradeLevel();
                break;
            }
        }
       
        if(classGrade > (userGrade + 1) || (classGrade < userGrade)){
            allowed = true;
        }
        return allowed;
    }
    
    
    //Update Grade Level
    public static void updateGrade(int lessonNo){
        
        int userGrade  = 0;
        int classGrade  = 0;
        
        //Class Grade
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
        for(SwimmingLessons obj : swimmingLessons){
            if(obj.getLessonNo() == lessonNo){
                classGrade = obj.getGradeLevel();
                break;
            }
        }
        //Student current  Grade
       List<RegisteredLearners> learnerData = RegisteredLearners.getRegisteredLearners();
        for(RegisteredLearners obj : learnerData){
            if(obj.getRegisteredID() == LoggedInDetails.id){
                classGrade = obj.getGradeLevel();
                break;
            }
        }
       
         //Update
       if(classGrade > userGrade){
            for(RegisteredLearners obj : learnerData){
                if(obj.getRegisteredID() == LoggedInDetails.id){
                    obj.setGradeLevel(obj.getGradeLevel()+1);
                    break;
                }
            }
        }
        
    }
    
    
    
         
    //registerdLearnerDashboardOption
    public static void registerdLearnerDashboardOption(){     
        int selectedOption;
        do {
            selectedOption = registerdLearnerDashboard();
            
            switch (selectedOption) {
                case 1 -> SwimmingLessons.filterLessonMenu();
                case 2 -> {
                            ClassBookings.NEW_BOOKING = true;
                            SwimmingLessons.filterLessonMenu();
                            ClassBookings.NEW_BOOKING = false;
                          }
                case 3 -> ClassBookings.cancelBooking();
                case 4 -> {
                            int bookingNo = ClassBookings.inputBookingNoFromLearner();
                            ClassBookings.bookingNoTempVariable = bookingNo;
                            ClassBookings.UPDATE_BOOKING = true;
                            SwimmingLessons.filterLessonMenu();
                            ClassBookings.UPDATE_BOOKING = false;
                            ClassBookings.bookingNoTempVariable = 0;
                         }
                case 5 -> LessonReviews.addReviewForCoach();
                case 6 -> ClassBookings.displayAllBookings();
                case 7 -> {
                            LoggedInDetails.logout();
                            return;
                        }
                default -> System.out.println("\nPlease input a valid choice (1-7)");
            }
        } while (selectedOption != 7);
    }
      
    
     //Learner Options
    public static int registerdLearnerDashboard(){     
        Scanner input = new Scanner(System.in);

        System.out.println("\n\nSelect your choice : ");
        System.out.println("1. Timetable");
        System.out.println("2. Book");
        System.out.println("3. Cancel");
        System.out.println("4. Change");
        System.out.println("5. Attend");
        System.out.println("6. Display Bookings");
        System.out.println("7. Logout");
        System.out.print("\nInput Your Choice : ");

        String choiceOption = input.nextLine();
        while (choiceOption.equals("") || !SwimmingBookingSystem.isDigit(choiceOption))
        {
            System.out.print("\nPlease input Correct Choice : ");
            choiceOption = input.nextLine();
        }
        return Integer.parseInt(choiceOption);
    }
 
    
    
}
