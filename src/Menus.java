import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

            showCurrentUserAllowedActions();
            int choice = enterUserChoice();
            doRequest(choice);
            lastAction = choice;
        }

    }

    static void showCurrentUserAllowedActions() {

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
        int ch = CheckInputData.inputNotNegativeInteger();
        return ch;
    }

    static void doRequest(int choice){

        switch (choice) {
            case 0 -> DBase.currentUser = new Anonymous();
            case 1 -> Authorize.loginAsPatient();
            case 2 -> Authorize.loginAsDoctor();
            case 3 -> Authorize.loginAsBoss();
            case 4 -> Hospital.showDoctors();
            case 5 -> Hospital.showPatients();
            case 6 -> {
                System.out.print("Please enter doctor_id:");
                int docId = CheckInputData.inputPositiveInteger();
                Doctor.showSortedDocApptsByCriteria(docId, "Up", SortCriteria.DATE_TIME);
            }
            case 7 -> Doctor.showSortedDocApptsByCriteria(((Doctor) DBase.currentUser).id, "Up", SortCriteria.DATE_TIME);
            case 8 -> Patient.showAppointmentsByPatientId(((Patient) DBase.currentUser).id);
            case 9 -> {
                System.out.print("Please enter patient_id:");
                int patId = CheckInputData.inputPositiveInteger();
                Patient.showAppointmentsByPatientId(patId);
            }
            case 10 -> {
                selectDoctorAndSortDirection();
                Doctor.showSortedDocApptsByCriteria(chosenDoctorID, sortedByUpDown, SortCriteria.DATE_TIME);
            }
            case 11 -> {
                selectDoctorAndSortDirection();
                Doctor.showSortedDocApptsByCriteria(chosenDoctorID, sortedByUpDown, SortCriteria.PATIENT_NAMES);
            }
            case 12 -> {
                selectDoctorAndSortDirection();
                Doctor.showSortedDocApptsByCriteria(chosenDoctorID, sortedByUpDown, SortCriteria.PATIENT_ID);
            }
            case 13 -> {
                String docFirstName = "";
                String docLastName = "";
                boolean isCorrectDoctorNames = false;
                while (!isCorrectDoctorNames){
                    docFirstName = scn.nextLine();
                    docLastName = scn.nextLine();
                    isCorrectDoctorNames = isSuchADoctorInHospital(docFirstName,docLastName);
                    if (!isCorrectDoctorNames) System.out.println("Doctor not found. Please try again.");
                }
                Hospital.showPatientsByDocNames(docFirstName, docLastName);
            }
            case 14 -> {
                Hospital.showPatientsBySpeciality(choseSpeciality());
            }
        }
    }

    private static void selectDoctorAndSortDirection(){
        int docID = 0;
        int chSort = 0;

        while (docID> DBase.maxDoctorID || docID<1){
            System.out.print("Please enter doctor_id:");
            docID = CheckInputData.inputPositiveInteger();
        }
        while (chSort!=1 && chSort!=2){
            System.out.print("1-Up 2-Down. Enter your option:");
            chSort = CheckInputData.inputPositiveInteger();
        }

        chosenDoctorID = docID;
        sortedByUpDown = (chSort==1) ? "Up":"Down";
    }

    private static boolean isSuchADoctorInHospital(String firstName, String lastName){
        for (Doctor doc: DBase.doctors){
            boolean check = (firstName.equalsIgnoreCase(doc.firstName)) && (lastName.equalsIgnoreCase(doc.lastName));
            if (check) return true;
        }
        return false;
    }

    private static String choseSpeciality(){
        Map<Integer, String> specMap = new HashMap();

        int br =0;
        for (Speciality sp : DBase.specialities){
            br++;
            System.out.println(br+")"+sp.name);
            specMap.put(sp.id,sp.name);
        }

        int choice = 0;
        while (choice>br||choice<1){
            System.out.print("Please enter number of speciality:");
            choice = scn.nextInt();
        }
        return specMap.get(choice);
    }
}
