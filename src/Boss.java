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

    public void removeAppointment(int appIdToRemove) {
        boolean f = true;
        for (Appointment app: DBase.appointments) {
            if (app.id==appIdToRemove) {
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

    public void changeDoctorVisibility(int idToChangeVis) {
        boolean f = true;
        for (Doctor d: DBase.doctors) {
            if (d.id==idToChangeVis) {
                d.isHidden = !d.isHidden;
                f = false;
                break;
            }
        }
        if (f) System.out.println("There is no doctor with such an ID");
    }

    public void changeSpecVisibility(int idToChangeVis) {
        boolean f = true;
        for (Speciality s: DBase.specialities) {
            if (s.id==idToChangeVis) {
                s.isHidden = !s.isHidden;
                f = false;
                break;
            }
        }
        if (f) System.out.println("There is no speciality with such an ID");
    }

}
