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

    public static User registerAsNewPatient(String firstName, String lastName, int age, String sex){
        System.out.println("*** Confirm your operation!***");
        int finalChoice = 0;
        while(finalChoice!=1 && finalChoice!=2){
            System.out.println("1) Reject operation");
            System.out.println("2) Finish operation");
            finalChoice = CheckInputData.inputPositiveInteger();
        }
        if (finalChoice==1){ return new Anonymous(); }

        Patient tmpPatient = new Patient(firstName, lastName, age, sex);

        DBase.patients.add(tmpPatient);         // ----------------------- Put Object in ArrayList and MAP
        DBase.patientsMap.put(tmpPatient.id,tmpPatient.firstName + " "
                + tmpPatient.lastName + " " + tmpPatient.age + "y "
                + tmpPatient.sex + " (id:"+tmpPatient.id+")" );

        return tmpPatient;
    }
}
