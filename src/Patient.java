import java.util.ArrayList;
import java.util.Scanner;

public class Patient extends User {

    static ArrayList<Integer> rights = new ArrayList<>();
    public static String keyWordForClassRights = "Patient"; //First word in arrayListUserRights.csv file

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

    public void showMyAppointments() {
        for (Appointment app: Hospital.appointments) {
            if (app.getPatientId()==this.patientId) {
                System.out.println(app);
            }
        }
    }

    public void removeAppointment() {
        System.out.println("Enter appointment Id to remove ...");
        int idToRemove = Inputs.inputPositiveInteger();
        int index = getMyAppointmentIndex(idToRemove);
        if (index >= 0) {
            Hospital.appointments.remove(index);
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private int getMyAppointmentIndex(int id) {
        for (Appointment app: Hospital.appointments) {
            if (app.getId()==id && app.getPatientId()==this.patientId) {
                return Hospital.appointments.indexOf(app);
            }
        }
        return -1;
    }

    public void changeAppointmentsDateTime(int id) {
        System.out.println("Enter appointment Id to change ...");
        int idToChange = Inputs.inputPositiveInteger();
        int index = getMyAppointmentIndex(idToChange);
        if (index >= 0) {
            String date = chooseNewDate();
            String time = chooseNewTime();
            Hospital.appointments.get(index).setDate(date);
            Hospital.appointments.get(index).setTime(time);
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private String chooseNewDate() { return "dddd"; }      // ------------- TODO

    private String chooseNewTime() { return "tttt"; }      // ------------- TODO

    public void AddAppointment() { }     // ------------- TODO

    public void showDocAppointments(int docId) { }     // ------------- TODO

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
