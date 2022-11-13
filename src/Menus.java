import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menus {

    static Scanner scn = new Scanner(System.in);
    static ArrayList<Integer> allowedActions = new ArrayList<>();

    public static void startPoint(){

        int lastAction = -1;
        while (lastAction!=21) {
            System.out.println("\n======= WELCOME " + DBase.currentUser.firstName + " "
                    + DBase.currentUser.lastName + " ========");

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
                System.out.println("Please enter doctor_id:");
                int docId = scn.nextInt();
                Doctor.showDocAppointments(docId);
                break;
            }
            case 7: {
                ((Doctor) DBase.currentUser).showDocAppointments(); break;
                //не ми харесва тоя метод. Кастването ще рече че само този тип потребител може да го прави
                //ако бе в хоспитал директно става с showDocApps(docID), което си го и имаме принципно
            }
            case 8: {
                ((Patient) DBase.currentUser).showMyAppointments(); break;
                //Също като по-горното
            }
            case 9: {
                System.out.println("Please enter patient_id:");
                int patId = scn.nextInt();
                Patient.showAppointmentsByPatientId(patId);
                break;
            }
            case 10:{
                System.out.println("Please enter doctor_id:");
                int docId = scn.nextInt();
                int chSort=0;
                while (chSort!=1&& chSort!=2){
                    System.out.println("1-Up 2-Down. Enter your option:");
                    chSort = scn.nextInt();
                }
                String upDown = (chSort==1) ? "Up":"Down";
                Doctor.showDocApptsByDateTime(docId, upDown);
                break;
            }
        }
    }

}
