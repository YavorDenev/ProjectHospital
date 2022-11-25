import java.util.*;

public abstract class DBase {

    public static final String APPOINTMENTS_FILE = "appointments.txt";
    public static final String DOCTORS_FILE = "doctors.txt";
    public static final String PATIENTS_FILE = "patients.txt";
    public static final String SPECIALTIES_FILE = "specialities.txt";
    public static final String ALLOWED_ACTIONS_FILE = "arrayListUserAllowedActions.txt";

    public static final String[] EXAMINATIONS = {"initial",
                                                "secondary",
                                                "consultation",
                                                "procedure"};
    public static User currentUser;

    public static Map<Integer, String> patientsMap = new HashMap<Integer, String>();
    public static Map<Integer, Doctor> doctorsMap = new HashMap<Integer, Doctor>();

    public static List<Boss> bosses = new ArrayList<>();

    public static List<Doctor> doctors = new ArrayList<>();

    public static List<Patient> patients = new ArrayList<>();

    public static List<Speciality> specialities = new ArrayList<>();

    public static List<Appointment> appointments = new ArrayList<>();

    public static String[] allowedActions = new String[23];

    public static int maxDoctorID = 0;
    public static ArrayList<String> activeDays = new ArrayList<>();

    public static void initializeAllowedActions(){
        allowedActions[0] = "EXIT"; //boss, doctor, patient, anonymous
        allowedActions[1] = "logout"; //boss, doctor, patient
        allowedActions[2] = "login as patient"; //anonymous
        allowedActions[3] = "login as doctor"; //anonymous
        allowedActions[4] = "login as boss"; //anonymous
        allowedActions[5] = "register as patient";
        allowedActions[6] = "show list of specialities"; //boss, doctor, patient, anonymous
        allowedActions[7] = "show list of doctors"; //boss, doctor, patient, anonymous ============> Hospital.showDoctors()
        allowedActions[8] = "show list of patients"; //boss, doctor ===============================> Hospital.showPatients()
        allowedActions[9] = "show doctor calendar by doctor_id"; //boss, doctor, patient  ==================> Doctor.showSortedDocApptsByCriteria(docId, Up/Down, SortCriteria.DATE_TIME);
        allowedActions[10] = "show my appointments"; //doctor, patient =====================================> Doctor.showSortedDocApptsByCriteria(...) OR patients.get(index).showMyAppointments()
        allowedActions[11] = "show patient appointments by patient_id"; //boss, doctor =============> Patient.showAppointmentsByPatientId(patientId)
        allowedActions[12] = "show sorted calendar on doctor_id by time"; //boss, doctor > Up/Down ============> Doctor.showSortedDocApptsByCriteria(docId, Up/Down, SortCriteria.DATE_TIME);
                                                                                                      // ======> Doctor.showSortedDocApptsByCriteria(Up/Down, SortCriteria.DATE_TIME);
        allowedActions[13] = "show sorted calendar on doctor_id by patient_name"; //boss, doctor > Up/Down ====> Doctor.showSortedDocApptsByCriteria(docId, Up/Down, SortCriteria.PATIENT_NAMES);
                                                                                                        // ====> Doctor.showSortedDocApptsByCriteria(Up/Down, SortCriteria.PATIENT_NAMES);
        allowedActions[14] = "show sorted calendar on doctor_id by patient_id"; //boss, doctor > Up/Down ======> Doctor.showSortedDocApptsByCriteria(docId, Up/Down, SortCriteria.PATIENT_ID);
                                                                                                      // ======> Doctor.showSortedDocApptsByCriteria(Up/Down, SortCriteria.PATIENT_ID);
        allowedActions[15] = "show patients by doctor names"; //boss, doctor > Down > Hospital.showPatientsByDocNames(firstName, lastName)
        allowedActions[16] = "show patients by speciality"; //boss, doctor > Down Hospital.showPatientsBySpeciality(speciality)
        allowedActions[17] = "show patients by date"; //boss, doctor > Down  Hospital.showPatientsByDate(date)
        allowedActions[18] = "change appointment by app_id"; //patient ================> patients.get(index).changeAppointmentsDateTime()
        allowedActions[19] = "add new appointment"; //patient   <---- НЕ СЕ ИСКА ПО УСЛОВИЕ ====> patients.get(index).AddAppointment(docId, typeOfExam, date, time)
        allowedActions[20] = "reject appointment by app_id";
        allowedActions[21] = "add new doctor"; //boss  <------- НЕ СЕ ИСКА ПО УСЛОВИЕ =======> bosses.get(index).addDoctor(firstName, lastName, age, sex, speciality)
        //allowedActions[22] = "change users allowed actions"; //boss  -------> MARTIN
    }

    public static void setActiveDays(){
        Set<String> setData = new HashSet<>(); //let use just dates in appointments

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

    private static Object[] reverseData(Object[] data){  //Change DateTimeFormat from dd-mm-yyyy to yyyy-mm-dd, and back
        Object[] newArray =  new Object[data.length];
        for (int i=0;i< data.length;i++){
            String[] field = ((String) data[i]).split("-");
            newArray[i] = field[2]+"-"+field[1]+"-"+field[0];
        }
        return newArray;
    }

}
