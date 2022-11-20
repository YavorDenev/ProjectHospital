import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menus {

    static int chosenDoctorID = 0;
    static String sortedByUpDown = "";

    static Scanner scn = new Scanner(System.in);
    static ArrayList<Integer> allowedActions = new ArrayList<>();

    public static void startPoint() {

        String GREEN = "\033[1;32m";
        String RESET_COLOR = "\033[0m";

        String userType = "";

        int lastAction = -1;
        while (lastAction!=21) {

            try {
                userType = String.valueOf(DBase.currentUser.getClass().getField("userType").get(DBase.currentUser));
            }
            catch (Exception exc){
                System.out.println(exc.getMessage());
            }

            System.out.println("\n======== " + GREEN + "WELCOME " + userType + " " + DBase.currentUser.firstName
                    + " " + DBase.currentUser.lastName
                    + RESET_COLOR + " ========");

            showCurrenUserAllowedActions();
            int choice = enterUserChoice();
            doRequest(choice);
            lastAction = choice;
        }

    }

    static void showCurrenUserAllowedActions() {

        if (DBase.currentUser instanceof Anonymous)  allowedActions = Anonymous.allowedActions;
        if (DBase.currentUser instanceof Patient)  allowedActions = Patient.allowedActions;
        if (DBase.currentUser instanceof Doctor)  allowedActions = Doctor.allowedActions;
        if (DBase.currentUser instanceof Boss)  allowedActions = Boss.allowedActions;

        for (int act: allowedActions){
            System.out.println(act + ") "+ DBase.allowedActions[act]);
        }
    }

    static int enterUserChoice(){
        System.out.print("Please enter your choice:");
        int ch = scn.nextInt();
        return ch;
    }

    static void doRequest(int choice){

        switch (choice) {
            case 0: DBase.currentUser =  new Anonymous(); break;
            case 1: Authorize.loginAsPatient(); break;
            case 2: Authorize.loginAsDoctor(); break;
            case 3: Authorize.loginAsBoss(); break;
            case 4: Hospital.showDoctors(); break;
            case 5: Hospital.showPatients(); break;
            case 6: {
                System.out.print("Please enter doctor_id:");
                int docId = scn.nextInt();
                Doctor.showDocApptsByDateTime(docId, "Up");
                break;
            }
            case 7: {
                Doctor.showDocApptsByDateTime(((Doctor) DBase.currentUser).id,"Up"); break;

            }
            case 8: {
                Patient.showAppointmentsByPatientId(((Patient) DBase.currentUser).id); break;
            }
            case 9: {
                System.out.print("Please enter patient_id:");
                int patId = scn.nextInt();
                Patient.showAppointmentsByPatientId(patId);
                break;
            }
            case 10:{
                selectDoctorAndSortDirection();
                Doctor.showDocApptsByDateTime(chosenDoctorID,sortedByUpDown);
                break;
            }
            case 11:{
                selectDoctorAndSortDirection();
                Doctor.showDocApptsByPatientNames(chosenDoctorID,sortedByUpDown);
                break;
            }
            case 12:{
                selectDoctorAndSortDirection();
                Doctor.showDocApptsByPatientId(chosenDoctorID,sortedByUpDown);
                break;
            }
            case 13:{
                Hospital.showPatientsByDocNames("Martin", "Katev");
            }

        }
    }

    private static void selectDoctorAndSortDirection(){
        int docID = 0;
        int chSort = 0;

        while (docID> DBase.maxDoctorID || docID<1){
            System.out.print("Please enter doctor_id:");
            docID = scn.nextInt();
        }
        while (chSort!=1 && chSort!=2){
            System.out.print("1-Up 2-Down. Enter your option:");
            chSort = scn.nextInt();
        }

        chosenDoctorID = docID;
        sortedByUpDown = (chSort==1) ? "Up":"Down";
    }

}
