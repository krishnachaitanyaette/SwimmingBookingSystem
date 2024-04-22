
package swimmingbookingsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SwimmingCoaches {
    
      
    private int registeredID;
    private String name;
    private String gender;
    private String contact;
    
    
   public static List <SwimmingCoaches> swimmingCoaches = new ArrayList<>();

    public SwimmingCoaches(int registeredID, String name, String gender, String contact) {
        this.registeredID = registeredID;
        this.name = name;
        this.gender = gender;
        this.contact = contact;
    }

    public int getRegisteredID() {
        return registeredID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    public static List<SwimmingCoaches> getSwimmingCoaches() {
        regiteredCoaches();
        return swimmingCoaches;
    }

    //registered Coach
    public static void regiteredCoaches(){
        
        SwimmingCoaches coach1 = new SwimmingCoaches(1,"Jacky","Male","07551 538547");
        SwimmingCoaches coach2 = new SwimmingCoaches(2,"Theresa","Male","020 8845 9522");
        SwimmingCoaches coach3 = new SwimmingCoaches(3,"Dennis","Male","0117 966 8959");
        SwimmingCoaches coach4 = new SwimmingCoaches(4,"Roberts","Male","0131 665 2703");
        SwimmingCoaches coach5 = new SwimmingCoaches(5,"Karen Cooper","Male","015394 36288");
        SwimmingCoaches coach6 = new SwimmingCoaches(6,"Kyle Bell","Male","01708 341719");
        
        SwimmingCoaches.swimmingCoaches.add(coach1);
        SwimmingCoaches.swimmingCoaches.add(coach2);
        SwimmingCoaches.swimmingCoaches.add(coach3);
        SwimmingCoaches.swimmingCoaches.add(coach4);
        SwimmingCoaches.swimmingCoaches.add(coach5);
        SwimmingCoaches.swimmingCoaches.add(coach6);
        
    }
    
    
    //Display Coaches
    public static void displayCoaches(){
        System.out.println("\n\n-------------------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-15s | %-10s | \n",
                "RegisteredID","Name", "Contact", "Gender");
        
        System.out.println("-------------------------------------------------------------------");
        Set<Integer> uniqueRecords = new HashSet<>(); 
         List<SwimmingCoaches> coachData = SwimmingCoaches.getSwimmingCoaches();
        for(SwimmingCoaches obj : coachData){
            if (!uniqueRecords.contains(obj.getRegisteredID())){
                uniqueRecords.add(obj.getRegisteredID());     

                System.out.printf("| %-15s | %-15s | %-15s | %-10s | \n",
                obj.getRegisteredID(),obj.getName(), obj.getContact(),obj.getGender());
            }
        }
        System.out.println("-------------------------------------------------------------------");
    }
    
    
    
    // Coach existing or not
    public static int isExistingCoach(int registeredID){
        int isExisting = 0;
        
        List<SwimmingCoaches> coachData = SwimmingCoaches.getSwimmingCoaches();
        for(SwimmingCoaches obj : coachData){
            if(obj.getRegisteredID() == registeredID){
                isExisting = 1;
                break;
            }
        }
        return isExisting;
    }
    
    
    //Get Coach Object
    public static SwimmingCoaches getCoachObject(int bookingUniqNo){
        
        List<SwimmingCoaches> coachData = SwimmingCoaches.getSwimmingCoaches();
        List<ClassBookings> classBookings = ClassBookings.getClassBookings();
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
        
        int lessonNo = 0;
        for(ClassBookings obj : classBookings){
            if(obj.getBookingUniqNo() == bookingUniqNo){
                lessonNo = obj.getLessonNo();
                break;
            }
        }
        
         int coachRegID = 0;
         for(SwimmingLessons obj : swimmingLessons){
            if(obj.getLessonNo() == lessonNo){
                coachRegID = obj.getLessonBy();
                break;
            }
        }
         
        //Coach Obj
        SwimmingCoaches coachObj = null;
        for(SwimmingCoaches cObj : coachData){
            if(cObj.getRegisteredID() == coachRegID){
                coachObj = cObj;
                break;
            }
        }
        
        return coachObj;
    }


}
