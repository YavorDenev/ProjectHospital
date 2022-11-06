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

/* -------------- това е в Доктор, тук е безмислено -----------
    public void readAppointments() {
        for (Doctor d : Db.doctors) {
            for (Appointment ap: Db.appointments) {
                if (ap.patientID==d.id) d.doctorApts.add(ap);
            }
        }
    }
 */

    //---------------------------------------------------------------------------
// ---------------------BOSS-------------------------------------------------

    public void addDoctor() { }     // ------------- TODO

    public void removeDoctor() { }     // ------------- TODO

    public void addSpecialty() { }     // ------------- TODO

    public void removeSpecialty() { }     // ------------- TODO


    //---------------------------------------------------------------------------
    // ------------- PATIENT ---------- TODO

    public void showPatientAppointments(int patientId) {
            // така кода става чуплив - ако подадеш грешно ИД, програмата ще крашне,
            // затова мястото му е в Пациент - той без класа Пациент няма никакъв смисъл
            // тука стои като пришит с бели конци - ТВЪРДО ДЪРЖА НА ТОВА !!!
        for (Appointment app: Hospital.appointments) {
            if (app.patientID==patientId) {
                System.out.println(app);
            }
        }
    }

    public void removePatientAppointment(int patientId) {
                // същото като за горния метод - излишно вкарване на параметъра patientId
                // което може да счупи кода - ТУК НЯМА МЯСТО !!!
        System.out.println("Enter appointment Id to remove ...");
        int idToRemove = CheckInputData.inputPositiveInteger();
        int index = getMyAppointmentIndex(idToRemove, patientId);
        if (index >= 0) {
            Hospital.appointments.remove(index);
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private int getMyAppointmentIndex(int idToRemove, int patientId) {
                // същото като за горния метод - излишно вкарване на параметъра patientId
        for (Appointment app: Hospital.appointments) {
            if (app.id==idToRemove && app.patientID==patientId) {
                return Hospital.appointments.indexOf(app);
            }
        }
        return -1;
    }
    public void changeAppointmentsDateTime(int patientId) {
                // същото като за горния метод - излишно вкарване на параметъра patientId
        System.out.println("Enter appointment Id to change ...");
        int idToChange = CheckInputData.inputPositiveInteger();
        int index = getMyAppointmentIndex(idToChange, patientId);
        if (index >= 0) {
            int doctorId = Hospital.appointments.get(index).doctorID;
            String date = chooseNewDate(doctorId);
            int time = chooseNewTime(doctorId, date);
            Hospital.appointments.get(index).setDate(date);
            Hospital.appointments.get(index).setTime(time);
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private String chooseNewDate(int doctorId) { return "dddd"; }      // ------------- TODO

    private int chooseNewTime(int doctorId, String date) { return 0; }      // ------------- TODO

    public void AddAppointment() { }     // ------------- TODO

    public void showDocAppointments(int docId) { }     // ------------- TODO
    public void showDocAppointments(int docId, String date) { }     // ------------- TODO

    private boolean checkIfDateTimeIsFreeByDoc(String date, String time) { return false; }      // ------------- TODO

    private boolean checkIfDateTimeIsFreeByMe(String date, String time) { return false; }      // ------------- TODO

}
