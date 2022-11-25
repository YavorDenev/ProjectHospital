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

         DBase.currentUser =  new Anonymous(); // final original login

        //HARD LOGIN
        // DBase.currentUser =  Katev; // hard login
        // DBase.currentUser =  DBase.doctorsMap.get(2);  // hard login
         //DBase.currentUser =  DBase.patients.get(1);

        //TestKatev.doTests();

        Menus.startPoint();
    }
}
