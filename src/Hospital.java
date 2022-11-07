import java.util.ArrayList;
import java.util.List;

public abstract class Hospital {

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


    //---------------------------------------------------------------------------
// ---------------------BOSS-------------------------------------------------

    public void addDoctor() { }     // ------------- TODO

    public void removeDoctor() { }     // ------------- TODO

    public void addSpecialty() { }     // ------------- TODO

    public void removeSpecialty() { }     // ------------- TODO


    //---------------------------------------------------------------------------
    // ------------- PATIENT ---------- TODO


}
