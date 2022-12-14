import java.util.Scanner;

public class Authorize {

    static Scanner scn = new Scanner(System.in);

    private Authorize(){}

    public static void loginAsPatient() {
        Menus.printBlueInputNotice("Please enter your first name:");
        String fName = CheckInput.inputAlphabeticalNonSpacesString();
        Menus.printBlueInputNotice("Please enter your patient_id:");
        int id = CheckInput.inputPositiveInteger();

        Patient foundUser = null;
        boolean successLogin = false;
        for (Patient p : DBase.patients){
           if (p.id == id && p.firstName.equalsIgnoreCase(fName)){
               successLogin = true;
               foundUser = p;
               break;
           }
        }

        if (!successLogin) Menus.printRedWarning("Wrong name or id, please try again!");
        else DBase.currentUser = foundUser;
    }

    public static void loginAsDoctor() {
        Menus.printBlueInputNotice("Please enter your first name:");
        String fName = CheckInput.inputAlphabeticalNonSpacesString();
        Menus.printBlueInputNotice("Please enter your doctor_id:");
        int id = CheckInput.inputPositiveInteger();

        Doctor foundUser = null;
        boolean successLogin = false;
        for (Doctor doc : DBase.doctors){
            if (doc.id == id && doc.firstName.equalsIgnoreCase(fName)){
                successLogin = true;
                foundUser = doc;
                break;
            }
        }

        if (!successLogin) Menus.printRedWarning("Wrong name or id, please try again!");
        else DBase.currentUser = foundUser;
    }

    public static void loginAsBoss() {
        Menus.printBlueInputNotice("Please enter your first name:");
        String fName = CheckInput.inputAlphabeticalNonSpacesString();
        Menus.printBlueInputNotice("Please enter your password:");
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

        if (!successLogin) Menus.printRedWarning("Wrong name or password, please try again!");
        else DBase.currentUser = foundUser;
    }

}
