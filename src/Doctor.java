import java.util.ArrayList;

public class Doctor extends User {

    public int id;
    public boolean isHidden;
    public String speciality;
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public static String keyWordForClassAllowedActions = "Doctor"; //First word in arrayListUserRights.csv file

    //public ArrayList<Appointment> doctorApts = new ArrayList<>();

    public void showDocAppointments(int docId) {
        for (Appointment app: DBase.appointments) {
            if (app.doctorID==docId) System.out.println(app);;
        }
    }

    public void showDocAppointments() {
        showDocAppointments(this.id);
    }







    public void removeDocAppointment() {
        System.out.println("Enter appointment Id to remove ...");
        int idToRemove = CheckInputData.inputPositiveInteger();
        int index = getDocAppointmentIndex(idToRemove);
        if (index >= 0) {
            DBase.appointments.remove(index);
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private int getDocAppointmentIndex(int idToRemove) {
        for (Appointment app: DBase.appointments) {
            if (app.id==idToRemove && app.doctorID==this.id) {
                return DBase.appointments.indexOf(app);
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "" + id + " " + firstName + " " + lastName + " " + speciality + " " + isHidden;
    }
}
