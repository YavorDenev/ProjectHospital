import java.util.ArrayList;

public class Boss extends User{
    //fields
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public static String keyWordForClassAllowedActions = "Boss"; //First word in arrayListUserRights.csv file

    public Boss(String firstName, String lastName, int age, String sex) {
        super(firstName, lastName, age, sex);
    }

    public void removeAppointment() {
        System.out.println("Enter appointment Id to remove ...");
        int idToRemove = CheckInputData.inputPositiveInteger();
        boolean f = true;
        for (Appointment app: DBase.appointments) {
            if (app.id==idToRemove) {
                DBase.appointments.remove(app);
                f = false;
                break;
            }
        }
        if (f) System.out.println("There is no appointment with such an ID");
    }

    public void addDoctor(String firstName, String lastName, int age, String sex, String speciality) {
        DBase.doctors.add(new Doctor(firstName, lastName, age, sex, speciality));
    }

    public void makeDoctorInactive() {
        System.out.println("Enter doctor ID to hide ...");
        int idToHide = CheckInputData.inputPositiveInteger();
        boolean f = true;
        for (Doctor d: DBase.doctors) {
            if (d.id==idToHide) {
                d.isHidden = true;
                f = false;
                break;
            }
        }
        if (f) System.out.println("There is no doctor with such an ID");
    }

    public void addSpeciality(String name) {
        DBase.specialities.add(new Speciality(name));
    }

    public void makeSpecialityInactive() {
        System.out.println("Enter speciality ID to hide ...");
        int idToHide = CheckInputData.inputPositiveInteger();
        boolean f = true;
        for (Speciality s: DBase.specialities) {
            if (s.id==idToHide) {
                s.isHidden = true;
                f = false;
                break;
            }
        }
        if (f) System.out.println("There is no speciality with such an ID");
    }

}
