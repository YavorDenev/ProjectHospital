import java.util.ArrayList;
import java.util.Scanner;

public class Anonymous extends User{
    //fields
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public static String userType = "Anonymous"; //First word in arrayListUserRights.csv file

    Anonymous(){

        this.firstName = "";
        this.lastName = "";
    }

    public static User registerAsNewPatient(){
        Scanner scn = new Scanner(System.in);
        int lastUsedID = 0;
        for (Patient p : DBase.patients){
            if (p.id> lastUsedID) lastUsedID = p.id;
        }

        System.out.print("Enter your first name:");
        String firstName = CheckInputData.inputAlphabeticalNonSpacesString();
        System.out.print("Enter your last name:");
        String lastName = CheckInputData.inputAlphabeticalNonSpacesString();

        System.out.println("Enter your age:");
        int age = CheckInputData.inputPositiveInteger();

        System.out.println("sex?");
        System.out.println("1) male");
        System.out.println("2) female");
        System.out.println("3) unknown");
        int sexChoice = scn.nextInt();

        System.out.println("*** Confirm your operation!***");

        int finalChoice = 0;
        while(finalChoice!=1 && finalChoice!=2){
            System.out.println("1) Reject operation");
            System.out.println("2) Finish operation");
            finalChoice = CheckInputData.inputPositiveInteger();
        }

        if (finalChoice==1){
            return new Anonymous();
        }

        Patient tmpPatient = new Patient();
        tmpPatient.id = lastUsedID+1;
        tmpPatient.firstName = firstName;
        tmpPatient.lastName = lastName;
        tmpPatient.age = age;

        switch (sexChoice){
            case 1 -> tmpPatient.sex = "male";
            case 2 -> tmpPatient.sex = "female";
            default -> tmpPatient.sex = "unknown";
        }

        //Put Object in ArrayList and MAP
        DBase.patients.add(tmpPatient);
        DBase.patientsMap.put(tmpPatient.id,tmpPatient.firstName + " "
                + tmpPatient.lastName + " " + tmpPatient.age + "y "
                + tmpPatient.sex + " (id:"+tmpPatient.id+")" );

        return tmpPatient;
    }
}
