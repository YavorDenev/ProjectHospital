import java.util.*;

public class Menus {
    private Menus(){}

    static ArrayList<Integer> allowedActions = new ArrayList<>();
    static Map<Integer, Integer> optionsMap = new HashMap<>();  // <option showed, real option num from file>

    public static void startPoint() {
        String userType = "";

        while (true) {  //exit is option in menu
            try {
                userType = String.valueOf(DBase.currentUser.getClass().getField("userType").get(DBase.currentUser));
            }
            catch (Exception exc){
                System.out.println(exc.getMessage());
            }

            System.out.println("\n======== " + Colors.GREEN + "WELCOME " + userType + " " + DBase.currentUser.firstName
                    + " " + DBase.currentUser.lastName
                    + Colors.RESET + " ========");

            showCurrentUserAllowedActions();
            int choice = enterUserChoice();
            doRequest(choice);
        }
    }

    private static void showCurrentUserAllowedActions() {
        if (DBase.currentUser instanceof Anonymous)  allowedActions = Anonymous.allowedActions;
        if (DBase.currentUser instanceof Patient)  allowedActions = Patient.allowedActions;
        if (DBase.currentUser instanceof Doctor)  allowedActions = Doctor.allowedActions;
        if (DBase.currentUser instanceof Boss)  allowedActions = Boss.allowedActions;

        Map<Integer,Integer> tmpOptionsMap = new HashMap<>();
        int optCounter = 0;
        for (int act: allowedActions){
            optCounter++;
            System.out.println(optCounter + ") "+ DBase.allowedActions[act]);
            tmpOptionsMap.put(optCounter, act);
        }
       optionsMap = tmpOptionsMap;
    }

    private static int enterUserChoice(){
        printBlueInputNotice("Please enter your choice:");
        int ch = CheckInput.inputPositiveInteger();
        if (!optionsMap.containsKey(ch)) { //when chosen option is not in optionsMap
            printRedWarning("This option is not in list! Please try again!");
            ch = enterUserChoice();
        }
        return ch;
    }

    private static void doRequest(int choice){

        int realChoice = optionsMap.get(choice);
        switch (realChoice) {
            case 0 -> {
                System.out.println();
                System.out.println("========  Goodbye! Welcome to us again.  ========");
                System.exit(0);
            }
            case 1 -> DBase.currentUser = new Anonymous();
            case 2 -> Authorize.loginAsPatient();
            case 3 -> Authorize.loginAsDoctor();
            case 4 -> Authorize.loginAsBoss();
            case 5 -> {
                printBlueNotice("Please enter you first name, last name, age and gender.");
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
                printBlueInputNotice("Please enter patient_id:");
                int patId = CheckInput.inputPositiveInteger();
                Patient.showAppointmentsByPatientId(patId);
            }
            case 12 -> Doctor.showSortedDocApptsByCriteria(selectDoctorID(), selectSortDirection(), SortCriteria.DATE_TIME);
            case 13 -> Doctor.showSortedDocApptsByCriteria(selectDoctorID(), selectSortDirection(), SortCriteria.PATIENT_NAMES);
            case 14 -> Doctor.showSortedDocApptsByCriteria(selectDoctorID(), selectSortDirection(), SortCriteria.PATIENT_ID);
            case 15 -> {
                printBlueNotice("Please enter the doctor's names.");
                String firstName = enterFirstName();
                String lastName = enterLastName();
                if ( ! isDoctorWithSuchNames(firstName,lastName)) printRedWarning("There is no doctor with such names! Please try again.");
                else Hospital.showPatientsByDocNames(firstName, lastName);
            }
            case 16 -> Hospital.showPatientsBySpeciality(enterSpeciality());
            case 17 -> Hospital.showPatientsByDate(chooseDataForViewPatients());

            case 18 -> {
                changeDateTimeOfAppointment();
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
                DBase.setActiveDays();
            }
            case 19 -> {   //Add new appointment
                while(!patientBookAnAppointment(true, 0)) {};   //loop until correct chose (for example if men/unknown choose gynecology)
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
                DBase.setActiveDays();
            }
            case 20 -> {
                chooseAppointmentToRemove();
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
                DBase.setActiveDays();
            }
            case 21 -> enterNewDoctor();
        }
    }

