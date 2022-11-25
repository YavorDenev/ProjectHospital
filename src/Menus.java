import java.util.*;

public abstract class Menus {
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
        for (int action: allowedActions) {   // -----------> �������� ���� ���������� ����� �������� �� allowedActions
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
            case 16 -> Hospital.showPatientsBySpeciality(choseSpeciality());
            case 17 -> Hospital.showPatientsByDate(choseDataForViewPatients());

            case 18 -> {
                //--------> ������� ����-��� �� Appointment -------------------- TODO
                //---------- ����� �� ��������� �� ���� � ���
                Patient p = (Patient) DBase.currentUser;
                //p.changeAppointmentsDateTime(3, "99-99-9999", 9999); <------ ������� �� ��������� �� ��������� ���������
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
                DBase.setActiveDays();
            }

            case 19 -> {
                showFreeOptionsByDoctorID();
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
                DBase.setActiveDays();
            }

            case 20 -> {
                choseAppointmentToRemove();
                Write.writeAppointmentsData(DBase.APPOINTMENTS_FILE);
                DBase.setActiveDays();
            }

            case 21 -> {  // ------> �������� �� ������  --------------------- TODO
                //-------- ����� �� ��������� �� ����������� �� ������
                Boss b = (Boss) DBase.currentUser;
                //b.addDoctor(firstName, lastName, age, sex, speciality); <------ ������� �� ��������� �� ��������� ���������
                Write.writeDoctorsData(DBase.DOCTORS_FILE);
                Read.getDoctorsFromFile(DBase.DOCTORS_FILE);
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
        while (docID> DBase.maxDoctorID || docID<1){
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

    private static String choseSpeciality(){
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
            System.out.print("Please enter" + valid + " number of speciality:");
            choice = CheckInputData.inputPositiveInteger();
            valid = " a valid";
        }
        return specMap.get(choice);
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

    private static void showFreeOptionsByDoctorID(){  //FOR ADD APPOINTMENT BY USER
        Map<Integer, String> choiceFreeDateMap = new HashMap<>();
        Map<Integer,String> choiceFreeTimeMap = new HashMap<>();

        int docID=0;
        while (docID> DBase.maxDoctorID || docID<1){
            System.out.print("Please enter doctor_id:");
            docID = CheckInputData.inputPositiveInteger();
        }

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

        int countTimes = 0; int countFreeOptions = 0;
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

        getUserChoiceForNewAppointment(choiceFreeDateMap, choiceFreeTimeMap, countFreeOptions, docID);
    }

    private static void getUserChoiceForNewAppointment(Map<Integer,String> mapDate, Map<Integer,String> mapTime, int cnt, int docID){



        System.out.print("Enter your choice:");
        int choice = scn.nextInt();
        while (choice<1||choice>cnt){
            choice = CheckInputData.inputPositiveInteger();
        }

        int patientID;
        if (DBase.currentUser instanceof Patient){
            patientID = ((Patient) DBase.currentUser).id;
            Appointment newApp = new Appointment(patientID,docID,"initial", mapDate.get(choice),mapTime.get(choice));
            DBase.appointments.add(newApp);
        }
        else System.out.println("Only patient can add new appointment!");
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
