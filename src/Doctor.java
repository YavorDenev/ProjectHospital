import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Doctor extends User {

    public int id;
    public boolean isHidden;
    public String speciality;
    static ArrayList<Integer> allowedActions = new ArrayList<>();
    public static String userType = "Doctor"; //First word in arrayListUserRights.csv file

    public Doctor() {}

    public Doctor(String firstName, String lastName, int age, String sex, String speciality) {
        super(firstName, lastName, age, sex);
        this.id = generateDoctorId();
        this.isHidden = false;
        this.speciality = speciality;
    }

    private int generateDoctorId() {
        int id = 0;
        for (Doctor d: DBase.doctors) {
            if (id < d.id) id = d.id;
        }
        return id + 1;
    }

    public void showDocApptsSortedByDateTime(String upDown) {
        showDocApptsSortedByDateTime(this.id, upDown);
    }

    public static void showDocApptsSortedByDateTime(int docId, String upDown) {
        showSortedDocApptsByCriteria(docId,upDown,"DateTime");
    }

    public void showDocApptsSortedByPatientNames(String upDown) {
        showDocApptsSortedByPatientNames(this.id, upDown);
    }

    public static void showDocApptsSortedByPatientNames(int docId, String upDown) {
        showSortedDocApptsByCriteria(docId,upDown,"PatientNames");
    }

    public void showDocApptsSortedByPatientId(String upDown) {
        showDocApptsSortedByPatientId(this.id, upDown);
    }

    public static void showDocApptsSortedByPatientId(int docId, String upDown) {
        showSortedDocApptsByCriteria(docId,upDown,"PatientID");
    }

    private static void showSortedDocApptsByCriteria(int docId, String upDown, String criterion){
        String notice = "(appointments ";
        switch (upDown.toLowerCase()) {
            case "up" -> notice += "up by";
            case "down" -> notice += "down by";
        }
        switch (criterion) {
            case "DateTime" -> notice += " date time)";
            case "PatientNames" -> notice += " patient name)";
            case "PatientID" -> notice += " patient id)";
        }
        showDocHeader(docId, notice);

        ArrayList<Appointment> docAppts = new ArrayList<>();
        for (Appointment app: DBase.appointments) {
            if (app.doctorID==docId) docAppts.add(app);
        }
        if (!docAppts.isEmpty()) {
            sortApptsByCriteria(docAppts, upDown, criterion);
            for (Appointment app: docAppts) { System.out.println(app); }
        } else System.out.println("The doctor doesn't have any appointments.");
    }

    private static void sortApptsByCriteria(List<Appointment> docAppts, String upDown, String criterion){
            switch (criterion) {
                case "DateTime" -> docAppts.sort(Comparator.comparing(Appointment::getDateTimeComparingKey));
                case "PatientNames" -> docAppts.sort(Comparator.comparing(Appointment::getPatientNames));
                case "PatientID" -> docAppts.sort(Comparator.comparing(Appointment::getPatientID));
            }
        if (upDown.equalsIgnoreCase("down")) Collections.reverse(docAppts);
    }

    public void removeDocAppointment() {
        System.out.println("Enter appointment Id to remove ...");
        int idToRemove = CheckInputData.inputPositiveInteger();
        int index = getDocAppointmentIndex(idToRemove);
        if (index >= 0) {
            DBase.appointments.remove(index);
        }
        else System.out.println("You do not have an appointment with such an ID");
    }

    private int getDocAppointmentIndex(int idToRemove) {
        for (Appointment app: DBase.appointments) {
            if (app.id==idToRemove && app.doctorID==this.id) {
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
        String blueColor = "\033[1;32m";
        String resetColor = "\033[0m";

        System.out.println("\n"+ blueColor +"============== Doctor " + DBase.doctorsMap.get(docId).toString50() +
                " " + notice + resetColor);
    }

}
