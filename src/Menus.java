import java.util.*;

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
        for (int action: allowedActions) {   // -------------------> �������� ���� ���������� ����� �������� �� allowedActions
            if(action==ch) return ch;
        }
        System.out.println("Wrong number!");
        enterUserChoice();
        return 0;
        //return ch;
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
                selectDoctorID();  // -------------------------------------------> �������� ���� ���������� ������ � ������ ID
                Doctor.showSortedDocApptsByCriteria(chosenDoctorID, "Up", SortCriteria.DATE_TIME);
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
                    System.out.print("Please enter doctor first name:");
                    docFirstName = scn.nextLine();
                    System.out.print("Please enter doctor last name:");
                    docLastName = scn.nextLine();
                    isCorrectDoctorNames = isSuchADoctorInHospital(docFirstName,docLastName);
                    if (!isCorrectDoctorNames) System.out.println("Doctor not found. Please try again.");
                }
                Hospital.showPatientsByDocNames(docFirstName, docLastName);
            }
            case 14 -> Hospital.showPatientsBySpeciality(choseSpeciality());
            case 15 -> Hospital.showPatientsByDate(choseDataForViewPatients());

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

    private static void selectDoctorID(){
        int docID = 0;
        while (docID> DBase.maxDoctorID || docID<1){
            System.out.print("Please enter doctor_id:");
            docID = CheckInputData.inputPositiveInteger();
        }
        chosenDoctorID = docID;
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
        String valid = "";
        while (choice>br||choice<1){
            System.out.print("Please enter" + valid + " number of speciality:");
            choice = CheckInputData.inputPositiveInteger();
            valid = " a valid";
        }
        return specMap.get(choice);
    }

    private static String choseDataForViewPatients(){
        Set<String> setData = new HashSet(); //let use just dates in appointments

        for (Appointment app : DBase.appointments){
            if (!setData.contains(app.date)) setData.add(app.date);
        }

        Object[] arrData = setData.toArray();
        arrData = reverseData(arrData);
        Arrays.sort(arrData); //Da gi sortira s godinata otpred
        arrData = reverseData(arrData);

        for (int i=1;i<=arrData.length;i++){
            System.out.println(i+") "+ arrData[i-1]);
        }

        int choice = 0;
        String notice = "Chose data to view patients list:";
        while (choice> arrData.length||choice<1){
            System.out.print(notice);
            choice = CheckInputData.inputPositiveInteger();
            notice = "Please select one of the dates listed above:";
        }
        return (String) arrData[choice-1];
    }

    private static Object[] reverseData(Object[] data){
        //Change DateTimeFormat from dd-mm-yyyy to yyyy-mm-dd
        //and back from yyyy-mm-dd to dd-mm-yyyy

        Object[] newArray =  new Object[data.length];
        for (int i=0;i< data.length;i++){
            String[] field = ((String) data[i]).split("-");
            newArray[i] = field[2]+"-"+field[1]+"-"+field[0];
        }
       return newArray;
    }
}
