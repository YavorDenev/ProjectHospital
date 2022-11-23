public class Main {

    public static void main(String[] args) {

        //setDatabaseFromFiles();
        Read.getSpecialitiesFromFile("specialities.txt");
        Read.getDoctorsFromFile("doctors.txt");
        Read.getPatientsFromFile("patients.txt");
        Read.getAppointmentsFromFile("appointments.txt");
        Read.getClassAllowedActionsFromFile("arrayListUserAllowedActions.txt");
        DBase.initializeAllowedActions();

        Boss Yavor = new Boss("Yavor", "Denev", 54, "m", "Denev" );
        Boss Katev = new Boss("Martin", "Katev", 46, "m", "Katev" );

        //HARD LOGIN
         DBase.currentUser =  new Anonymous(); // final original login
        // DBase.currentUser =  Katev; // hard login
        //DBase.currentUser =  DBase.doctorsMap.get(2);  // hard login

        //TestKatev.doTests();
        //TestYavor.doTests();

        DBase.appointments.get(0).setDate("10-12-2022");//hard change

        Write.writeAppointmentsData();

        Menus.startPoint();
    }
}
