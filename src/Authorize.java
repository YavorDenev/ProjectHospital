import java.util.Scanner;

public abstract class Authorize {

    static Scanner scn = new Scanner(System.in);

    public static void loginAsPatient() {
        System.out.print("Please enter your first name:");
        String fName = scn.nextLine();
        System.out.print("Please enter your patient_id:");
        int id = CheckInputData.inputPositiveInteger();

        Patient foundUser = null;
        boolean successLogin = false;
        for (Patient p : DBase.patients){
           if (p.id == id && p.firstName.toUpperCase().equals(fName.toUpperCase())){
               successLogin = true;
               foundUser = p;
               break;
           }
        }

        if (!successLogin) System.out.println("wrong name or id, please try again!");
        else DBase.currentUser = foundUser;
    }

    public static void loginAsDoctor() {
        System.out.print("Please enter your first name:");
        String fName = scn.nextLine();

        System.out.print("Please enter your doctor_id:");
        int id = CheckInputData.inputPositiveInteger();

        Doctor foundUser = null;
        boolean successLogin = false;
        for (Doctor doc : DBase.doctors){
            if (doc.id == id && doc.firstName.toUpperCase().equals(fName.toUpperCase())){
                successLogin = true;
                foundUser = doc;
                break;
            }
        }

        if (!successLogin) System.out.println("wrong name or id, please try again!");
        else DBase.currentUser = foundUser;
    }

    public static void loginAsBoss() {
        System.out.print("Please enter your first name:");
        String fName = scn.nextLine();
        System.out.print("Please enter your password:");
        String password = scn.nextLine();

        Boss foundUser = null;
        boolean successLogin = false;
        for (Boss usr : DBase.bosses){
            if (usr.firstName.equals(fName) && usr.password.equals(password)){
                successLogin = true;
                foundUser = usr;
                break;
            }
        }

        if (!successLogin) System.out.println("Wrong name or password, please try again!");
        else DBase.currentUser = foundUser;
    }
}
