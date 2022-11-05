public class Main {

    public static void main(String[] args) {

        //Create objects
        Db.currentUser = new Anonymous();

        //setDatabaseFromFiles();
        ReadWrite.getSpecialityesFromFile("specialities.txt");
        ReadWrite.getDoctorsFromFile("doctors.txt");
        ReadWrite.getPatientsFromFile("patients.txt");
        ReadWrite.getAppointmentsFromFile("appointments.txt");
        ReadWrite.getClassAllowedActionsFromFile("arrayListUserAllowedActions.txt");
        Db.initializeAllowedActions();

        TestKatev.doTests(); //everything is fine :)

        Menus.startPoint(); //todo empty for now
    }
}
