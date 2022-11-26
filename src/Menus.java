import java.util.*;

public abstract class Menus {
    static Scanner scn = new Scanner(System.in);
    static ArrayList<Integer> allowedActions = new ArrayList<>();

    public static void startPoint() {

        String GREEN = "\033[1;32m";
        String RESET_COLOR = "\033[0m";

        String userType = "";

        int lastAction = -1;
        while (lastAction!=22) {

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
        for (int action: allowedActions) {   // -----------> проверка дали въведеното число отговаря на allowedActions
            if(action==ch) return ch;
        }
        System.out.println("Wrong number!");
        enterUserChoice();
        return 0;
    }

    static void doRequest(int choice){

        switch (choice) {
            case 0 -> System.exit(0);
            case 1 -> DBase.currentUser = new Anonymous();
            case 2 -> Authorize.loginAsPatient();
            case 3 -> Authorize.loginAsDoctor();
            case 4 -> Authorize.loginAsBoss();
            case 5 -> {
                System.out.println("Please enter you first name, last name, age and gender.");
                String firstName = enterFirstName();
                String lastName = enterLastName();
                int age = enterAge();
                String sex = enterSex();
                if (confirm()) {
                    DBase.currentUser = Anonymous.registerAsNewPatient(firstName, lastName, age, sex);
                    Write.writePatientsData(DBase.PATIENTS_FILE);
                } else new Anonymous();
            }
            case 6 -> Hospital.showSpecialities();
            case 7 -> Hospital.showDoctors();
            case 8 -> Hospital.showPatients();
            case 9 -> Doctor.showSortedDocApptsByCriteria(selectDoctorID(), "Up", SortCriteria.DATE_TIME);
            case 10 -> {
                if (DBase.currentUser instanceof Patient)  {
                    ((Patient) DBase.currentUser).showMyAppointment();
                }
                if (DBase.currentUser instanceof Doctor) {
                    Doctor.showSortedDocApptsByCriteria(((Doctor) DBase.currentUser).id, "Up", SortCriteria.DATE_TIME);
                }
            }
            case 11 -> {
                System.out.print("Please enter patient_id:");
                int patId = CheckInputData.inputPositiveInteger();
                Patient.showAppointmentsByPatientId(patId);
            }
            case 12 -> Doctor.showSortedDocApptsByCriteria(selectDoctorID(), selectSortDirection(), SortCriteria.DATE_TIME);
            case 13 -> Doctor.showSortedDocApptsByCriteria(selectDoctorID(), selectSortDirection(), SortCriteria.PATIENT_NAMES);
            case 14 -> Doctor.showSortedDocApptsByCriteria(selectDoctorID(), selectSortDirection(), SortCriteria.PATIENT_ID);
            case 15 -> {
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
            case 16 -> Hospital.showPatientsBySpeciality(enterSpeciality());
            case 17 -> Hospital.showPatientsByDate(choseDataForViewPatients());

            case 18 -> {
                changeDateTimeOfAppointment();
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
                DBase.setActiveDays();
            }

            case 19 -> {
                generateAppointment(true,0);
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
                DBase.setActiveDays();
            }

            case 20 -> {
                choseAppointmentToRemove();
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
                DBase.setActiveDays();
            }

            case 21 -> {
                System.out.println("Please enter the names, age, gender and speciality of the doctor you wish to add.");
                String firstName = enterFirstName();
                String lastName = enterLastName();
                int age = enterAge();
                String sex = enterSexForDoctors();
                String speciality = enterSpeciality();
                if (confirm()) {
                    Boss b = (Boss) DBase.currentUser;
                    b.addDoctor(firstName, lastName, age, sex, speciality);
                    Write.writeDoctorsData(DBase.DOCTORS_FILE);
                    Read.getDoctorsFromFile(DBase.DOCTORS_FILE);
                }
            }
        }
    }

    private static String selectSortDirection(){
        int chSort = 0;
        while (chSort!=1 && chSort!=2){
            System.out.print("1-Up 2-Down. Enter your option:");
            chSort = CheckInputData.inputPositiveInteger();
        }
        return  (chSort==1) ? "Up":"Down";
    }

    private static int selectDoctorID(){
        int docID = 0;
        while (!CheckInputData.checkDoctorWithThisIDExist(docID)){
            System.out.print("Please enter doctor_id:");
            docID = CheckInputData.inputPositiveInteger();
        }
        return docID;
    }

    private static boolean isSuchADoctorInHospital(String firstName, String lastName){
        for (Doctor doc: DBase.doctors){
            boolean check = (firstName.equalsIgnoreCase(doc.firstName)) && (lastName.equalsIgnoreCase(doc.lastName));
            if (check) return true;
        }
        return false;
    }

    private static String enterFirstName(){
        System.out.print("Enter first name:");
        return CheckInputData.inputAlphabeticalNonSpacesString();
    }

    private static String enterLastName(){
        System.out.print("Enter last name:");
        return CheckInputData.inputAlphabeticalNonSpacesString();
    }

    private static int enterAge(){
        System.out.println("Enter age:");
        return CheckInputData.inputPositiveInteger();
    }

    private static String enterSex(){
        System.out.println("Enter gender:");
        System.out.println("1) male");
        System.out.println("2) female");
        System.out.println("3),... another");
        int sexChoice = CheckInputData.inputPositiveInteger();
        String sex = "unknown";
        switch (sexChoice){
            case 1 -> sex = "male";
            case 2 -> sex = "female";
        }
        return sex;
    }

    private static String enterSexForDoctors(){
        System.out.println("Enter gender:");
        System.out.println("1) male");
        System.out.println("2) female");
        int sexChoice = CheckInputData.inputPositiveInteger();
        String sex = "unknown";
        switch (sexChoice){
            case 1 -> sex = "male";
            case 2 -> sex = "female";
        }
        return sex;
    }

    private static String enterSpeciality(){
        Map<Integer, String> specMap = new HashMap<>();

        int br =0;
        for (Speciality sp : DBase.specialities){
            br++;
            System.out.println(br+")"+sp.name);
            specMap.put(sp.id,sp.name);
        }

        int choice = 0;
        String valid = "";
        while (choice>br||choice<1){
            System.out.print("Enter" + valid + " number of speciality:");
            choice = CheckInputData.inputPositiveInteger();
            valid = " a valid";
        }
        return specMap.get(choice);
    }

    private static boolean confirm() {
        System.out.println("*** Confirm your operation!***");
        int finalChoice = 0;
        while(finalChoice!=1 && finalChoice!=2){
            System.out.println("1) Reject operation");
            System.out.println("2) Finish operation");
            finalChoice = CheckInputData.inputPositiveInteger();
        }
        return finalChoice == 2;
    }

    private static int enterTypeOfExamination(){
        System.out.println("Please choice type of examination:");
        System.out.println("1)"+ DBase.EXAMINATIONS[0]);
        System.out.println("2)"+ DBase.EXAMINATIONS[1]);
        System.out.println("3)"+ DBase.EXAMINATIONS[2]);
        System.out.println("4)"+ DBase.EXAMINATIONS[3]);
        return getChoice(4);
    }

    private static String choseDataForViewPatients(){
        int choice = 0;
        System.out.println();

        String notice = "Chose data to view patients list:";
        for (int i=0 ; i<DBase.activeDays.size() ; i++){
            System.out.println((i+1) + ")" + DBase.activeDays.get(i));
        }
        while (choice> DBase.activeDays.size()||choice<1){
            System.out.print(notice);
            choice = CheckInputData.inputPositiveInteger();
            notice = "Please select one of the dates listed above:";
        }
        return DBase.activeDays.get(choice-1);
    }

    private static void choseAppointmentToRemove(){
        String redColor = "\033[1;31m"; String resetColor = "\033[0m";

        Map<Integer,Appointment> choiceMap = new HashMap<>();
        int num =0;
        for (Appointment app: DBase.appointments) {
            boolean f = false;
            if (DBase.currentUser instanceof Patient && app.patientID == ((Patient) DBase.currentUser).id) f = true;
            else if (DBase.currentUser instanceof Doctor && app.doctorID == ((Doctor) DBase.currentUser).id) f = true;
            else if (DBase.currentUser instanceof Boss) f = true;
            if (f) {
                num++; choiceMap.put(num,app);
                System.out.print(num +") ");
                System.out.println(app);
            }
        }

        if (num == 0) System.out.println(redColor + "You have no appointments!" + resetColor);
        else {
            int choice;
            do {
                System.out.print("Please enter appointment to reject:");
                choice = CheckInputData.inputPositiveInteger();
            } while (choice > num);

            DBase.appointments.remove(choiceMap.get(choice));
            System.out.println();
            System.out.println(choiceMap.get(choice) + redColor + "was removed" + resetColor);
        }
    }

    private static void changeDateTimeOfAppointment(){
        Map<Integer, Appointment> myApps = new HashMap<>();

        System.out.println("Which your appointment you wish to change:");
        int br = 0;
        if (DBase.currentUser instanceof Patient)  {
            for (Appointment app : DBase.appointments) {
                if (app.patientID == ((Patient) DBase.currentUser).id){
                    br++;
                    System.out.println(br+")"+ app.toString());
                    myApps.put(br,app);
                }
            }
        }

        int choice = getChoice(br);

        generateAppointment(false, myApps.get(choice).getId()); //show calendar to change

    }

    private static void generateAppointment(boolean isNewApp, int oldAppID){
        //FOR ADD/CHANGE APPOINTMENT BY USER

        //when isNewApp is true > Add New Appointment
        //when isNewApp is false > change oldAppID to new one

        int docID = 0;
        int typeExamination=1;
        if (isNewApp){
            docID = selectDoctorID();
            typeExamination = enterTypeOfExamination();
        } else {
            for (Appointment app : DBase.appointments){  //For old Appointment get doctor from app.id
                if (app.getId()==oldAppID) {
                    docID = app.getDoctorID();
                    break;
                }
            }
        }

        Map<Integer, String> choiceFreeDateMap = new HashMap<>();
        Map<Integer,String> choiceFreeTimeMap = new HashMap<>();

        System.out.println(FunctionsText.leftFrameFixedLengthIn(
                "============== Choose free hour from Calendar ============= Doctor " + DBase.doctorsMap.get(docID)+
                        " ======================================",50,Colors.BLUE));

        ArrayList<String> appTimes = new ArrayList<>();

        ArrayList<String> min = new ArrayList<>();
        min.add("00"); min.add("20"); min.add("40"); //when convert int to min "00" become 0 (ugly)

        //gen start app times
        for (int h=9;h<=18;h++){
            for (String m : min){
                if (h!=12) appTimes.add( Integer.toString(h) + ":" + m);
                //h=12 dinner time
            }
        }

        int countTimes = 0;
        int countFreeOptions = 0;
        boolean isFreeTime;

        for (int i = 0; i<DBase.activeDays.size() ; i++){
            String actDate = DBase.activeDays.get(i); int dateWidth = 13;
            FunctionsText.printRightAlignmentColoredText(actDate,dateWidth,Colors.BLUE);
            for (String appTime : appTimes){
                isFreeTime = checkDoctorApps(docID, actDate, appTime);
                if (isFreeTime) {
                    countFreeOptions++;
                    choiceFreeDateMap.put(countFreeOptions, actDate);
                    choiceFreeTimeMap.put(countFreeOptions, appTime);
                }

                FunctionsText.printRightAlignmentColoredText(" |",2,Colors.BLUE);
                String color = isFreeTime ? Colors.GREEN : Colors.RED; //green or red
                FunctionsText.printRightAlignmentColoredText(appTime,9,color);

                String option = isFreeTime ? "("+countFreeOptions+")" : ""; //white option
                FunctionsText.printRightAlignmentColoredText(option,6,Colors.WHITE);

                countTimes++;
                if (countTimes%9==0) {
                    System.out.println();
                    if(!appTime.equals("18:40")){
                        FunctionsText.printRightAlignmentColoredText(" ",dateWidth,Colors.BLUE);
                    }
                    else FunctionsText.newColoredLine('-',Colors.BLUE,160);
                }
            }
        }

        getUserChoiceForNewAppointment(choiceFreeDateMap, choiceFreeTimeMap, countFreeOptions, docID, isNewApp, oldAppID, typeExamination);

        System.out.println(Colors.BLUE +"Operation confirmed!"+Colors.RESET);
    }

    private static void getUserChoiceForNewAppointment(Map<Integer,String> mapDate, Map<Integer,String> mapTime, int cnt, int docID, boolean isNewApp, int oldAppID, int typeExm){
        int patientID;
        if (DBase.currentUser instanceof Patient){
            int choice = getChoice(cnt);
            patientID = ((Patient) DBase.currentUser).id;
            boolean isIAMFree =  CheckInputData.checkIsChosenAppDataTimePatientIsFree(mapDate.get(choice),mapTime.get(choice),patientID);
            if (isIAMFree){
                if (isNewApp){
                    Appointment newApp = new Appointment(patientID,docID,DBase.EXAMINATIONS[typeExm-1], mapDate.get(choice),mapTime.get(choice));
                    DBase.appointments.add(newApp);
                } else {
                    String[] resTime = mapTime.get(choice).split(":");
                    int intTime = 100*Integer.parseInt(resTime[0]) +Integer.parseInt(resTime[1]) ;
                    ((Patient) DBase.currentUser).changeAppointmentsDateTime(oldAppID, mapDate.get(choice), intTime);
                }
            } else {
                System.out.println( Colors.RED+
                        "You have another appointment in this moment. Please choose again!"+
                        Colors.RESET);
                getUserChoiceForNewAppointment(mapDate, mapTime, cnt, docID, isNewApp, oldAppID, typeExm);
            }
        } else System.out.println("Only patient can add new appointment!");
    }

    private static int getChoice(int maxChoice){
        String t = "Enter your choice:";
        int choice = 0;
        while (choice<1||choice>maxChoice){
            System.out.println(t);
            choice = CheckInputData.inputPositiveInteger();
            t = "Invalid input number. Try again!";
        }
        return choice;
    }

    private static boolean checkDoctorApps(int docID, String date, String time){

        for (Appointment app: DBase.appointments){
            if (docID == app.doctorID && app.date.equals(date)) {
                String appTime = Integer.toString(app.time);
                String h = Integer.toString(app.time/100);
                String finTime = h+":"+ appTime.charAt(appTime.length()-2)+appTime.charAt(appTime.length()-1);

                if (finTime.equals(time)){
                    return false;
                }
            }
        }

        return true;
    }

}
