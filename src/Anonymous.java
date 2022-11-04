import java.util.ArrayList;

public class Anonymous extends User{
    //fields
    static ArrayList<Integer> rights = new ArrayList<>();
    public static String keyWordForClassRights = "Anonymous"; //First word in arrayListUserRights.csv file

    Anonymous(){
        this.firstName = "Anonymous";
    }
}
