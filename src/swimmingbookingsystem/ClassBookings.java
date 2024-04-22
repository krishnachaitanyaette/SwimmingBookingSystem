
package swimmingbookingsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class ClassBookings {
    
    private int bookingUniqNo;
    private int lessonNo;
    private int registeredID;
    private String bookingStatus;
    
    public static List <ClassBookings> classBookings = new ArrayList<>();
    
    
    public static int bookingNoTempVariable = 0;
    public static boolean NEW_BOOKING = false;
    public static boolean UPDATE_BOOKING = false;
    public static final String CLASS_BOOKED = "Booked";
    public static final String CLASS_CHANGED = "Changed";
    public static final String CLASS_CANCEL = "Cancelled";
    public static final String CLASS_ATTENDED = "Attended";
    

    public ClassBookings(int bookingUniqNo, int lessonNo, int registeredID, String bookingStatus) {
        this.bookingUniqNo = bookingUniqNo;
        this.lessonNo = lessonNo;
        this.registeredID = registeredID;
        this.bookingStatus = bookingStatus;
    }

    public int getBookingUniqNo() {
        return bookingUniqNo;
    }

    public int getLessonNo() {
        return lessonNo;
    }

    public int getRegisteredID() {
        return registeredID;
    }
    
    public String getBookingStatus() {
        return bookingStatus;
    }

    public static List<ClassBookings> getClassBookings() {
        return classBookings;
    }

    public void setLessonNo(int lessonNo) {
        this.lessonNo = lessonNo;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

  
    //Display Bookings
    public static void displayAllBookings(){
        
        List<ClassBookings> classBookings = ClassBookings.getClassBookings();
        List<RegisteredLearners> regsiteredLearners = RegisteredLearners.getRegisteredLearners();
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
                          
        if(classBookings.size() < 1){
            System.out.println("\nNo Data Found"); 
            return;
        }
                
        System.out.println("\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-11s | %-9s | %-30s | %-15s | %-9s | %-12s | %-15s | %-10s | %-20s | %-15s | %-15s |\n",
                "BookingNo","LessonNo", "Lesson", "LessonGrade","WeekDay","Timing","LessonOn", 
                "Status","LessonBy","LearnerRegID","LearnerGrade");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        
        Set<Integer> uniqueRecords = new HashSet<>(); 
        
        for(ClassBookings obj : classBookings){
            
            //Lesson Details
            String lesson = "";
            String classGrade = "";
            String day = "";
            String date = "";
            String timing = "";
            String coachName = "";
            for(SwimmingLessons SwimmingLessonsObj : swimmingLessons){
                if(obj.getLessonNo() == SwimmingLessonsObj.getLessonNo()){
                    lesson = SwimmingLessonsObj.getLesson();
                    classGrade = String.valueOf(SwimmingLessonsObj.getGradeLevel());
                    day = SwimmingLessonsObj.getWeekDay();
                    date = SwimmingLessonsObj.getLessonOn();
                    timing = SwimmingLessonsObj.getLessonTiming();
                    
                    SwimmingCoaches coachObj = SwimmingCoaches.getCoachObject(obj.getBookingUniqNo());
                    coachName = coachObj.getName();
                    break;
                }
            }
            
            //Learner Details
            String learnername = "";
            int learnerCurrentGrade = 0;
            for(RegisteredLearners regsiteredLearnersObj : regsiteredLearners){
                if(regsiteredLearnersObj.getRegisteredID() == obj.getRegisteredID()){
                    learnername = regsiteredLearnersObj.getName();
                    learnerCurrentGrade = regsiteredLearnersObj.getGradeLevel();
                    break;
                }
            }
            
            //Display
            if (!uniqueRecords.contains(obj.getBookingUniqNo())){
                uniqueRecords.add(obj.getBookingUniqNo());     

                //Learner
                if(LoggedInDetails.roleType.equalsIgnoreCase("Learner") && obj.getRegisteredID() == LoggedInDetails.id){
  
                    System.out.printf("| %-11s | %-9s | %-30s | %-15s | %-9s | %-12s | %-15s | %-10s | %-20s | %-15s | %-15s |\n",
                   obj.getBookingUniqNo(),obj.getLessonNo(), lesson, classGrade,day,timing,date, 
                   obj.getBookingStatus(),coachName,learnername,learnerCurrentGrade);
                //Supervisor
                }else if(LoggedInDetails.roleType.equalsIgnoreCase("ManagingStaff")){
                    System.out.printf("| %-11s | %-9s | %-30s | %-15s | %-9s | %-12s | %-15s | %-10s | %-20s | %-15s | %-15s |\n",
                       obj.getBookingUniqNo(),obj.getLessonNo(), lesson, classGrade,day,timing,date, 
                       obj.getBookingStatus(),coachName,learnername,learnerCurrentGrade);
                }
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    
    //Book a new lesson
    public static void bookNewClass(int lessonNo){
        
        //Seats Availability
        int seatsAvailability = SwimmingLessons.getAllowedLearnerSeats(lessonNo);
        if(seatsAvailability == 1){
            System.out.println("\nMaximum booking for selected lesson has been reached");
            return;
        }
        
        //duplicate Booking
        int duplicateBooking = duplicateBooking(lessonNo);
        if(duplicateBooking == 1){
            System.out.println("\nAlready Booked Selected Lesson");
            return;
        }
        
        //Is lesson of Current Grade level
        boolean allowedSelectedLesson = RegisteredLearners.allowedSelectedLesson(lessonNo);
        if(allowedSelectedLesson){
                System.out.println("\nSelected Lesson is neither of your current grade level nor one level higher");
                return;
        }
                        
        Random random = new Random();
        int bookingNo = random.nextInt(10000);
        
        bookingNoTempVariable = bookingNo;
        ClassBookings newClassObj = new ClassBookings(bookingNo,lessonNo,LoggedInDetails.id,CLASS_BOOKED);
        ClassBookings.classBookings.add(newClassObj);
        
         //Update available seats 
        SwimmingLessons.updateAllowedLearnerSeats(lessonNo,1);
        
        System.out.println("\nBooked Lesson");
        return;
        
    }
    
    
     //duplicate booking
    public static int duplicateBooking(int lessonNo){
        int isDulplicate = 0;
        
        List<ClassBookings> classBookings = ClassBookings.getClassBookings();
        for(ClassBookings obj : classBookings){
            if(obj.getRegisteredID() == LoggedInDetails.id && obj.getLessonNo() == lessonNo &&
                    (obj.getBookingStatus().equalsIgnoreCase(CLASS_BOOKED) || obj.getBookingStatus().equalsIgnoreCase(CLASS_CHANGED))){
                isDulplicate = 1;
                break;
            }
        }
        return isDulplicate;
    }
    
    
     //isExistingBookingNo
    public static int isExistingBookingNo(int bookingNo){
        int isExisting = 0;
        
        List<ClassBookings> classBookings = ClassBookings.getClassBookings();
        for(ClassBookings obj : classBookings){
            if(obj.getBookingUniqNo() == bookingNo){
                isExisting = 1;
                break;
            }
        }
        return isExisting;
    }
    
    
    //Change Booking
    public static void changeBooking(int bookingNo, int oldBookedLessonNo, int newlessonNo){
        List<ClassBookings> classBookings = ClassBookings.getClassBookings();
        
        //Update available seats 
        SwimmingLessons.updateAllowedLearnerSeats(oldBookedLessonNo,-1);
        
        for(ClassBookings obj : classBookings){
            if(obj.getBookingUniqNo() == bookingNo){
                obj.setLessonNo(newlessonNo);
                obj.setBookingStatus(CLASS_CHANGED);
                break;
            }
        }
        
         //Update available seats 
        SwimmingLessons.updateAllowedLearnerSeats(newlessonNo,1);
        
        System.out.println("\nUpdated");
    }
    
     
    //isAttendedOeCancelBooking
    public static int isAttendedOeCancelBooking(int bookingNo){
        int isAttendedOeCancelBooking = 0;
        
        List<ClassBookings> classBookings = ClassBookings.getClassBookings();
        for(ClassBookings obj : classBookings){
            if(obj.getBookingUniqNo() == bookingNo &&
                    (obj.getBookingStatus().equalsIgnoreCase(CLASS_ATTENDED) || obj.getBookingStatus().equalsIgnoreCase(CLASS_CANCEL))){
                isAttendedOeCancelBooking = 1;
                break;
            }
        }
        return isAttendedOeCancelBooking;
    }
    
     
    //Cancel Booking
    public static void cancelBooking(){
        
        int bookingNo = inputBookingNoFromLearner();
        
        //Already cancelled or attended
        int isAttendedOeCancelBooking = isAttendedOeCancelBooking(bookingNo);
        if(isAttendedOeCancelBooking == 1){
            System.out.println("\nAlready Attended Or Cancelled Selected Booking No");
            return;
        }
        
        
        List<ClassBookings> classBookings = ClassBookings.getClassBookings();
        int lessonNo = 0;
        for(ClassBookings obj : classBookings){
            if(obj.getBookingUniqNo() == bookingNo){
                obj.setBookingStatus(CLASS_CANCEL);
                lessonNo = obj.getLessonNo();
                break;
            }
        }
        
         //Update available seats 
        SwimmingLessons.updateAllowedLearnerSeats(lessonNo,-1);
        System.out.println("\nCancelled");
    }
    

    public static int inputBookingNoFromLearner(){
        Scanner input = new Scanner(System.in);

        System.out.print("\nPlease Input Booking No : ");
        String bookingNo = input.nextLine();
        
        if(bookingNo.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(bookingNo)){
            do{
                System.out.print("\nPlease Input Correct Booking No : ");
                bookingNo = input.nextLine();

            }while(bookingNo.equalsIgnoreCase("")  || !SwimmingBookingSystem.isDigit(bookingNo));
        }

        int isExistingBookingNo = isExistingBookingNo(Integer.parseInt(bookingNo));

        if(bookingNo.equalsIgnoreCase("") || isExistingBookingNo != 1 || !SwimmingBookingSystem.isDigit(bookingNo)){
            do{
                System.out.print("\nPlease Input Correct Booking No : ");
                bookingNo = input.nextLine();
               isExistingBookingNo = isExistingBookingNo(Integer.parseInt(bookingNo));

            }while(bookingNo.equalsIgnoreCase("") ||  isExistingBookingNo != 1 || !SwimmingBookingSystem.isDigit(bookingNo));
        }
                
        return Integer.parseInt(bookingNo);
         
    }
    
}
