import java.util.ArrayList;

public class Boss extends User{
    //fields
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public static String userType = "Boss"; //First word in arrayListUserRights.csv file
    public String password;

    public Boss(String firstName, String lastName, int age, String sex, String password) {
        super(firstName, lastName, age, sex);
        this.password = password;
        DBase.bosses.add(this);
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
        boolean f = true;
        for (Doctor d: DBase.doctors) {
            if (d.firstName.equalsIgnoreCase(firstName) && d.lastName.equalsIgnoreCase(lastName) && d.age==age && d.speciality.equalsIgnoreCase(speciality)) {
                f = false;
                break;
            }
        }
        if (f) {
            addSpeciality(speciality);
            DBase.doctors.add(new Doctor(firstName, lastName, age, sex, speciality));
        }
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
        boolean f = true;
        for (Speciality s: DBase.specialities) {
            if (s.name.equalsIgnoreCase(name)) {
                f = false;
                break;
            }
        }
        if (f) DBase.specialities.add(new Speciality(name));
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
