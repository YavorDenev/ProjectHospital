import java.util.ArrayList;

public class Patient extends User {

    public static String keyWordForClassAllowedActions = "Patient"; //First word in arrayListUserRights.csv file
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public int id;

    private ArrayList<Appointment> patientApts = new ArrayList<>();
    public ArrayList<Appointment> getPatientApts() {
        return patientApts;
    }

    public Patient(){
        //put data from file
    }

    public Patient(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    //Ostavqm gi, no bih iskal az da gi napravq direktno izvan klasa, poneje sa tqsno obvarzani s MENU i ReadWrite

    public void setPatientApts(ArrayList<Appointment> patientApts) {
        this.patientApts = patientApts;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "Id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
