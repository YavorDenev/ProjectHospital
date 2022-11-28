import java.util.Scanner;

public class CheckInput {
    private CheckInput(){}

    public static int inputPositiveInteger() {
        Scanner sc = new Scanner(System.in);
        while (!(sc.hasNextInt())) {
            printRedWarning("Invalid input. Try again!");
            sc.nextLine();
        }
        int num = sc.nextInt();
        while (num < 1) {
            printRedWarning("Invalid input number. Try again!");
            num = inputPositiveInteger();
        }
        return num;
    }

    public static int inputMaxInt(int maxInt){
        String msg = Colors.BLUE + "Enter your choice:" + Colors.RESET;
        int choice = 0;
        while (choice<1 || choice>maxInt){
            System.out.print(msg);
            choice = inputPositiveInteger();
            msg = Colors.RED + "Invalid input number. Try again!" + Colors.RESET;
        }

        return choice;
    }

    public static String inputAlphabeticalNonSpacesString() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        boolean isCorrect = true;
        for (int i=0; i<str.length();i++) {
            char c = str.charAt(i);
            if (!Character.isAlphabetic(c) && (c != '-')){
                isCorrect = false;
                break;
            }
        }
        if (!isCorrect) {
            printRedWarning("Please use only a-z, A-Z and '-', without spaces! Try again!");
            str = inputAlphabeticalNonSpacesString();
        }
        return str;
    }

    public static boolean checkDoctorWithThisIDExist(int docID){
        for (Doctor d: DBase.doctors) {
            if (d.id==docID) return true;
        }
        if (docID!=0) printRedWarning("Doctor with this id does not exist!");
        //when docID = 0 this happens before any user choice because while (!true) loop
        return false;
    }

    public static boolean checkIsChosenAppDataTimePatientIsFree(String date, String time, int patID){

        for (Appointment app: DBase.appointments){
            if (app.patientID==patID && app.date.equals(date)){
                String[] resTime = time.split(":");
                int intTime = 100*Integer.parseInt(resTime[0]) +Integer.parseInt(resTime[1]) ;
                if (intTime==app.time){
                    return false;
                }
            }
        }

        return true;
    }

    private static void printRedWarning(String txt){
        String msg = Colors.RED;
        msg += txt + Colors.RESET;
        System.out.println(msg);
    }

}
