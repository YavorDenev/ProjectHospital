public class Main {

    public static void main(String[] args) {

        //setDatabaseFromFiles();
        ReadWrite.getSpecialitiesFromFile("specialities.txt");
        ReadWrite.getDoctorsFromFile("doctors.txt");
        ReadWrite.getPatientsFromFile("real_patients.txt");
        ReadWrite.getAppointmentsFromFile("real_appointments.txt");
        ReadWrite.getClassAllowedActionsFromFile("arrayListUserAllowedActions.txt");
        DBase.initializeAllowedActions();

        Boss Yavor = new Boss("Yavor", "Denev", 54, "m", "Denev" );
        Boss Katev = new Boss("Martin", "Katev", 46, "m", "Katev" );

        DBase.currentUser = Katev; // new Anonymous();
        //TestKatev.doTests();
        //TestYavor.doTests();

        Menus.startPoint();
    }
}
