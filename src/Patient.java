import java.util.ArrayList;

public class Patient extends User {

    public static String keyWordForClassAllowedActions = "Patient"; //First word in arrayListUserRights.csv file
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public int id;


    public Patient(){
        //put data from file
    }

    public Patient(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "" + id + " " + firstName + " " + lastName + " " + age + " " + sex;
    }
}
