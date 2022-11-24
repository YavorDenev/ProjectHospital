public class Main {

    public static void main(String[] args) {

        Read.getAppointmentsFromFile(DBase.FILES[0]);
        Read.getDoctorsFromFile(DBase.FILES[1]);
        Read.getPatientsFromFile(DBase.FILES[2]);
        Read.getSpecialitiesFromFile(DBase.FILES[3]);
        Read.getClassAllowedActionsFromFile(DBase.FILES[4]);
        DBase.initializeAllowedActions();

        Boss Yavor = new Boss("Yavor", "Denev", 53, "m", "Denev" );
        Boss Katev = new Boss("Martin", "Katev", 46, "m", "Katev" );

        //HARD LOGIN
        // DBase.currentUser =  new Anonymous(); // final original login
        // DBase.currentUser =  Katev; // hard login
        // DBase.currentUser =  DBase.doctorsMap.get(2);  // hard login
        DBase.currentUser =  DBase.patients.get(1);

        //TestKatev.doTests();
        //TestYavor.doTests();

        //DBase.appointments.get(0).setDate("10-12-2022");//hard change

        Write.writeAppointmentsData();

        Menus.startPoint();
    }
}
