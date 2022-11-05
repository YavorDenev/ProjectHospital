public abstract class Db {

    public static User currentUser;

    public static Boss[] boss;
    public static Doctor[] doctors;
    public static Patient[] patients;
    public static Specialities[] specialities;
    public static Appointment[] appointments;

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
        allowedActions[4] = "show list of doctors"; //boss, doctor, patient, anonymous
        allowedActions[5] = "show list of patients"; //boss, doctor
        allowedActions[6] = "show doctor calendar by doctor_id"; //boss, doctor
        allowedActions[7] = "show my calendar"; //doctor
        allowedActions[8] = "show my appointments"; //patient
        allowedActions[9] = "show patient appointments by patient_id"; //boss, doctor
        allowedActions[10] = "show sorted calendar on doctor_id by time"; //boss, doctor  > Up; Down
        allowedActions[11] = "show sorted calendar on doctor_id by patient_name"; //boss, doctor > Up; Down
        allowedActions[12] = "show sorted calendar on doctor_id by patient_id"; //boss, doctor > Up; Down
        allowedActions[13] = "show sorted calendar by doctor_firstName"; //boss, doctor > Down
        allowedActions[14] = "show sorted calendar by speciality"; //boss, doctor > Down
        allowedActions[15] = "show sorted calendar by datetime"; //boss, doctor > Down
        allowedActions[16] = "change appointment by app_id"; //patient
        allowedActions[17] = "reject appointment by app_id"; //boss, doctor, patient
        allowedActions[18] = "make doctor inactive (hidden)"; //boss
        allowedActions[19] = "add new doctor"; //boss
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
