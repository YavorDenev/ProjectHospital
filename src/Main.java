public class Main {

    public static void main(String[] args) {

        //Create objects
        DBase.currentUser = new Anonymous();

        //setDatabaseFromFiles();
        ReadWrite.getSpecialitiesFromFile("specialities.txt");
        ReadWrite.getDoctorsFromFile("doctors.txt");
        ReadWrite.getPatientsFromFile("patients.txt");
        ReadWrite.getAppointmentsFromFile("appointments.txt");
        ReadWrite.getClassAllowedActionsFromFile("arrayListUserAllowedActions.txt");
        DBase.initializeAllowedActions();

        Boss Yavor = new Boss("Yavor", "Denev", 54, "m", "Denev" );
        Boss Katev = new Boss("Martin", "Katev", 46, "m", "Katev" );

          //TestKatev.doTests(); //everything is fine :)
          //TestYavor.doTests();

        Menus.startPoint(); //todo empty for now
    }
}
