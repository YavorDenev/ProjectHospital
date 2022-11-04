import java.util.ArrayList;

public class Patient extends User {
    static int rights = 3;
    private int patientId;
    private int age;
    private ArrayList<Appointment> patientApts = new ArrayList<>();

    public int getId() {
        return patientId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public ArrayList<Appointment> getPatientApts() {
        return patientApts;
    }

    public void setPatientApts(ArrayList<Appointment> patientApts) {
        this.patientApts = patientApts;
    }

    public Patient(int id, String firstName, String lastName, int age) {
        this.patientId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }



    public void showMyAppointments() { }       // ------------- TODO

    public void removeAppointment(int id) { }     // ------------- TODO

    public void changeAppointmentsDateTime(int aptId) { }     // ------------- TODO

    public void AddAppointment() { }     // ------------- TODO

    public void showDocAppointments(int docId) { }     // ------------- TODO

    private String chooseNewDate() { return ""; }      // ------------- TODO

    private String chooseNewTime() { return ""; }      // ------------- TODO

    private boolean checkIfDateTimeIsFreeByDoc(String date, String time) { return false; }      // ------------- TODO

    private boolean checkIfDateTimeIsFreeByMe(String date, String time) { return false; }      // ------------- TODO

    public void readAppointments() {
        for (Appointment ap: Hospital.appointments) {
            if (ap.getPatientId()==this.patientId) patientApts.add(ap);
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
                "Id=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
