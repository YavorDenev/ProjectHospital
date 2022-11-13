import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menus {

    static Scanner scn = new Scanner(System.in);
    static ArrayList<Integer> allowedActions = new ArrayList<>();

    public static void startPoint(){

        String GREEN = "\033[1;32m";
        String RESET_COLOR = "\033[0m";

        int lastAction = -1;
        while (lastAction!=21) {
            System.out.println("\n======== " + GREEN + "WELCOME " + DBase.currentUser.firstName
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
                Doctor.showDocAppointments(docId);
                break;
            }
            case 7: {
                Doctor.showDocAppointments(((Doctor) DBase.currentUser).id); break;
                //((Doctor) DBase.currentUser).showDocAppointments(); break;
                //showDocAppointments() тоя метод е излишен, а класическия по id бе добре да е в Хоспитал
            }
            case 8: {
                Patient.showAppointmentsByPatientId(((Patient) DBase.currentUser).id); break;
                //Същото важи и за този. Сега ако искам да ги викам от други потребители
                //не мога през тези опции и се бъгва заради несъответсвието на типа в currentUser
            }
            case 9: {
                System.out.print("Please enter patient_id:");
                int patId = scn.nextInt();
                Patient.showAppointmentsByPatientId(patId);
                break;
            }
            case 10:{
                System.out.print("Please enter doctor_id:");
                int docId = scn.nextInt();
                int chSort=0;
                while (chSort!=1&& chSort!=2){
                    System.out.print("1-Up 2-Down. Enter your option:");
                    chSort = scn.nextInt();
                }
                String upDown = (chSort==1) ? "Up":"Down";
                Doctor.showDocApptsByDateTime(docId, upDown);
                break;
            }


        }
    }

}
