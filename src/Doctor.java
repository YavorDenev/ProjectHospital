import java.util.ArrayList;

public class Doctor extends User {

    public int id;
    public boolean isHidden;
    public String speciality;
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public static String keyWordForClassAllowedActions = "Doctor"; //First word in arrayListUserRights.csv file

    public ArrayList<Appointment> doctorApts = new ArrayList<>();

    @Override
    public String toString() {
        return "Doctor{" +
                "Id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + speciality + '\'' +
                '}';
    }
}
