public class Main {

    public static void main(String[] args) {

        Read.showIntroPictureFile("intro150width.txt");

        Read.getAppointmentsFromFile(DBase.APPOINTMENTS_FILE);
        Read.getDoctorsFromFile(DBase.DOCTORS_FILE);
        Read.getPatientsFromFile(DBase.PATIENTS_FILE);
        Read.getSpecialitiesFromFile(DBase.SPECIALTIES_FILE);
        Read.getClassAllowedActionsFromFile(DBase.ALLOWED_ACTIONS_FILE);
        DBase.initializeAllowedActions();

        Boss Yavor = new Boss("Yavor", "Denev", 53, "m", "Denev" );
        Boss Katev = new Boss("Martin", "Katev", 46, "m", "Katev" );

        DBase.currentUser =  new Anonymous(); // final original login

        Menus.startPoint();

    }
}
