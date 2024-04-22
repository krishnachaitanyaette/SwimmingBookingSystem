
package swimmingbookingsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class LessonReviews {
    
      
    private int bookingNo;
    private int rating;
    private String lessonReview;
    private String attendedOn;
    
    public static List <LessonReviews> lessonReviews = new ArrayList<>();

    public LessonReviews(int bookingNo, int rating, String lessonReview, String attendedOn) {
        this.bookingNo = bookingNo;
        this.rating = rating;
        this.lessonReview = lessonReview;
        this.attendedOn = attendedOn;
    }

    public int getBookingNo() {
        return bookingNo;
    }

    public int getRating() {
        return rating;
    }

    public String getLessonReview() {
        return lessonReview;
    }

    public String getAttendedOn() {
        return attendedOn;
    }

    public static List<LessonReviews> getLessonReviews() {
        return lessonReviews;
    }

      
   //Display Reviews
    public static void displayReviews(){
        
        List<LessonReviews> lessonReviews = LessonReviews.getLessonReviews();
        List<ClassBookings> classBookings = ClassBookings.getClassBookings();
        List<RegisteredLearners> registeredLearners = RegisteredLearners.getRegisteredLearners();
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
        
        if(lessonReviews.size() < 1){
            System.out.println("\nNo Reviews Found"); 
            return;
        }
        
        System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-13s | %-40s | %-18s | %-12s | %-10s | %-30s |  \n",
                "BookingNo","LessonNo", "Lesson", "ReviewBy","ReviewTo","Rating","Review" );
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        Set<Integer> uniqueRecords = new HashSet<>(); 

        for(LessonReviews lessonReviewsObj : lessonReviews){
            for(ClassBookings obj : classBookings){
                
                if(lessonReviewsObj.getBookingNo() == obj.getBookingUniqNo()){
                    
                    //Lesson Details
                    String lesson = "";
                    String coachName = "";
                    for(SwimmingLessons lessonDataObj : swimmingLessons){
                        if(obj.getLessonNo() == lessonDataObj.getLessonNo()){
                            lesson = lessonDataObj.getLesson();
                            
                            SwimmingCoaches coach = SwimmingCoaches.getCoachObject(lessonReviewsObj.getBookingNo());
                            coachName = coach.getName();
                            break;
                        }
                    }
                    
                    //Learner Details
                    String learnername = "";
                    for(RegisteredLearners registeredLearnersObj : registeredLearners){
                        if(registeredLearnersObj.getRegisteredID() == obj.getRegisteredID()){
                            learnername = registeredLearnersObj.getName();
                            break;
                        }
                    }

                    //Display
                    if (!uniqueRecords.contains(lessonReviewsObj.getBookingNo())){
                        uniqueRecords.add(lessonReviewsObj.getBookingNo());     

                        System.out.printf("| %-12s | %-13s | %-40s | %-18s | %-12s | %-10s | %-30s |  \n",
                        obj.getBookingUniqNo(),obj.getLessonNo(), lesson, learnername,coachName,lessonReviewsObj.getRating(),
                        lessonReviewsObj.getLessonReview());
                    }
                }
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
    
    //inputRatingAndReview
    public static void addReviewForCoach(){
        
        Scanner input = new Scanner(System.in);

        System.out.print("\nPlease Input Booking No : ");
        String bookingNo = input.nextLine();
        
        int isExistingBookingNo = ClassBookings.isExistingBookingNo(Integer.parseInt(bookingNo));

         if(bookingNo.equalsIgnoreCase("") || isExistingBookingNo != 1 || !SwimmingBookingSystem.isDigit(bookingNo)){
            do{
                System.out.print("\nPlease Input Valid Booking No : ");
                bookingNo = input.nextLine();
               isExistingBookingNo = ClassBookings.isExistingBookingNo(Integer.parseInt(bookingNo));

            }while(bookingNo.equalsIgnoreCase("") || isExistingBookingNo != 1 || !SwimmingBookingSystem.isDigit(bookingNo));
        }
               
        //Already cancelled or attended
        int isAttendedOeCancelBooking = ClassBookings.isAttendedOeCancelBooking(Integer.parseInt(bookingNo));
        if(isAttendedOeCancelBooking == 1){
            System.out.println("\nAlready Attended Or Cancelled Selected Booking No");
            return;
        }

        SwimmingCoaches coachData = SwimmingCoaches.getCoachObject(Integer.parseInt(bookingNo));

        System.out.print("\nPlease Input Review for Coach "+coachData.getName()+" : ");
        String review = input.nextLine();

        if(review.equalsIgnoreCase("")){
            do{
                System.out.print("\nPlease Input Review for Coach "+coachData.getName()+" : ");
                review = input.nextLine();
            }while(review.equalsIgnoreCase(""));
        }

        reviewAccordingToRating();
        
        System.out.print("\nPlease Input Rating from above of the values : ");
        String rating = input.nextLine();
         
        if(rating.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(rating) || 
                Integer.parseInt(rating) < RegisteredLearners.MIN_GRADE_VAL || Integer.parseInt(rating) > RegisteredLearners.MAX_GRADE_VAL){
            do{
                System.out.print("\nPlease Input Rating from above of the values : ");
                rating = input.nextLine();
            }while(rating.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(rating) || 
                    Integer.parseInt(rating) < RegisteredLearners.MIN_GRADE_VAL || Integer.parseInt(rating) > RegisteredLearners.MAX_GRADE_VAL);
        }
           
        //Add review Object

        LocalDateTime currentDateTime = LocalDateTime.now();
        
        LessonReviews reviewobj = new LessonReviews(Integer.parseInt(bookingNo),Integer.parseInt(rating),review
                ,String.valueOf(currentDateTime));
        LessonReviews.lessonReviews.add(reviewobj);
        
        //Update booking status
        List<ClassBookings> classBookings = ClassBookings.getClassBookings();
        int lessonNo = 0;
        for(ClassBookings obj : classBookings){
            if(obj.getBookingUniqNo() == Integer.parseInt(bookingNo)){
                lessonNo = obj.getLessonNo();
                obj.setBookingStatus(ClassBookings.CLASS_ATTENDED);
                break;
            }
        }
        
        //Update grade level of registered learners -  if higher class attended
        RegisteredLearners.updateGrade(lessonNo);
        
        //Update seats of lessons for other learbers to book
        SwimmingLessons.updateAllowedLearnerSeats(lessonNo,-1);
        
        System.out.println("\nAdded Review");
    }
    
    
    //reviewAccordingToRating
    private static void reviewAccordingToRating(){
            
        System.out.println("\n\n--------------------------------------------");
        System.out.printf("| %-15s | %-22s |\n","Rating","Review");
        System.out.println("------------------------------------------");
        System.out.printf("| %-15s | %-22s |\n","1","Very Dissatisfied");
        System.out.printf("| %-15s | %-22s |\n","2","Dissatisfied");
        System.out.printf("| %-15s | %-22s |\n","3","OK");
        System.out.printf("| %-15s | %-22s |\n","4","Satisfied");
        System.out.printf("| %-15s | %-22s |\n","5","Very Satisfied");
        System.out.println("------------------------------------------------");
        
    }
  
    
    
}
