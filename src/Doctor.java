import java.util.ArrayList;
import java.util.Calendar;

public class Doctor extends User {

    static ArrayList<Integer> rights = new ArrayList<>();
    public static String keyWordForClassRights = "Doctor"; //First word in arrayListUserRights.csv file
    public boolean isHidden;

    private int doctorId;
    private String specialty;

    private ArrayList<Appointment> doctorApts = new ArrayList<>();

    //private DocCalendar calendar = new DocCalendar();  ---------------- TODO


    // ------------------------------------- TODO


    public void readAppointments() {
        for (Appointment ap: Hospital.appointments) {
            if (ap.getPatientId()==this.doctorId) doctorApts.add(ap);
        }
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "Id=" + doctorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
