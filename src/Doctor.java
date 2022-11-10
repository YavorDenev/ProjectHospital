import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Doctor extends User {

    public int id;
    public boolean isHidden;
    public String speciality;
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public static String keyWordForClassAllowedActions = "Doctor"; //First word in arrayListUserRights.csv file

    public Doctor() {}

    public Doctor(String firstName, String lastName, int age, String sex, String speciality) {
        super(firstName, lastName, age, sex);
        this.id = generateDoctorId();
        this.isHidden = false;
        this.speciality = speciality;
    }

    private int generateDoctorId() {
        int id = 0;
        for (Doctor d: DBase.doctors) {
            if (id < d.id) id = d.id;
        }
        return id + 1;
    }

    public void showDocAppointments() {
        showDocAppointments(this.id);
    }

    public static void showDocAppointments(int docId) {
        for (Appointment app: DBase.appointments) {
            if (app.doctorID==docId) System.out.println(app);
        }
    }

    public void showDocApptsByPatientNames(String upDown) {
        showDocApptsByPatientNames(this.id, upDown);
    }

    public static void showDocApptsByPatientNames(int docId, String upDown) {
        ArrayList<Appointment> docAppts = new ArrayList<>();
        for (Appointment app: DBase.appointments) {
            if (app.doctorID==docId) docAppts.add(app);
        }
        if (!docAppts.isEmpty()) {
            if (upDown.equalsIgnoreCase("up")){
                System.out.println("------------- appointments up by patient names -----------------");
                docAppts.sort(Comparator.comparing(Appointment::getPatientNames));
            } else if (upDown.equalsIgnoreCase("down")) {
                System.out.println("------------- appointments down by patient names -----------------");
                docAppts.sort(Comparator.comparing(Appointment::getPatientNames).reversed());
            }
            for (Appointment app: docAppts) { System.out.println(app.getPatientNames() + " - appointment " + app); }
        } else System.out.println("The doctor doesn't have any appointments.");
    }

    public void showDocApptsByDateTime(String upDown) {
        showDocApptsByDateTime(this.id, upDown);
    }

    public static void showDocApptsByDateTime(int docId, String upDown) {
        ArrayList<Appointment> docAppts = new ArrayList<>();
        for (Appointment app: DBase.appointments) {
            if (app.doctorID==docId) docAppts.add(app);
        }
        if (!docAppts.isEmpty()) {
            if (upDown.equalsIgnoreCase("up")){
                System.out.println("------------- appointments up by date and time -----------------");
                docAppts.sort(Comparator.comparing(Appointment::getDateTimeComparingKey));
            } else if (upDown.equalsIgnoreCase("down")) {
                System.out.println("------------- appointments down by date and time -----------------");
                docAppts.sort(Comparator.comparing(Appointment::getDateTimeComparingKey).reversed());
            }
            for (Appointment app: docAppts) { System.out.println(app); }
        } else System.out.println("The doctor doesn't have any appointments.");
    }

    public void showDocApptsByPatientId(String upDown) {
        showDocApptsByPatientId(this.id, upDown);
    }

    public static void showDocApptsByPatientId(int docId, String upDown) {
        ArrayList<Appointment> docAppts = new ArrayList<>();
        for (Appointment app: DBase.appointments) {
            if (app.doctorID==docId) docAppts.add(app);
        }
        if (!docAppts.isEmpty()) {
            if (upDown.equalsIgnoreCase("up")){
                System.out.println("------------- appointments up by patient ID -----------------");
                docAppts.sort(Comparator.comparing(Appointment::getPatientID));
            } else if (upDown.equalsIgnoreCase("down")) {
                System.out.println("------------- appointments down by patient ID -----------------");
                docAppts.sort(Comparator.comparing(Appointment::getPatientID).reversed());
            }
            for (Appointment app: docAppts) { System.out.println(app); }
        } else System.out.println("The doctor doesn't have any appointments.");
    }

    public void removeDocAppointment() {
        System.out.println("Enter appointment Id to remove ...");
        int idToRemove = CheckInputData.inputPositiveInteger();
        int index = getDocAppointmentIndex(idToRemove);
        if (index >= 0) {
            DBase.appointments.remove(index);
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private int getDocAppointmentIndex(int idToRemove) {
        for (Appointment app: DBase.appointments) {
            if (app.id==idToRemove && app.doctorID==this.id) {
                return DBase.appointments.indexOf(app);
            }
        }
        return -1;
    }

    public void showMyPatients() {
        boolean f = true;
        for (Patient p : DBase.patients) {
            for (Appointment app: DBase.appointments) {
                if (app.doctorID==this.id && app.patientID==p.id) {
                    System.out.println(p);
                    f = false;
                    break;
                }
            }
        }
        if (f) System.out.println("The doctor doesn't have any patients.");
    }

    @Override
    public String toString() {
        return "id:" + id +
                " " + firstName +
                " " + lastName +
                " - " + speciality;
    }

}
