import java.util.Scanner;

public abstract class Logins {

    static Scanner scan = new Scanner(System.in);


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

    private static void addPatient() { }    // ------------- TODO

    private static void loginPatient() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter you name...;");
        String name = sc.nextLine();
        System.out.println("Enter you Id...;");
        int id = Inputs.inputPositiveInteger();
        if (     true         ) {                  // --------------------- TODO - verify
            System.out.println("Welcome!");
            Menus.showPatientMenu(id);
        } else {
            System.out.println("Invalid Username or Id!");
            Menus.showMainMenu();
        }
    }



    private static void loginDoctor() { }     // ------------- TODO

    private static void loginChief() { }     // ------------- TODO


}
