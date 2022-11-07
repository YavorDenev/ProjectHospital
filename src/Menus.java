public abstract class Menus {

    public static void startPoint(){
        //check user allowedActions
    }

    static void showMainMenu() {
        System.out.println("""

                What do you want to do?
                \t1. Add new Patient;
                \t2. Login as Patient;
                \t3. Login as Doctor;
                \t4. Login as Chief;
                \t5. Exit.""");
      //  Logins.enterChoice();
    }

// Towa ste go realiziram v Menu v metod prosledqva6t Db.currentUser
    /*
    public static void enterChoice() {
        System.out.print("Enter 1, 2, 3, 4 or 5 ... ");
        String choice = scan.next();
        switch (choice) {
            case "1" -> addPatient();
            case "2" -> loginPatient();
            case "3" -> loginDoctor();
            case "4" -> loginChief();
            case "5" -> {
                System.out.println("Good bye!");
                //-------------------------------------- TODO ----> saving the data to a files
            }
            default -> {
                System.out.println("Invalid input. Try again!");
                enterChoice();
            }
        }
    }
    */

}
