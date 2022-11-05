import java.util.Scanner;

public abstract class CheckInputData {

    static Scanner scan = new Scanner(System.in);

    public static int inputPositiveInteger() {
        while (!(scan.hasNextInt())) {
            System.out.println("Invalid input. Try again!");
            scan.next();
        }
        int num = scan.nextInt();
        while (num < 1) {
            System.out.println("Invalid input number. Try again!");
            num = inputPositiveInteger();
        }
        return num;
    }


}
