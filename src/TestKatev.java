import java.util.ArrayList;

public abstract class TestKatev {

    public static void doTests(){

        //check reading specialities

        //System.out.println("\n ======= SPECIALITIES ======");
        System.out.println();
        Hospital.showSpecialities();

        //check doctors
        //System.out.println("\n ========= DOCTORS ========");
        System.out.println();
        Hospital.showDoctors();

        //check patients
        //
        // System.out.println("\n ======== PATIENTS =======");
        System.out.println();
        Hospital.showPatients();

        //check appointments
        System.out.println("\n ====== APPOINTMENTS ======");
        for (Appointment a : DBase.appointments) {System.out.println(a);}

        //Check rights
        System.out.println("\n====== USER ALLOWED ACTIONS (Beta) =====\n");
        System.out.println("Права на ANONYMOUS ========================= \n" + showArrayList(Anonymous.allowedActions));
        System.out.println("Права на PATIENTS ========================= \n" + showArrayList(Patient.allowedActions));
        System.out.println("Права на DOCTORS ========================= \n" + showArrayList(Doctor.allowedActions));
        System.out.println("Права на BOSS ========================= \n" + showArrayList(Boss.allowedActions));

    }

    private static String showArrayList(ArrayList<Integer> al){
        String sum = "";
        for (int x: al){
            sum += DBase.allowedActions[x] + "\n";
        }
        return sum;
    }

}
