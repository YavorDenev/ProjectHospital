import java.sql.SQLOutput;
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
        for (int action: allowedActions) {   // -------------------> проверка дали въведеното число отговаря на allowedActions
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
                DBase.currentUser = Anonymous.registerAsNewPatient();
                Write.writePatientsData(DBase.PATIENTS_FILE);
            }
            case 6 -> Hospital.showSpecialities();
            case 7 -> Hospital.showDoctors();
            case 8 -> Hospital.showPatients();
            case 9 -> {
                selectDoctorID();
                Doctor.showSortedDocApptsByCriteria(chosenDoctorID, "Up", SortCriteria.DATE_TIME);
            }
            case 10 -> {
                if (DBase.currentUser instanceof Patient)  {
                    Patient.showAppointmentsByPatientId(((Patient) DBase.currentUser).id);
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
            case 12 -> {
                selectDoctorAndSortDirection();
                Doctor.showSortedDocApptsByCriteria(chosenDoctorID, sortedByUpDown, SortCriteria.DATE_TIME);
            }
            case 13 -> {
                selectDoctorAndSortDirection();
                Doctor.showSortedDocApptsByCriteria(chosenDoctorID, sortedByUpDown, SortCriteria.PATIENT_NAMES);
            }
            case 14 -> {
                selectDoctorAndSortDirection();
                Doctor.showSortedDocApptsByCriteria(chosenDoctorID, sortedByUpDown, SortCriteria.PATIENT_ID);
            }
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
            case 16 -> Hospital.showPatientsBySpeciality(choseSpeciality());
            case 17 -> Hospital.showPatientsByDate(choseDataForViewPatients());

            case 18 -> {


                //--------> промяна дата-час на Appointment -------------------- TODO
                //---------- избор от конзолата на Дата и час
                Patient p = (Patient) DBase.currentUser;
                //p.changeAppointmentsDateTime(3, "99-99-9999", 9999); <------ подават се вкараните от конзолата параметри
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
            }

            case 19 -> {
                showFreeOptionsByDoctorID(); //IN CONSTRUCTION INTERFACE IS OK

                //p.addAppointment(docId, typeOfExam, date, time); <------ подават се вкараните от конзолата параметри
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
            }

            case 20 -> {
                choseAppointmentToRemove();
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
            }

            case 21 -> {  // ------> добавяне на Доктор  --------------------- TODO
                //-------- избор от конзолата на параметрите на Доктор
                Boss b = (Boss) DBase.currentUser;
                //b.addDoctor(firstName, lastName, age, sex, speciality); <------ подават се вкараните от конзолата параметри
                Write.writeDoctorsData(DBase.DOCTORS_FILE);
                Read.getDoctorsFromFile(DBase.DOCTORS_FILE);
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
        int choice = 0;
        System.out.println("");

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

        boolean f;  int br=0;
        for (Appointment app: DBase.appointments) {
            f = false;
            if (DBase.currentUser instanceof Patient && app.patientID == ((Patient) DBase.currentUser).id) f = true;
            else if (DBase.currentUser instanceof Doctor && app.doctorID == ((Doctor) DBase.currentUser).id) f = true;
            else if (DBase.currentUser instanceof Boss) f = true;

            if (f) {
                br++; choiceMap.put(br,app);
                System.out.print(br+") ");
                System.out.println(app);
            }
        }

        int ch = 0;
        if (br>0) {
            while (ch < 1 || ch > br) {
                System.out.print("Please enter appointment) to reject:");
                ch = CheckInputData.inputPositiveInteger();
            }

            Appointment appChosen = choiceMap.get(ch); //not necessary but for better reading
            int index = DBase.appointments.indexOf(appChosen);

            if (index>=0) {
                System.out.println();
                System.out.println(appChosen + redColor + "was removed" + resetColor);
                DBase.appointments.remove(index);
            }
        }
        else {
            System.out.println(redColor + "You have no appointments!" + resetColor);
        }
    }

    private static void showFreeOptionsByDoctorID(){
        int docID=0;
        while (docID> DBase.maxDoctorID || docID<1){
            System.out.print("Please enter doctor_id:");
            docID = CheckInputData.inputPositiveInteger();
        }

        System.out.println(FunctionsText.fixLengthIn(
                "============== Choose free hour from Calendar ============= Doctor " + DBase.doctorsMap.get(docID)+
                        " ======================================",50,"blue"));

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

        int countTimes = 0; int countFreeOptions = 0;
        boolean isFreeTime;
        for (int i=0 ; i<DBase.activeDays.size() ; i++){
            String actDate = DBase.activeDays.get(i);
            System.out.print(FunctionsText.fixLengthIn (actDate,15,"reset"));
            for (String appTime : appTimes){
                isFreeTime = checkDoctorApps(docID, actDate, appTime);
                if (isFreeTime) countFreeOptions++;

                String color = isFreeTime ? "green" : "red"; //green or red
                String optToShow = appTime;
                if (isFreeTime) optToShow += "("+countFreeOptions+")";
                //iskam da napravq beli samo opciite no se skapva duljinata za6toto
                //cvetovete u4astvat kato text, koito ne se izpisva realno
                System.out.print(FunctionsText.fixLengthIn(optToShow,15, color));
                countTimes++;
                if (countTimes%9==0) {
                    System.out.println();
                    if(!appTime.equals("18:40")){
                        System.out.print(FunctionsText.fixLengthIn (" ",15));
                    }
                    else FunctionsText.newColoredLine('-',"blue",160);
                }
            }
        }
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
