import java.util.ArrayList;

public class Anonymous extends User{
    //fields
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public static String keyWordForClassAllowedActions = "Anonymous"; //First word in arrayListUserRights.csv file

    Anonymous(){
        this.firstName = "Anonymous";
    }
}
