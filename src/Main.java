public class Main {

    public static void main(String[] args) {

        Read.getAppointmentsFromFile(DBase.APPOINTMENTS_FILE);
        Read.getDoctorsFromFile(DBase.DOCTORS_FILE);
        Read.getPatientsFromFile(DBase.PATIENTS_FILE);
        Read.getSpecialitiesFromFile(DBase.SPECIALTIES_FILE);
        Read.getClassAllowedActionsFromFile(DBase.ALLOWED_ACTIONS_FILE);
        DBase.initializeAllowedActions();

        Boss Yavor = new Boss("Yavor", "Denev", 53, "m", "Denev" );
        Boss Katev = new Boss("Martin", "Katev", 46, "m", "Katev" );

        //HARD LOGIN
        // DBase.currentUser =  new Anonymous(); // final original login
        // DBase.currentUser =  Katev; // hard login
        // DBase.currentUser =  DBase.doctorsMap.get(2);  // hard login
        DBase.currentUser =  DBase.patients.get(10);

        //TestKatev.doTests();
        //TestYavor.doTests();

        //DBase.appointments.get(0).setDate("10-12-2022");//hard change
        //Write.writeAppointmentsData(DBase.FILES[0]);

        Menus.startPoint();
    }
}
