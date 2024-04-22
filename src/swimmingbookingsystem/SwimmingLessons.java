
package swimmingbookingsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class SwimmingLessons {
    
      
    private int lessonNo;
    private String lesson;
    private String lessonTiming;
    private String weekDay;
    private int lessonBy;
    private int gradeLevel;
    private int allowedLearners;
    private String lessonOn;
    
    public static List <SwimmingLessons> swimmingLessons = new ArrayList<>();

    public SwimmingLessons(int lessonNo, String lesson, String lessonTiming, String weekDay, int lessonBy, int gradeLevel, int allowedLearners, String lessonOn) {
        this.lessonNo = lessonNo;
        this.lesson = lesson;
        this.lessonTiming = lessonTiming;
        this.weekDay = weekDay;
        this.lessonBy = lessonBy;
        this.gradeLevel = gradeLevel;
        this.allowedLearners = allowedLearners;
        this.lessonOn = lessonOn;
    }

    public int getLessonNo() {
        return lessonNo;
    }

    public String getLesson() {
        return lesson;
    }

    public String getLessonTiming() {
        return lessonTiming;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public int getLessonBy() {
        return lessonBy;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public int getAllowedLearners() {
        return allowedLearners;
    }

    public String getLessonOn() {
        return lessonOn;
    }

    public static List<SwimmingLessons> getSwimmingLessons() {
        existingLessons();
        return swimmingLessons;
    }

    public void setAllowedLearners(int allowedLearners) {
        this.allowedLearners = allowedLearners;
    }
    
    
     //filterLessonOptions
    public static int filterLessonOptions(){     
        Scanner input = new Scanner(System.in);
        
        System.out.println("\n\nSelect your choice : ");
        System.out.println("1. All");
        System.out.println("2. Day");
        System.out.println("3. Coach");
        System.out.println("4. Grade Level");
        System.out.println("5. Back");
        System.out.print("\nInput Your Choice : ");

        String choiceOption = input.nextLine();
        while (choiceOption.equals("") || !SwimmingBookingSystem.isDigit(choiceOption))
        {
            System.out.print("\nPlease Input Correct Choice : ");
            choiceOption = input.nextLine();
        }
        return Integer.parseInt(choiceOption);
    }
 
    
    //filterLessonMenu
    public static void filterLessonMenu(){
        int selectedOption;
        do {
            selectedOption = filterLessonOptions();
            switch (selectedOption) {
                case 1 -> displayAllLessons();
                case 2 -> {
                            Scanner input = new Scanner(System.in);

                            System.out.print("\nPlease Input Day : ");
                            String day = input.nextLine();

                             if(day.equalsIgnoreCase("")){
                                do{
                                    System.out.print("\nPlease Input Day : ");
                                    day = input.nextLine();
                                }while(day.equalsIgnoreCase(""));
                            }

                            if(day.equalsIgnoreCase("Mon")){
                                day = "Monday";
                            }else if(day.equalsIgnoreCase("Wed")){
                                day = "Wednesday";
                            }else if(day.equalsIgnoreCase("Fri")){
                                day = "Friday";
                            }else if(day.equalsIgnoreCase("Sat")){
                                day = "Saturday";
                            }

                            if(!(day.equalsIgnoreCase("Monday") || day.equalsIgnoreCase("Wednesday") || day.equalsIgnoreCase("Friday") ||
                                    day.equalsIgnoreCase("Saturday"))){
                                do{
                                    System.out.print("\nYou have inputed incorrect Weekday. Please Input Correct : ");
                                    day = input.nextLine();
                                }while(!(day.equalsIgnoreCase("Monday") || day.equalsIgnoreCase("Wednesday") || day.equalsIgnoreCase("Friday") ||
                                    day.equalsIgnoreCase("Saturday")));
                            }
                            
                            viewLessonsByDay(day);
        
                        }
                case 3 -> {
                            Scanner input = new Scanner(System.in);

                            SwimmingCoaches.displayCoaches();
                            System.out.print("Please Input Coach Registered ID from above list  : ");
                            String coachUniqueID = input.nextLine();
                            
                            if(coachUniqueID.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(coachUniqueID)){
                                do{
                                    System.out.print("\nPlease Input Correct Coach Unique ID : ");
                                    coachUniqueID = input.nextLine();
                                }while(coachUniqueID.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(coachUniqueID));
                            }

                            
                            int isExistingCoach = SwimmingCoaches.isExistingCoach(Integer.parseInt(coachUniqueID));

                            if(coachUniqueID.equalsIgnoreCase("") || isExistingCoach != 1 || !SwimmingBookingSystem.isDigit(coachUniqueID)){
                                do{
                                    System.out.print("\nPlease Input Correct Coach Unique ID : ");
                                    coachUniqueID = input.nextLine();
                                    isExistingCoach = SwimmingCoaches.isExistingCoach(Integer.parseInt(coachUniqueID));
                                }while(coachUniqueID.equalsIgnoreCase("") || isExistingCoach != 1 || !SwimmingBookingSystem.isDigit(coachUniqueID));
                            }

                            viewLessonsByCoach(Integer.parseInt(coachUniqueID));
                        }
                case 4 -> {
                            Scanner input = new Scanner(System.in);

                            System.out.println("\n(1 to 5)");
                            System.out.print("Please Input Grade Level ("+RegisteredLearners.MIN_GRADE_VAL+" - "+RegisteredLearners.MAX_GRADE_VAL+""+") : ");
                            String givenGrade = input.nextLine();

                            if(givenGrade.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(givenGrade) ||
                                    (Integer.parseInt(givenGrade) < RegisteredLearners.MIN_GRADE_VAL|| Integer.parseInt(givenGrade) >
                                    RegisteredLearners.MAX_GRADE_VAL)){
                                do{
                                    System.out.print("\nPlease Input Grade Level ("+RegisteredLearners.MIN_GRADE_VAL+" - "+RegisteredLearners.MAX_GRADE_VAL+""+") : ");
                                    givenGrade = input.nextLine();
                                }while(givenGrade.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(givenGrade) ||
                                        (Integer.parseInt(givenGrade) < RegisteredLearners.MIN_GRADE_VAL || 
                                        Integer.parseInt(givenGrade) > RegisteredLearners.MAX_GRADE_VAL));
                            }
                            
                            viewLessonsByGradeLevel(Integer.parseInt(givenGrade));
                        }
                case 5 ->{
                            return;
                          }
                default -> System.out.println("\nPlease input a valid choice (1-5)");
            }
        } while (selectedOption != 5);
          
        
    }
    
    
    //Existing Lessons
    public static void existingLessons(){
        
        
        SwimmingLessons lesson1 = new SwimmingLessons(1,"Fundamental Aquatic Skills","4-5pm","Monday",1,1,
                4, "01/04/2024");
                
        SwimmingLessons lesson2 = new SwimmingLessons(2,"Crawling","5-6pm","Monday",2,2,
                4,"01/04/2024");
                
        SwimmingLessons lesson3 = new SwimmingLessons(3,"Breaststroke","6-7pm","Monday",3,3,
                4,"01/04/2024");
        
        
        SwimmingLessons lesson4 = new SwimmingLessons(4,"Front crawl,","4-5pm","Wednesday",4,2,
                4, "03/04/2024");
                
        SwimmingLessons lesson5 = new SwimmingLessons(5,"Snorkeling","5-6pm","Wednesday",5,3,
                4,"03/04/2024");
                
        SwimmingLessons lesson6 = new SwimmingLessons(6,"Dog paddle","6-7pm","Wednesday",6,4,
                4,"03/04/2024");
        
        
        SwimmingLessons lesson7 = new SwimmingLessons(7,"Fundamental Aquatic Skills","4-5pm","Friday",1,1,
                4, "05/04/2024");
                
        SwimmingLessons lesson8 = new SwimmingLessons(8,"Crawling","5-6pm","Friday",2,5,
                4,"05/04/2024");
                
        SwimmingLessons lesson9 = new SwimmingLessons(9,"Breaststroke","6-7pm","Friday",3,2,
                4,"05/04/2024");
        
        
        SwimmingLessons lesson10 = new SwimmingLessons(10,"Front crawl,","2-3pm","Saturday",2,2,
                4, "06/04/2024");
                
        SwimmingLessons lesson11 = new SwimmingLessons(11,"Snorkeling","3-4pm","Saturday",3,4,
                4,"06/04/2024");
            
 
        SwimmingLessons lesson12 = new SwimmingLessons(12,"Fundamental Aquatic Skills","4-5pm","Monday",1,1,
                4, "08/04/2024");
                
        SwimmingLessons lesson13 = new SwimmingLessons(13,"Crawling","5-6pm","Monday",2,2,
                4,"08/04/2024");
                
        SwimmingLessons lesson14 = new SwimmingLessons(14,"Breaststroke","6-7pm","Monday",3,3,
                4,"08/04/2024");
        
        
        SwimmingLessons lesson15 = new SwimmingLessons(15,"Front crawl,","4-5pm","Wednesday",4,2,
                4, "10/04/2024");
                
        SwimmingLessons lesson16 = new SwimmingLessons(16,"Snorkeling","5-6pm","Wednesday",5,3,
                4,"10/04/2024");
                
        SwimmingLessons lesson17 = new SwimmingLessons(17,"Dog paddle","6-7pm","Wednesday",6,4,
                4,"10/04/2024");
        
        
        SwimmingLessons lesson18 = new SwimmingLessons(18,"Fundamental Aquatic Skills","4-5pm","Friday",1,1,
                4, "12/04/2024");
                
        SwimmingLessons lesson19 = new SwimmingLessons(19,"Crawling","5-6pm","Friday",2,5,
                4,"12/04/2024");
                
        SwimmingLessons lesson20 = new SwimmingLessons(20,"Breaststroke","6-7pm","Friday",3,2,
                4,"12/04/2024");
        
        
        SwimmingLessons lesson21 = new SwimmingLessons(21,"Front crawl,","2-3pm","Saturday",2,2,
                4, "13/04/2024");
                
        SwimmingLessons lesson22 = new SwimmingLessons(22,"Snorkeling","3-4pm","Saturday",3,4,
                4,"13/04/2024");
            

        SwimmingLessons lesson23 = new SwimmingLessons(23,"Fundamental Aquatic Skills","4-5pm","Monday",1,1,
                4, "15/04/2024");
                
        SwimmingLessons lesson24 = new SwimmingLessons(24,"Crawling","5-6pm","Monday",2,2,
                4,"15/04/2024");
                
        SwimmingLessons lesson25 = new SwimmingLessons(25,"Breaststroke","6-7pm","Monday",3,3,
                4,"15/04/2024");
        
        
        SwimmingLessons lesson26 = new SwimmingLessons(26,"Front crawl,","4-5pm","Wednesday",4,2,
                4, "17/04/2024");
                
        SwimmingLessons lesson27 = new SwimmingLessons(27,"Snorkeling","5-6pm","Wednesday",5,3,
                4,"17/04/2024");
                
        SwimmingLessons lesson28 = new SwimmingLessons(28,"Dog paddle","6-7pm","Wednesday",6,4,
                4,"17/04/2024");
        
        
        SwimmingLessons lesson29 = new SwimmingLessons(29,"Fundamental Aquatic Skills","4-5pm","Friday",1,1,
                4, "19/04/2024");
                
        SwimmingLessons lesson30 = new SwimmingLessons(30,"Crawling","5-6pm","Friday",2,5,
                4,"19/04/2024");
                
        SwimmingLessons lesson31 = new SwimmingLessons(31,"Breaststroke","6-7pm","Friday",3,2,
                4,"19/04/2024");
        
        
        SwimmingLessons lesson32 = new SwimmingLessons(32,"Front crawl,","2-3pm","Saturday",2,2,
                4, "20/04/2024");
                
        SwimmingLessons lesson33 = new SwimmingLessons(33,"Snorkeling","3-4pm","Saturday",3,4,
                4,"20/04/2024");
        
                
        SwimmingLessons lesson34 = new SwimmingLessons(34,"Fundamental Aquatic Skills","4-5pm","Monday",1,1,
                4, "22/04/2024");
                
        SwimmingLessons lesson35 = new SwimmingLessons(35,"Crawling","5-6pm","Monday",2,2,
                4,"22/04/2024");
                
        SwimmingLessons lesson36 = new SwimmingLessons(36,"Breaststroke","6-7pm","Monday",3,3,
                4,"22/04/2024");
        
        
        SwimmingLessons lesson37 = new SwimmingLessons(37,"Front crawl,","4-5pm","Wednesday",4,2,
                4, "24/04/2024");
                
        SwimmingLessons lesson38 = new SwimmingLessons(38,"Snorkeling","5-6pm","Wednesday",5,3,
                4,"24/04/2024");
                
        SwimmingLessons lesson39 = new SwimmingLessons(39,"Dog paddle","6-7pm","Wednesday",6,4,
                4,"24/04/2024");
        
        
        SwimmingLessons lesson40 = new SwimmingLessons(40,"Fundamental Aquatic Skills","4-5pm","Friday",1,1,
                4, "26/04/2024");
                
        SwimmingLessons lesson41 = new SwimmingLessons(41,"Crawling","5-6pm","Friday",2,5,
                4,"26/04/2024");
                
        SwimmingLessons lesson42 = new SwimmingLessons(42,"Breaststroke","6-7pm","Friday",3,2,
                4,"26/04/2024");
        
        
        SwimmingLessons lesson43 = new SwimmingLessons(43,"Front crawl,","2-3pm","Saturday",2,2,
                4, "27/04/2024");
                
        SwimmingLessons lesson44 = new SwimmingLessons(44,"Snorkeling","3-4pm","Saturday",3,4,
                4,"27/04/2024");
            
        
        SwimmingLessons.swimmingLessons.add(lesson1);
        SwimmingLessons.swimmingLessons.add(lesson2);
        SwimmingLessons.swimmingLessons.add(lesson3);
        SwimmingLessons.swimmingLessons.add(lesson4);
        SwimmingLessons.swimmingLessons.add(lesson5);
        SwimmingLessons.swimmingLessons.add(lesson6);
        SwimmingLessons.swimmingLessons.add(lesson7);
        SwimmingLessons.swimmingLessons.add(lesson8);
        SwimmingLessons.swimmingLessons.add(lesson9);
        SwimmingLessons.swimmingLessons.add(lesson10);
        SwimmingLessons.swimmingLessons.add(lesson11);
        SwimmingLessons.swimmingLessons.add(lesson12);
        SwimmingLessons.swimmingLessons.add(lesson13);
        SwimmingLessons.swimmingLessons.add(lesson14);
        SwimmingLessons.swimmingLessons.add(lesson15);
        SwimmingLessons.swimmingLessons.add(lesson16);
        SwimmingLessons.swimmingLessons.add(lesson17);
        SwimmingLessons.swimmingLessons.add(lesson18);
        SwimmingLessons.swimmingLessons.add(lesson19);
        SwimmingLessons.swimmingLessons.add(lesson20);
        SwimmingLessons.swimmingLessons.add(lesson21);
        SwimmingLessons.swimmingLessons.add(lesson22);
        SwimmingLessons.swimmingLessons.add(lesson23);
        SwimmingLessons.swimmingLessons.add(lesson24);
        SwimmingLessons.swimmingLessons.add(lesson25);
        SwimmingLessons.swimmingLessons.add(lesson26);
        SwimmingLessons.swimmingLessons.add(lesson27);
        SwimmingLessons.swimmingLessons.add(lesson28);
        SwimmingLessons.swimmingLessons.add(lesson29);
        SwimmingLessons.swimmingLessons.add(lesson30);
        SwimmingLessons.swimmingLessons.add(lesson31);
        SwimmingLessons.swimmingLessons.add(lesson32);
        SwimmingLessons.swimmingLessons.add(lesson33);
        SwimmingLessons.swimmingLessons.add(lesson34);
        SwimmingLessons.swimmingLessons.add(lesson35);
        SwimmingLessons.swimmingLessons.add(lesson36);
        SwimmingLessons.swimmingLessons.add(lesson37);
        SwimmingLessons.swimmingLessons.add(lesson38);
        SwimmingLessons.swimmingLessons.add(lesson39);
        SwimmingLessons.swimmingLessons.add(lesson40);
        SwimmingLessons.swimmingLessons.add(lesson41);
        SwimmingLessons.swimmingLessons.add(lesson42);
        SwimmingLessons.swimmingLessons.add(lesson43);
        SwimmingLessons.swimmingLessons.add(lesson44);
        
        
    }
    
    
    //Lessons of selected day
    public static void viewLessonsByDay(String day){
        
        System.out.println("\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-30s | %-15s | %-15s | %-15s | %-15s | %-10s | %-20s | \n",
                "LessonNo","Lesson", "GradeLevel", "Day","Time", "Date", "Seats","Coach");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
         List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
        List<SwimmingCoaches> swimmingCoaches = SwimmingCoaches.getSwimmingCoaches();
        
        Set<String> lessonrecords = new HashSet<>(); 
        for(SwimmingLessons obj : swimmingLessons){
            if (!lessonrecords.contains(String.valueOf(obj.getLessonNo()))){
                lessonrecords.add(String.valueOf(obj.getLessonNo()));
                
                if(obj.getWeekDay().equalsIgnoreCase(day)){
                    String name = "";
                    for(SwimmingCoaches swimmingCoachObj : swimmingCoaches){
                        if(swimmingCoachObj.getRegisteredID() == obj.getLessonBy()){
                            name = swimmingCoachObj.getName();
                            break;
                        }
                    }

                     System.out.printf("| %-15s | %-30s | %-15s | %-15s | %-15s | %-15s | %-10s | %-20s |\n",
                    obj.getLessonNo(),obj.getLesson(), obj.getGradeLevel(),obj.getWeekDay(), obj.getLessonTiming(),
                    obj.getLessonOn(), obj.getAllowedLearners(),name);
                }
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");

        Scanner input = new Scanner(System.in);
      
        if(ClassBookings.NEW_BOOKING){
            
            System.out.print("\nPlease Input Lesson No : ");
            String lessonUniqueNo = input.nextLine();

            int lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));
            
            if(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1){
                do{
                    System.out.print("\nPlease Input Lesson No : ");
                    lessonUniqueNo = input.nextLine();
                   lessonNo = SwimmingLessons.isExistingLesson(Integer.parseInt(lessonUniqueNo));

                }while(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1 );
            }
        
            ClassBookings.bookNewClass(Integer.parseInt(lessonUniqueNo));
            return;
        }
        
        if(ClassBookings.UPDATE_BOOKING){
         
            System.out.print("\nPlease Input New Lesson No : ");
            String lessonUniqueNo = input.nextLine();

            int lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));
            
             if(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1){
                do{
                    System.out.print("\nPlease Input Lesson No: ");
                    lessonUniqueNo = input.nextLine();
                   lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));

                }while(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1);
            }
             
             //Old class
            int oldlessonNo = 0;
            List<ClassBookings> classBookings = ClassBookings.getClassBookings();
            for(ClassBookings obj : classBookings){
                if(obj.getBookingUniqNo() == ClassBookings.bookingNoTempVariable){
                    oldlessonNo = obj.getLessonNo();
                    break;
                }
            }

            ClassBookings.changeBooking(ClassBookings.bookingNoTempVariable,oldlessonNo,Integer.parseInt(lessonUniqueNo));
            return;
        
        }
    }
    
    
    
    //Lessons of selected coach
    public static void viewLessonsByCoach(int coachRegisteredID){
         
        System.out.println("\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-30s | %-15s | %-15s | %-15s | %-15s | %-10s | %-20s | \n",
                "LessonNo","Lesson", "GradeLevel", "Day","Time", "Date", "Seats","Coach");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
        List<SwimmingCoaches> swimmingCoaches = SwimmingCoaches.getSwimmingCoaches();
        
        Set<String> lessonrecords = new HashSet<>(); 
        for(SwimmingLessons obj : swimmingLessons){
            if (!lessonrecords.contains(String.valueOf(obj.getLessonNo()))){
                lessonrecords.add(String.valueOf(obj.getLessonNo()));
                
                if(obj.getLessonBy() == coachRegisteredID){
                    String name = "";
                    for(SwimmingCoaches swimmingCoachObj : swimmingCoaches){
                        if(swimmingCoachObj.getRegisteredID() == obj.getLessonBy()){
                            name = swimmingCoachObj.getName();
                            break;
                        }
                    }

                    System.out.printf("| %-15s | %-30s | %-15s | %-15s | %-15s | %-15s | %-10s | %-20s |\n",
                obj.getLessonNo(),obj.getLesson(), obj.getGradeLevel(),obj.getWeekDay(), obj.getLessonTiming(),
                obj.getLessonOn(), obj.getAllowedLearners(),name);
                }
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");

              
        Scanner input = new Scanner(System.in);
        
        if(ClassBookings.NEW_BOOKING){
            
            System.out.print("\nPlease Input Lesson No : ");
            String lessonUniqueNo = input.nextLine();

            int lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));
            
            if(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1){
                do{
                    System.out.print("\nPlease Input Lesson No : ");
                    lessonUniqueNo = input.nextLine();
                   lessonNo = SwimmingLessons.isExistingLesson(Integer.parseInt(lessonUniqueNo));

                }while(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1 );
            }
        
            ClassBookings.bookNewClass(Integer.parseInt(lessonUniqueNo));
            return;
        }
        
        if(ClassBookings.UPDATE_BOOKING){

            System.out.print("\nPlease Input New Lesson No : ");
            String lessonUniqueNo = input.nextLine();

            int lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));
            
             if(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1){
                do{
                    System.out.print("\nPlease Input Lesson No: ");
                    lessonUniqueNo = input.nextLine();
                   lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));

                }while(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1);
            }
             
             //Old class
            int oldlessonNo = 0;
            List<ClassBookings> classBookings = ClassBookings.getClassBookings();
            for(ClassBookings obj : classBookings){
                if(obj.getBookingUniqNo() == ClassBookings.bookingNoTempVariable){
                    oldlessonNo = obj.getLessonNo();
                    break;
                }
            }

            ClassBookings.changeBooking(ClassBookings.bookingNoTempVariable,oldlessonNo,Integer.parseInt(lessonUniqueNo));
            return;
        
        }
    }
    
    
    
    //Lessons of Selected Grade Level
    public static void viewLessonsByGradeLevel(int gradeLevel){
         
        System.out.println("\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-30s | %-15s | %-15s | %-15s | %-15s | %-10s | %-20s | \n",
                "LessonNo","Lesson", "GradeLevel","Day", "Time", "LessonOn", "Seats","LessonBy");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
        List<SwimmingCoaches> swimmingCoaches = SwimmingCoaches.getSwimmingCoaches();
        
        Set<String> lessonrecords = new HashSet<>(); 
        for(SwimmingLessons obj : swimmingLessons){
            if (!lessonrecords.contains(String.valueOf(obj.getLessonNo()))){
                lessonrecords.add(String.valueOf(obj.getLessonNo()));
                
                if(obj.getGradeLevel() == gradeLevel){
                    String name = "";
                    for(SwimmingCoaches swimmingCoachObj : swimmingCoaches){
                        if(swimmingCoachObj.getRegisteredID() == obj.getLessonBy()){
                            name = swimmingCoachObj.getName();
                            break;
                        }
                    }

                     System.out.printf("| %-15s | %-30s | %-15s | %-15s | %-15s | %-15s | %-10s | %-20s |\n",
                    obj.getLessonNo(),obj.getLesson(), obj.getGradeLevel(),obj.getWeekDay(), obj.getLessonTiming(),
                    obj.getLessonOn(), obj.getAllowedLearners(),name);
                }
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");

              
        Scanner input = new Scanner(System.in);
        
        if(ClassBookings.NEW_BOOKING){

            System.out.print("\nPlease Input Lesson No : ");
            String lessonUniqueNo = input.nextLine();

            int lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));
            
            if(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1){
                do{
                    System.out.print("\nPlease Input Lesson No : ");
                    lessonUniqueNo = input.nextLine();
                   lessonNo = SwimmingLessons.isExistingLesson(Integer.parseInt(lessonUniqueNo));

                }while(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1 );
            }
        
            ClassBookings.bookNewClass(Integer.parseInt(lessonUniqueNo));
            return;
        }
        
        if(ClassBookings.UPDATE_BOOKING){
           
            System.out.print("\nPlease Input New Lesson No : ");
            String lessonUniqueNo = input.nextLine();

            int lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));
            
             if(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1){
                do{
                    System.out.print("\nPlease Input Lesson No: ");
                    lessonUniqueNo = input.nextLine();
                   lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));

                }while(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1);
            }
             
             //Old class
            int oldlessonNo = 0;
            List<ClassBookings> classBookings = ClassBookings.getClassBookings();
            for(ClassBookings obj : classBookings){
                if(obj.getBookingUniqNo() == ClassBookings.bookingNoTempVariable){
                    oldlessonNo = obj.getLessonNo();
                    break;
                }
            }

            ClassBookings.changeBooking(ClassBookings.bookingNoTempVariable,oldlessonNo,Integer.parseInt(lessonUniqueNo));
            return;
        
        }
    }
    
    
    
    //Display All Lessons
    public static void displayAllLessons(){
         
        System.out.println("\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-30s | %-15s | %-15s | %-15s | %-15s | %-10s | %-20s | \n",
                "LessonNo","Lesson", "GradeLevel","Day", "Time", "LessonOn", "Seats","LessonBy");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
        List<SwimmingCoaches> swimmingCoaches = SwimmingCoaches.getSwimmingCoaches();
        
        Set<String> lessonrecords = new HashSet<>(); 
        for(SwimmingLessons obj : swimmingLessons){
            if (!lessonrecords.contains(String.valueOf(obj.getLessonNo()))){
                lessonrecords.add(String.valueOf(obj.getLessonNo()));
                
                String coachName = "";
                for(SwimmingCoaches swimmingCoachObj : swimmingCoaches){
                    if(swimmingCoachObj.getRegisteredID() == obj.getLessonBy()){
                        coachName = swimmingCoachObj.getName();
                        break;
                    }
                }

                 System.out.printf("| %-15s | %-30s | %-15s | %-15s | %-15s | %-15s | %-10s | %-20s |\n",
                obj.getLessonNo(),obj.getLesson(), obj.getGradeLevel(),obj.getWeekDay(), obj.getLessonTiming(),
                obj.getLessonOn(), obj.getAllowedLearners(),coachName);
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");

        Scanner input = new Scanner(System.in);
              
        if(ClassBookings.NEW_BOOKING){
            

            System.out.print("\nPlease Input Lesson No : ");
            String lessonUniqueNo = input.nextLine();

            int lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));
            
            if(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1){
                do{
                    System.out.print("\nPlease Input Lesson No : ");
                    lessonUniqueNo = input.nextLine();
                   lessonNo = SwimmingLessons.isExistingLesson(Integer.parseInt(lessonUniqueNo));

                }while(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1 );
            }
        
            ClassBookings.bookNewClass(Integer.parseInt(lessonUniqueNo));
            return;
        }
        
        if(ClassBookings.UPDATE_BOOKING){
      
            System.out.print("\nPlease Input New Lesson No : ");
            String lessonUniqueNo = input.nextLine();

            int lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));
            
             if(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1){
                do{
                    System.out.print("\nPlease Input Lesson No: ");
                    lessonUniqueNo = input.nextLine();
                   lessonNo = isExistingLesson(Integer.parseInt(lessonUniqueNo));

                }while(lessonUniqueNo.equalsIgnoreCase("") || lessonNo != 1);
            }
             
             //Old class
            int oldlessonNo = 0;
            List<ClassBookings> classBookings = ClassBookings.getClassBookings();
            for(ClassBookings obj : classBookings){
                if(obj.getBookingUniqNo() ==ClassBookings.bookingNoTempVariable){
                    oldlessonNo = obj.getLessonNo();
                    break;
                }
            }

            ClassBookings.changeBooking(ClassBookings.bookingNoTempVariable,oldlessonNo,Integer.parseInt(lessonUniqueNo));
            return;
        
        }
    }
    
    
    //existing lesson or not
    public static int isExistingLesson(int lessonNo){
        int isExisting = 0;
        
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
        for(SwimmingLessons obj : swimmingLessons){
            if(obj.getLessonNo() == lessonNo){
                isExisting = 1;
                break;
            }
        }
        
        return isExisting;
    }
    
    
    //Update Allowed Learners Seats
    public static int getAllowedLearnerSeats(int lessonNo){
        int available = 0;
        int seats = 0;
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
        for(SwimmingLessons obj : swimmingLessons){
            if(obj.getLessonNo() == lessonNo){
                seats = obj.getAllowedLearners();
                break;
            }
        }
        if(seats == 0){
            available = 1;
        }
        return available;
    }
    
    
    //Update Allowed Learners Seats
    public static void updateAllowedLearnerSeats(int lessonNo, int seats){        
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
         for(SwimmingLessons obj : swimmingLessons){
            if(obj.getLessonNo() == lessonNo){
                obj.setAllowedLearners(obj.getAllowedLearners() - seats);
                break;
            }
        }
    }
    
    
    
}
