import java.util.ArrayList;

public class Doctor extends User {

    public int id;
    public boolean isHidden;
    public String speciality;
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public static String keyWordForClassAllowedActions = "Doctor"; //First word in arrayListUserRights.csv file

    public ArrayList<Appointment> doctorApts = new ArrayList<>();

    public void readAppointments() {
        for (Appointment ap: Hospital.appointments) {
            if (ap.patientID==this.id) doctorApts.add(ap);
        }
    }

    @Override
    public String toString() {
        return "" + id + " " + firstName + " " + lastName + " " + speciality + " " + isHidden;
    }
}
