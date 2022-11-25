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

    public static int inputNotNegativeInteger() {
        while (!(scan.hasNextInt())) {
            System.out.println("Invalid input. Try again!");
            scan.next();
        }
        int num = scan.nextInt();
        while (num < 0) {
            System.out.println("Invalid input number. Try again!");
            num = inputPositiveInteger();
        }
        return num;
    }

    public static String inputAlphabeticalNonSpacesString() {
        String str = scan.next();

        boolean isCorrect = true;
        for (int i=0; i<str.length();i++) {
            char c = str.charAt(i);
            if (!Character.isAlphabetic(c)){  //םושמ םו נאבמעט ץלל... //todo
                isCorrect = false;
                break;
            }
        }

        while (!isCorrect) {
            System.out.println("Please use only a-z, A-Z without spaces! Try again!");
            str = inputAlphabeticalNonSpacesString();
        }
        return str;
    }


}
