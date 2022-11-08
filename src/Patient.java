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

    public void showMyAppointments() {
        showAppointmentsByPatientId(this.id);
    }

    public static void showAppointmentsByPatientId(int patientId) {
        for (Appointment app: DBase.appointments) {
            if (app.patientID==patientId) System.out.println(app);
        }
    }

    public void removeMyAppointment() {
        System.out.println("Enter appointment Id to remove ...");
        int idToRemove = CheckInputData.inputPositiveInteger();
        int index = getMyAppointmentIndex(idToRemove);
        if (index >= 0) {
            DBase.appointments.remove(index);
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private int getMyAppointmentIndex(int idToRemove) {
        for (Appointment app: DBase.appointments) {
            if (app.id==idToRemove && app.patientID==this.id) {
                return DBase.appointments.indexOf(app);
            }
        }
        return -1;
    }
    public void changeAppointmentsDateTime(int apptIdToChange, String date, int time) {
        int index = getMyAppointmentIndex(apptIdToChange);
        if (index >= 0) {
            if (checkIfDateTimeIsFreeByMe(date, time)) {
                DBase.appointments.get(index).setDate(date);
                DBase.appointments.get(index).setTime(time);
            } else System.out.println("You have another appointment booked at the same time.");
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private boolean checkIfDateTimeIsFreeByMe(String date, int time) {
        for (Appointment app: DBase.appointments) {
            if (app.patientID==this.id && app.date.equals(date) && app.time==time) return false;
        }
        return true;
    }

    public void AddAppointment(int docId, String typeOfExam, String date, int time) {
        DBase.appointments.add(new Appointment(this.id, docId, typeOfExam, date, time));
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", firstName='" + firstName +
                ", lastName='" + lastName +
                ", age=" + age +
                ", sex='" + sex;
    }

}
