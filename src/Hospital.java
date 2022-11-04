import java.util.ArrayList;
import java.util.List;

public abstract class Hospital {
    static Chief chief = new Chief();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static ArrayList<String> examinations = new ArrayList<>(List.of(
            "initial",
            "secondary",
            "consultation",
            "procedure"));
    static final ArrayList<String> specialties = new ArrayList<>(List.of(
            "Anesthesiology",
            "Internal diseases",
            "Gastroenterology",
            "Endocrinology",
            "Cardiology",
            "Dermatology",
            "Neurology",
            "Nephrology",
            "Psychiatry",
            "Rheumatology",
            "Gynecology",
            "Orthopedics",
            "Ophthalmology",
            "Urology",
            "Surgery"));




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

}
