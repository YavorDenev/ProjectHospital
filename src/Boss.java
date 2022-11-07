import java.util.ArrayList;

public class Boss extends User{
    //fields
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public static String keyWordForClassAllowedActions = "Boss"; //First word in arrayListUserRights.csv file

    public void removeAppointment() {
        System.out.println("Enter appointment Id to remove ...");
        int idToRemove = CheckInputData.inputPositiveInteger();
        for (Appointment app: DBase.appointments) {
            if (app.id==idToRemove) {
                DBase.appointments.remove(app);
                break;
            } else System.out.println("There is no appointment with such an ID");
        }
    }

    public void addDoctor() { }     // ------------- TODO

    public void removeDoctor() { }     // ------------- TODO

    public void addSpecialty() { }     // ------------- TODO

    public void removeSpecialty() { }     // ------------- TODO

}