    private static String selectSortDirection(){
        printBlueInputNotice("1-Up 2-Down. ");
        return (CheckInput.inputMaxInt(2) == 1) ? "Up" : "Down";
    }

    private static int selectDoctorID(){
        int docID = 0;
        while (!CheckInput.checkDoctorWithThisIDExist(docID)){
            printBlueInputNotice("Please enter doctor_id:");
            docID = CheckInput.inputPositiveInteger();
        }
        return docID;
    }

    private static boolean isDoctorWithSuchNames(String firstName, String lastName){
        for (Doctor doc: DBase.doctors){
            boolean check = (firstName.equalsIgnoreCase(doc.firstName)) && (lastName.equalsIgnoreCase(doc.lastName));
            if (check) return true;
        }
        return false;
    }

    private static void enterNewDoctor(){
        printBlueNotice("Please enter the names, age, gender and speciality of the doctor you wish to add.");
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

    private static String enterFirstName(){
        printBlueInputNotice("Enter first name:");
        return CheckInput.inputAlphabeticalNonSpacesString();
    }

    private static String enterLastName(){
        printBlueInputNotice("Enter last name:");
        return CheckInput.inputAlphabeticalNonSpacesString();
    }

    private static int enterAge(){
        printBlueInputNotice("Enter age:");
        return CheckInput.inputPositiveInteger();
    }

    private static String enterSex(){
        printBlueNotice("Enter gender:");
        System.out.println("1) male");
        System.out.println("2) female");
        System.out.println("3) ... maybe later");
        int sexChoice = CheckInput.inputPositiveInteger();
        String sex = "unknown";
        switch (sexChoice){
            case 1 -> sex = "male";
            case 2 -> sex = "female";
        }
        return sex;
    }

    private static String enterSexForDoctors(){
        printBlueNotice("Enter gender:");
        System.out.println("1) male");
        System.out.println("2) female");
        int sexChoice = CheckInput.inputMaxInt(2);
        String sex = "unknown";
        switch (sexChoice){
            case 1 -> sex = "male";
            case 2 -> sex = "female";
        }
        return sex;
    }

    private static String enterSpeciality(){
        printBlueNotice("Please select speciality:");

        Map<Integer, String> specMap = new HashMap<>();
        int cntOptions = 0;
        for (Speciality sp : DBase.specialities){
            cntOptions++;
            System.out.println(cntOptions+") " + sp.name);
            specMap.put(sp.id, sp.name);
        }

        int choice = CheckInput.inputMaxInt(cntOptions);
        return specMap.get(choice);
    }

    private static boolean confirm() {
        printBlueNotice("*** Confirm your operation!***");
        System.out.println("1) Reject operation");
        System.out.println("2) Finish operation");
        return CheckInput.inputMaxInt(2) == 2;
    }

    private static int enterTypeOfExamination(){
        printBlueNotice("Please choice type of examination:");
        System.out.println("1)"+ DBase.EXAMINATIONS[0]);
        System.out.println("2)"+ DBase.EXAMINATIONS[1]);
        System.out.println("3)"+ DBase.EXAMINATIONS[2]);
        System.out.println("4)"+ DBase.EXAMINATIONS[3]);
        return CheckInput.inputMaxInt(4);
    }

    private static String chooseDataForViewPatients(){
        printBlueNotice("Please select data to view patients list:");

        for (int i=0 ; i<DBase.activeDays.size() ; i++){
            System.out.println((i+1) + ") " + DBase.activeDays.get(i));
        }

        int choice = CheckInput.inputMaxInt(DBase.activeDays.size());
        return DBase.activeDays.get(choice-1);
    }


    private static void chooseAppointmentToRemove(){
        Map<Integer,Appointment> choiceMap = new HashMap<>();
        int cntOptions =0;
        for (Appointment app: DBase.appointments) {
            boolean f = false;
            if (DBase.currentUser instanceof Patient && app.patientID == ((Patient) DBase.currentUser).id) f = true;
            else if (DBase.currentUser instanceof Doctor && app.doctorID == ((Doctor) DBase.currentUser).id) f = true;
            else if (DBase.currentUser instanceof Boss) f = true;
            if (f) {
                cntOptions++; choiceMap.put(cntOptions,app);
                System.out.print(cntOptions +") ");
                System.out.println(app);
            }
        }

        if (cntOptions == 0) printRedWarning("You have no appointments!");
        else {
            printBlueNotice("Please select number of appointment to reject. ");
            int choice = CheckInput.inputMaxInt(cntOptions);

            if (confirm()) {
                DBase.appointments.remove(choiceMap.get(choice));
                System.out.println();
                System.out.println(choiceMap.get(choice) + Colors.RED + " WAS REMOVED!" + Colors.RESET);
            }
        }
    }

    private static void changeDateTimeOfAppointment(){
        Map<Integer, Appointment> myApps = new HashMap<>();

        printBlueNotice("Which your appointment you wish to change?");
        int cntOptions = 0;
        if (DBase.currentUser instanceof Patient)  {
            for (Appointment app : DBase.appointments) {
                if (app.patientID == ((Patient) DBase.currentUser).id){
                    cntOptions++;
                    System.out.println(cntOptions+")"+ app.toString());
                    myApps.put(cntOptions,app);
                }
            }
        }

        int choice = CheckInput.inputMaxInt(cntOptions);

        patientBookAnAppointment(false, myApps.get(choice).getId()); //show calendar to change
    }

    private static boolean patientBookAnAppointment(boolean isNewApp, int appID){

        int docID = 0;
        int typeExamination=1;
        if (isNewApp){
            docID = selectDoctorID();
            if (!DBase.currentUser.sex.equals("female")){
                if (DBase.doctorsMap.get(docID).speciality.equals("gynecology")){
                    printRedWarning("You cant book appointment for gynecology");
                    return false;
                }
            }
            else typeExamination = enterTypeOfExamination();
        } else {
            for (Appointment app : DBase.appointments){  //For old Appointment get doctor from app.id
                if (app.getId()== appID) {
                    docID = app.getDoctorID();
                    break;
                }
            }
        }

       printBlueNotice("============== Choose free hour from Calendar ============= Doctor "
                + DBase.doctorsMap.get(docID)+ " ======================================");

        Map<Integer, String> choiceFreeDateMap = new HashMap<>();
        Map<Integer,String> choiceFreeTimeMap = new HashMap<>();

        ArrayList<String> appTimes = new ArrayList<>();
        ArrayList<String> min = new ArrayList<>();
        min.add("00"); min.add("20"); min.add("40");  //when convert int to min "00" become 0 (ugly)

        for (int h=9; h<=18; h++){   //gen start app times
            for (String m : min){
                if (h!=12) appTimes.add(h + ":" + m);   //h=12 dinner time
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

        getUserChoiceForNewAppointment(choiceFreeDateMap, choiceFreeTimeMap, countFreeOptions, docID, isNewApp, appID, typeExamination);

        printBlueNotice("Operation confirmed!");

        return true; //method must be not void because we need exit when man chose gynecology option
    }

    private static void getUserChoiceForNewAppointment(Map<Integer,String> mapDate, Map<Integer,String> mapTime, int cnt, int docID, boolean isNewApp, int appID, int typeExm){
        if (DBase.currentUser instanceof Patient){
            int choice = CheckInput.inputMaxInt(cnt);
            String[] resTime = mapTime.get(choice).split(":");
            int intTime = 100*Integer.parseInt(resTime[0]) +Integer.parseInt(resTime[1]) ;
            int patientID = ((Patient) DBase.currentUser).id;
            boolean isIAMFree =  CheckInput.checkIsChosenAppDataTimePatientIsFree(mapDate.get(choice),mapTime.get(choice),patientID);
            if (isIAMFree){
                if (isNewApp) {
                    ((Patient) DBase.currentUser).addAppointment(docID,DBase.EXAMINATIONS[typeExm-1], mapDate.get(choice),intTime);
                } else {
                    ((Patient) DBase.currentUser).changeAppointmentsDateTime(appID, mapDate.get(choice), intTime);
                }
            } else {
                printRedWarning("You have another appointment in this moment. Please choose again!");
                getUserChoiceForNewAppointment(mapDate, mapTime, cnt, docID, isNewApp, appID, typeExm);
            }
        } else printRedWarning("Only patient can add new appointment!");
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

    public static void printBlueInputNotice(String txt){
        System.out.print(Colors.BLUE + txt + Colors.RESET);
    }

    public static void printBlueNotice(String txt){
        System.out.println(Colors.BLUE + txt + Colors.RESET);
    }

    public static void printRedWarning(String txt){
        System.out.println(Colors.RED + txt + Colors.RESET);
    }
}