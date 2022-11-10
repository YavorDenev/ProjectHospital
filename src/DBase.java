import java.util.ArrayList;
import java.util.List;

public abstract class DBase {

    public static User currentUser;

    public static List<Boss> bosses = new ArrayList<>();

    public static List<Doctor> doctors = new ArrayList<>();

    public static List<Patient> patients = new ArrayList<>();

    public static List<Speciality> specialities = new ArrayList<>();

    public static List<Appointment> appointments = new ArrayList<>();

    public static String[] allowedActions = new String[21];

    static String[] examinations = new String[] {
            constAppointments.initial,
            constAppointments.consultation,
            constAppointments.secondary,
            constAppointments.procedure
    };

    public static void initializeAllowedActions(){
        allowedActions[0] = "logout"; //boss, doctor, patient  ----------> MARTIN - TODO
        allowedActions[1] = "login as patient"; //anonymous  ------------> MARTIN - TODO
        allowedActions[2] = "login as doctor"; //anonymous  -------------> MARTIN - TODO
        allowedActions[3] = "login as boss"; //anonymous  ---------------> MARTIN - TODO
        allowedActions[4] = "show list of doctors"; //boss, doctor, patient, anonymous ============> Hospital.showDoctors()
        allowedActions[5] = "show list of patients"; //boss, doctor ===============================> Hospital.showPatients()
        allowedActions[6] = "show doctor calendar by doctor_id"; //boss, doctor  ==================> Doctor.showDocAppointments(docId)
        allowedActions[7] = "show my calendar"; //doctor  =========================================> doctors.get(index).showDocAppointments()
        allowedActions[8] = "show my appointments"; //patient =====================================> patients.get(index).showMyAppointments()
        allowedActions[9] = "show patient appointments by patient_id"; //boss, doctor =============> Patient.showAppointmentsByPatientId(patientId)
        allowedActions[10] = "show sorted calendar on doctor_id by time"; //boss, doctor > Up/Down ============> Doctor.showDocApptsByDateTime(docId, Up/Down);
                                                                                                      // ======> doctors.get(index).showDocApptsByDateTime(Up/Down)
        allowedActions[11] = "show sorted calendar on doctor_id by patient_name"; //boss, doctor > Up/Down ====> Doctor.showDocApptsByPatientNames(docId, Up/Down)
                                                                                                        // ====> doctors.get(index).showDocApptsByPatientNames(Up/Down)
        allowedActions[12] = "show sorted calendar on doctor_id by patient_id"; //boss, doctor > Up/Down ======> Doctor.showDocApptsByPatientId(docId, Up/Down)
                                                                                                      // ======> doctors.get(index).showDocApptsByPatientId(Up/Down)
        allowedActions[13] = "show sorted calendar by doctor_firstName"; //boss, doctor > Down  <---------- НЕ СЕ ИСКА, НЕ ГО ПРАВЯ
        allowedActions[14] = "show sorted calendar by speciality"; //boss, doctor > Down  <---------------- НЕ СЕ ИСКА, НЕ ГО ПРАВЯ
        allowedActions[15] = "show sorted calendar by datetime"; //boss, doctor > Down  <------------------ НЕ СЕ ИСКА, НЕ ГО ПРАВЯ
        // ----- ВМЕСТО ГОРНИТЕ ТРИ МЕТОДА ДОБАВЯМ: ------
        // "show patients by doctor names"; //boss, doctor <--- ИСКА СЕ ПО УСЛОВИЕ ====> Hospital.showPatientsByDocNames(firstName, lastName)
        // "show patients by speciality"; //boss, doctor <----- ИСКА СЕ ПО УСЛОВИЕ ====> Hospital.showPatientsBySpeciality(speciality)
        // "show patients by date"; //boss, doctor  <---------- ИСКА СЕ ПО УСЛОВИЕ ====> Hospital.showPatientsByDate(date)
        allowedActions[16] = "change appointment by app_id"; //patient ================> patients.get(index).changeAppointmentsDateTime()
        // липсва:  "add new appointment"; //patient   <---- НЕ СЕ ИСКА ПО УСЛОВИЕ ====> patients.get(index).AddAppointment(docId, typeOfExam, date, time)
        allowedActions[17] = "reject appointment by app_id"; // patient ===============> patients.get(index).removeMyAppointment()
                             // doctor  <------- НЕ СЕ ИСКА ПО УСЛОВИЕ ================> doctors.get(index).removeDocAppointment()
                             // boss  <------- НЕ СЕ ИСКА ПО УСЛОВИЕ ==================> bosses.get(index).removeAppointment()
        // ----- ЗАБЕЛЕЖКА: No 17 е реализиран с 3 различни метода, защото Пациент и Доктор могат да трият само свои часове, а Шефът - всички
        allowedActions[18] = "make doctor inactive (hidden)"; //boss  <---- НЕ СЕ ИСКА ПО УСЛОВИЕ =======> TODO - ако има време
        allowedActions[19] = "add new doctor"; //boss  <------------------- НЕ СЕ ИСКА ПО УСЛОВИЕ =======> TODO - ако има време
        // липсва:  "add speciality"; //boss  <---------------------------- НЕ СЕ ИСКА ПО УСЛОВИЕ =======> TODO - ако има време
        // липсва:  "make speciality inactive (hidden)"; //boss   <-------- НЕ СЕ ИСКА ПО УСЛОВИЕ =======> TODO - ако има време
        allowedActions[20] = "change users allowed actions"; //boss  -------> MARTIN - TODO
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
