import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

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

    public void showDocAppointments() {
        showDocAppointments(this.id);
    }

    public static void showDocAppointments(int docId) {
        showDocHeader(docId, "");
        for (Appointment app: DBase.appointments) {
            if (app.doctorID==docId) System.out.println(app);
        }
    }

    public void showDocApptsByDateTime(String upDown) {
        showDocApptsByDateTime(this.id, upDown);
    }

    public static void showDocApptsByDateTime(int docId, String upDown) {
        universalAppByDocIDComparator(docId,upDown,1); //1 for DateTime
    }

    public void showDocApptsByPatientNames(String upDown) {
        showDocApptsByPatientNames(this.id, upDown);
    }

    public static void showDocApptsByPatientNames(int docId, String upDown) {
        universalAppByDocIDComparator(docId,upDown,2); //2 for Patient Names
    }

    public void showDocApptsByPatientId(String upDown) {
        showDocApptsByPatientId(this.id, upDown);
    }

    public static void showDocApptsByPatientId(int docId, String upDown) {
        universalAppByDocIDComparator(docId,upDown,3); //3 for PatientID
    }

    public static void universalAppByDocIDComparator(int docId, String upDown, int compareType){
        //compareType = 1 byDateTime
        //compareType = 2 byPatientName
        //compareType = 3 byPatientID

        ArrayList<Appointment> docAppts = new ArrayList<>();

        int sortDirection = 0;
        String notice = "(appointments ";

        if (upDown.equalsIgnoreCase("up")) {
            sortDirection = 1; notice += "up by";
        }
        if (upDown.equalsIgnoreCase("down")) {
            sortDirection = 2; notice += "down by";
        }

        switch (compareType) {
            case 1 -> notice += " date time)";
            case 2 -> notice += " patient name)";
            case 3 -> notice += " patient id)";
            default -> {
            }
        }

        showDocHeader(docId, notice);

        for (Appointment app: DBase.appointments) {
            if (app.doctorID==docId) docAppts.add(app);
        }
        if (!docAppts.isEmpty()) {
            if (sortDirection==1){
                switch (compareType) {
                    case 1 -> docAppts.sort(Comparator.comparing(Appointment::getDateTimeComparingKey));
                    case 2 -> docAppts.sort(Comparator.comparing(Appointment::getPatientNames));
                    case 3 -> docAppts.sort(Comparator.comparing(Appointment::getPatientID));
                }
            } else if (sortDirection==2) {
                switch(compareType){
                    case 1 -> docAppts.sort(Comparator.comparing(Appointment::getDateTimeComparingKey).reversed());
                    case 2 -> docAppts.sort(Comparator.comparing(Appointment::getPatientNames).reversed());
                    case 3 -> docAppts.sort(Comparator.comparing(Appointment::getPatientID).reversed());
                }
            }
            for (Appointment app: docAppts) { System.out.println(app); }
        }
        else System.out.println("The doctor doesn't have any appointments.");
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
        //for good looking in sort orders
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
