import java.util.ArrayList;

public class Doctor extends User {
    int id;
    String speciality;
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public static String userType = "Doctor"; //First word in arrayListUserRights.csv file

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Doctor() {}

    public Doctor(String firstName, String lastName, int age, String sex, String speciality) {
        super(firstName, lastName, age, sex);
        this.id = generateDoctorId();
        this.speciality = speciality;
    }

    private int generateDoctorId() {
        int id = 0;
        for (Doctor d: DBase.doctors) {
            if (id < d.id) id = d.id;
        }
        return id + 1;
    }

    public static void showSortedDocApptsByCriteria(int docId, String upDown, SortCriteria criterion){
        String notice = "(appointments " + upDown.toLowerCase() + " by " + criterion.text + ")";
        showDocHeader(docId, notice);

        ArrayList<Appointment> docAppts = new ArrayList<>();
        for (Appointment app: DBase.appointments) {
            if (app.doctorID==docId) docAppts.add(app);
        }
        if (!docAppts.isEmpty()) {
            Appointment.sortApptsByCriteria(docAppts, upDown, criterion);
            for (Appointment app: docAppts) { System.out.println(app); }
        }
        else System.out.println("The doctor doesn't have any appointments.");
    }

    public void removeDocAppointment(int appId) {
        int index = getDocAppointmentIndex(appId);
        if (index >= 0) {
            DBase.appointments.remove(index);
        }
        else System.out.println("You do not have an appointment with such an ID");
    }

    private int getDocAppointmentIndex(int appId) {
        for (Appointment app: DBase.appointments) {
            if (app.id==appId && app.doctorID==this.id) {
                return DBase.appointments.indexOf(app);
            }
        }
        return -1;
    }

    public void showMyPatients() {
        boolean f = true;
        for (Patient p : DBase.patients) {
            for (Appointment app: DBase.appointments) {
                if (app.doctorID==this.id && app.patientID==p.id) {
                    System.out.println(p);
                    f = false;
                    break;
                }
            }
        }
        if (f) System.out.println("The doctor doesn't have any patients.");
    }

    @Override
    public String toString() {
        return firstName +
                " " + lastName + " id:"+id+
                " (" + speciality +")" ;
    }

    public String toString50() {
        //for good look in sorted orders
        String txt = "";
        txt += firstName + " " + lastName + " id:" + id +  " (" + speciality +") " ;

        while (txt.length()<50) txt += "=";
        return txt;

    }

    public static void showDocHeader(int docId, String notice){
        System.out.println("\n"+ Colors.GREEN +"============== Doctor " + DBase.doctorsMap.get(docId).toString50() +
                " " + notice + Colors.RESET);
    }

}
