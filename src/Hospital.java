import java.util.ArrayList;
import java.util.List;

public abstract class Hospital {

    static ArrayList<Appointment> appointments = new ArrayList<>();

    //Wsi4ko neobhodimo e v DB. Procheteno ot vhodnite failove
    //tezi zakomentiranite predlagam da gi mahnem
    //dostap do tqh imame prez masiva v Db >>
    String a = Db.specialities[1].name; // todo DELETE


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

    //------ APPS ----
    public void readAppointments() {  //
        for (Doctor d : Db.doctors) {
            for (Appointment ap: Db.appointments) {
                if (ap.patientID==d.id) d.doctorApts.add(ap); //
            }
        }

    }


    // ------------- DOCTOR ---------- TODO

    public void addDoctor() { }     // ------------- TODO

    public void removeDoctor() { }     // ------------- TODO

    public void addSpecialty() { }     // ------------- TODO

    public void removeSpecialty() { }     // ------------- TODO


}
