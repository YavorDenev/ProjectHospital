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
        Patient tmpPatient = new Patient(firstName, lastName, age, sex);

        DBase.patients.add(tmpPatient);         // ----------------------- Put Object in ArrayList and MAP
        DBase.patientsMap.put(tmpPatient.id,tmpPatient.firstName + " "
                + tmpPatient.lastName + " " + tmpPatient.age + "y "
                + tmpPatient.sex + " (id:"+tmpPatient.id+")" );

        return tmpPatient;
    }
}
