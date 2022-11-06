import java.util.ArrayList;
import java.util.List;

public abstract class Hospital {

    static ArrayList<Appointment> appointments = new ArrayList<>();

    //Wsi4ko neobhodimo e v DB. Procheteno ot vhodnite failove
    //tezi zakomentiranite predlagam da gi mahnem
    //dostap do tqh imame prez masiva v Db >>
    String a = Db.specialities[1].name; // todo DELETE


// --------------------------------------------------------------------------
// ---------------------common-------------------------------------------------

    public static void showDoctors() {
        //  ---------------------------------------------------- TODO
    }

    public static void showPatients() {
        //  ---------------------------------------------------- TODO
    }

    public static void showPatientsByDoctor(int docId) {
        //  ---------------------------------------------------- TODO
    }

    public static void showPatientsBySpecialty(String specialty) {
        //  ---------------------------------------------------- TODO
    }

    public static void showPatientsByDate(String date) {
        // ---------------------------------------------------- TODO
    }

    //---------------------------------------------------------------------------
    // ------------- DOCTOR ---------- TODO

    public void readAppointments() {  //
        for (Doctor d : Db.doctors) {
            for (Appointment ap: Db.appointments) {
                if (ap.patientID==d.id) d.doctorApts.add(ap); //
            }
        }

    }


    //---------------------------------------------------------------------------
// ---------------------BOSS-------------------------------------------------

    public void addDoctor() { }     // ------------- TODO

    public void removeDoctor() { }     // ------------- TODO

    public void addSpecialty() { }     // ------------- TODO

    public void removeSpecialty() { }     // ------------- TODO


    //---------------------------------------------------------------------------
    // ------------- PATIENT ---------- TODO

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
    public void changeAppointmentsDateTime() {
        System.out.println("Enter appointment Id to change ...");
        int idToChange = Inputs.inputPositiveInteger();
        int index = getMyAppointmentIndex(idToChange);
        if (index >= 0) {
            int doctorId = Hospital.appointments.get(index).getDoctorId();
            String date = chooseNewDate(doctorId);
            String time = chooseNewTime(doctorId);
            String time = chooseNewTime(doctorId, date);
            Hospital.appointments.get(index).setDate(date);
            Hospital.appointments.get(index).setTime(time);
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private String chooseNewDate(int doctorId) { return "dddd"; }      // ------------- TODO

    private String chooseNewTime(int doctorId) { return "tttt"; }      // ------------- TODO
    private String chooseNewTime(int doctorId, String date) { return "tttt"; }      // ------------- TODO

    public void AddAppointment() { }     // ------------- TODO

    public void showDocAppointments(int docId) { }     // ------------- TODO
    public void showDocAppointments(int docId, String date) { }     // ------------- TODO

    private boolean checkIfDateTimeIsFreeByDoc(String date, String time) { return false; }      // ------------- TODO

    private boolean checkIfDateTimeIsFreeByMe(String date, String time) { return false; }      // ------------- TODO

}
