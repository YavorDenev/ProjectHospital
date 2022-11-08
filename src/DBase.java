import java.util.ArrayList;
import java.util.List;

public abstract class DBase {

    public static User currentUser;

    public static List<Boss> bosses = new ArrayList<>();

    public static List<Doctor> doctors = new ArrayList<>();

    public static List<Patient> patients = new ArrayList<>();

    public static List<Specialities> specialities = new ArrayList<>();

    public static List<Appointment> appointments = new ArrayList<>();

    public static String[] allowedActions = new String[21];

    static String[] examinations = new String[] {
            constAppointments.initial,
            constAppointments.consultation,
            constAppointments.secondary,
            constAppointments.procedure
    };

    public static void initializeAllowedActions(){
        allowedActions[0] = "logout"; //boss, doctor, patient
        allowedActions[1] = "login as patient"; //anonymous
        allowedActions[2] = "login as doctor"; //anonymous
        allowedActions[3] = "login as boss"; //anonymous
        allowedActions[4] = "show list of doctors"; //boss, doctor, patient, anonymous ==========> Hospital.showDoctors()
        allowedActions[5] = "show list of patients"; //boss, doctor =============================> Hospital.showPatients()
        // липсва:  "show Patients By Doctor"; //boss, doctor  <-------- ИСКА СЕ ПО УСЛОВИЕ =====> Hospital.showPatientsByDocNames()
        // липсва:  "show Patients By Speciality"; //boss, doctor  <---- ИСКА СЕ ПО УСЛОВИЕ
        // липсва:  "show Patients By Date"; //boss, doctor   <--------- ИСКА СЕ ПО УСЛОВИЕ
        allowedActions[6] = "show doctor calendar by doctor_id"; //boss, doctor  ===============> Doctor.showDocAppointments(docId)
        allowedActions[7] = "show my calendar"; //doctor  ======================================> doctors.get(index).showDocAppointments()
        allowedActions[8] = "show my appointments"; //patient ==================================> patients.get(index).showMyAppointments()
        allowedActions[9] = "show patient appointments by patient_id"; //boss, doctor
        allowedActions[10] = "show sorted calendar on doctor_id by time"; //boss, doctor  > Up; Down
        allowedActions[11] = "show sorted calendar on doctor_id by patient_name"; //boss, doctor > Up; Down
        allowedActions[12] = "show sorted calendar on doctor_id by patient_id"; //boss, doctor > Up; Down
        allowedActions[13] = "show sorted calendar by doctor_firstName"; //boss, doctor > Down
        allowedActions[14] = "show sorted calendar by speciality"; //boss, doctor > Down
        allowedActions[15] = "show sorted calendar by datetime"; //boss, doctor > Down
        allowedActions[16] = "change appointment by app_id"; //patient
        // липсва:  "add new appointment"; //patient   <-------------------------- НЕ СЕ ИСКА ПО УСЛОВИЕ
        allowedActions[17] = "reject appointment by app_id"; //boss, doctor, patient
        // номер 17 ще реализирам с 3 различни метода, защото
        // Пациент и Доктор могат да трият само своите часове, а Шефът - всички
        allowedActions[18] = "make doctor inactive (hidden)"; //boss    <---------- НЕ СЕ ИСКА ПО УСЛОВИЕ
        allowedActions[19] = "add new doctor"; //boss     <--------------------- НЕ СЕ ИСКА ПО УСЛОВИЕ
        // липсва:  "add speciality"; //boss       <------------------------ НЕ СЕ ИСКА ПО УСЛОВИЕ
        // липсва:  "remove speciality"; //boss       <-------------------- НЕ СЕ ИСКА ПО УСЛОВИЕ
        allowedActions[20] = "change users allowed actions"; //boss

    }

    static class constSex{
        static final String male = "male";
        static final String female = "female";
    }

    static class constAppointments{
        static final String initial = "initial";
        static final String secondary = "secondary";
        static final String consultation = "consultation";
        static final String procedure = "procedure";
    }

}
