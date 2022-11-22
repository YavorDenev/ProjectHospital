import java.lang.reflect.Type;
import java.util.*;

public abstract class DBase {

    public static User currentUser;

    public static Map<Integer, String> patientsMap = new HashMap<Integer, String>();
    public static Map<Integer, Doctor> doctorsMap = new HashMap<Integer, Doctor>();

    public static List<Boss> bosses = new ArrayList<>();

    public static List<Doctor> doctors = new ArrayList<>();

    public static List<Patient> patients = new ArrayList<>();

    public static List<Speciality> specialities = new ArrayList<>();

    public static List<Appointment> appointments = new ArrayList<>();

    public static String[] allowedActions = new String[22];

    public static int maxDoctorID = 0;
    public static ArrayList<String> activeDays = new ArrayList<>();

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
        // липсва:  "show list of specialities"; //boss, doctor, patient, anonymous <---- НЕ СЕ ИСКА ПО УСЛОВИЕ =====> Hospital.showSpecialities();
        allowedActions[6] = "show doctor calendar by doctor_id"; //boss, doctor  ==================> Doctor.showSortedDocApptsByCriteria(docId, Up/Down, SortCriteria.DATE_TIME);
        allowedActions[7] = "show my calendar"; //doctor  =========================================> Doctor.showSortedDocApptsByCriteria(Up/Down, SortCriteria.DATE_TIME);
        allowedActions[8] = "show my appointments"; //patient =====================================> patients.get(index).showMyAppointments()
        allowedActions[9] = "show patient appointments by patient_id"; //boss, doctor =============> Patient.showAppointmentsByPatientId(patientId)
        allowedActions[10] = "show sorted calendar on doctor_id by time"; //boss, doctor > Up/Down ============> Doctor.showSortedDocApptsByCriteria(docId, Up/Down, SortCriteria.DATE_TIME);
                                                                                                      // ======> Doctor.showSortedDocApptsByCriteria(Up/Down, SortCriteria.DATE_TIME);
        allowedActions[11] = "show sorted calendar on doctor_id by patient_name"; //boss, doctor > Up/Down ====> Doctor.showSortedDocApptsByCriteria(docId, Up/Down, SortCriteria.PATIENT_NAMES);
                                                                                                        // ====> Doctor.showSortedDocApptsByCriteria(Up/Down, SortCriteria.PATIENT_NAMES);
        allowedActions[12] = "show sorted calendar on doctor_id by patient_id"; //boss, doctor > Up/Down ======> Doctor.showSortedDocApptsByCriteria(docId, Up/Down, SortCriteria.PATIENT_ID);
                                                                                                      // ======> Doctor.showSortedDocApptsByCriteria(Up/Down, SortCriteria.PATIENT_ID);
        allowedActions[13] = "show patients by doctor names"; //boss, doctor > Down
        allowedActions[14] = "show patients by speciality"; //boss, doctor > Down
        allowedActions[15] = "show patients by date"; //boss, doctor > Down
        // "show patients by doctor names"; //boss, doctor <--- ИСКА СЕ ПО УСЛОВИЕ ====> Hospital.showPatientsByDocNames(firstName, lastName)
        // "show patients by speciality"; //boss, doctor <----- ИСКА СЕ ПО УСЛОВИЕ ====> Hospital.showPatientsBySpeciality(speciality)
        // "show patients by date"; //boss, doctor  <---------- ИСКА СЕ ПО УСЛОВИЕ ====> Hospital.showPatientsByDate(date)
        allowedActions[16] = "change appointment by app_id"; //patient ================> patients.get(index).changeAppointmentsDateTime()
        // липсва:  "add new appointment"; //patient   <---- НЕ СЕ ИСКА ПО УСЛОВИЕ ====> patients.get(index).AddAppointment(docId, typeOfExam, date, time)
        allowedActions[17] = "reject appointment by app_id"; // patient ===============> patients.get(index).removeMyAppointment()
                             // doctor  <------- НЕ СЕ ИСКА ПО УСЛОВИЕ ================> doctors.get(index).removeDocAppointment()
                             // boss  <------- НЕ СЕ ИСКА ПО УСЛОВИЕ ==================> bosses.get(index).removeAppointment()
        // ----- ЗАБЕЛЕЖКА: No 17 е реализиран с 3 различни метода, защото Пациент и Доктор могат да трият само свои часове, а Шефът - всички
        allowedActions[18] = "change doctor visibility"; //boss  <---- НЕ СЕ ИСКА ПО УСЛОВИЕ =======> bosses.get(index).changeDoctorVisibility(int idToChangeVis)
        allowedActions[19] = "add new doctor"; //boss  <------------------- НЕ СЕ ИСКА ПО УСЛОВИЕ =======> bosses.get(index).addDoctor(firstName, lastName, age, sex, speciality)
        // липсва:  "add speciality"; //boss  <---------------------------- НЕ СЕ ИСКА ПО УСЛОВИЕ =======> bosses.get(index).addSpeciality(name)
        // липсва:  "change speciality visibility"; //boss   <-------- НЕ СЕ ИСКА ПО УСЛОВИЕ =======> bosses.get(index).changeSpecVisibility(int idToChangeVis)
        allowedActions[20] = "change users allowed actions"; //boss  -------> MARTIN - TODO
        allowedActions[21] = "EXIT"; //boss, doctor, patient, anonymous  ----------> MARTIN - TODO
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

    public static void setActiveDays(){
        Set<String> setData = new HashSet(); //let use just dates in appointments

        for (Appointment app : DBase.appointments){
            if (!setData.contains(app.date)) setData.add(app.date);
        }

        Object[] arrData = setData.toArray();
        arrData = reverseData(arrData);
        Arrays.sort(arrData); //Da gi sortira s godinata otpred
        arrData = reverseData(arrData);

        ArrayList<String> tmpDataList = new ArrayList<>();
        for (int i=1;i<=arrData.length;i++){
            tmpDataList.add((String) arrData[i-1]);
        }
       activeDays = tmpDataList;
    }

    private static Object[] reverseData(Object[] data){
        //Change DateTimeFormat from dd-mm-yyyy to yyyy-mm-dd
        //and back from yyyy-mm-dd to dd-mm-yyyy

        Object[] newArray =  new Object[data.length];
        for (int i=0;i< data.length;i++){
            String[] field = ((String) data[i]).split("-");
            newArray[i] = field[2]+"-"+field[1]+"-"+field[0];
        }
        return newArray;
    }

}
