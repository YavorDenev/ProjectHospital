import java.util.ArrayList;
import java.util.Calendar;

public class Doctor extends User {
    static int rights = 2;
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
