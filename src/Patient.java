import java.util.ArrayList;

public class Patient extends User {

    public static String userType = "Patient"; //First word in arrayListUserRights.csv file
    static ArrayList<Integer> allowedActions = new ArrayList<>();

    int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Patient() { }    //put data from file
    public Patient(String firstName, String lastName, int age, String sex) {
        super(firstName, lastName, age, sex);
        this.id = generatePatientId();
    }

    private int generatePatientId() {
        int id = 0;
        for (Patient p: DBase.patients) {
            if (id < p.id) id = p.id;
        }
        return id + 1;
    }

    public void showMyAppointment() {
        showAppointmentsByPatientId(this.id);
    }

    public static void showAppointmentsByPatientId(int patientId) {
        System.out.println("");
        boolean f = true;
        for (Patient p: DBase.patients) {
            if (p.id==patientId) {
                for (Appointment app: DBase.appointments) {
                    if (app.patientID==patientId) System.out.println(app);
                }
                f = false;
                break;
            }
        }
        if (f) System.out.println("There is no patient with such an ID.");
    }

    public void removeMyAppointment(int appIdToRemove) {
        int index = getMyAppointmentIndex(appIdToRemove);
        if (index >= 0) {
            DBase.appointments.remove(index);
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private int getMyAppointmentIndex(int appId) {
        for (Appointment app: DBase.appointments) {
            if (app.id==appId && app.patientID==this.id) {
                return DBase.appointments.indexOf(app);
            }
        }
        return -1;
    }
    public void changeAppointmentsDateTime(int appIdToChange, String date, int time) {
        int index = getMyAppointmentIndex(appIdToChange);
        if (index >= 0) {
            if (checkIfDateTimeIsFreeByMe(date, time)) {
                DBase.appointments.get(index).setDate(date);
                DBase.appointments.get(index).setTime(time);
            } else System.out.println("You have another appointment booked at the same time.");
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private boolean checkIfDateTimeIsFreeByMe(String date, int time) {
        for (Appointment app: DBase.appointments) {
            if (app.patientID==this.id && app.date.equals(date) && app.time==time) return false;
        }
        return true;
    }

    public void addAppointment(int docId, String typeOfExam, String date, int time) {
        DBase.appointments.add(new Appointment(this.id, docId, typeOfExam, date, time));
    }

    @Override
    public String toString() {

        return fixLengthIn("id:" + id,9) +
                getColorBySex(this.sex) + //set color
                fixLengthIn(this.firstName + " " + this.lastName + " " + this.age + "y " + this.sex,44) +
                Colors.RESET ;
    }

    public String formattedToListOfPatients(){
        String txt="";
        String sexColor = getColorBySex(this.sex);
        txt += fixLengthIn("id:" + id,9) +
                sexColor+
                FunctionsText.leftFrameFixedOnLength(this.firstName + " " + this.lastName,30) +
                Colors.RESET +
                FunctionsText.leftFrameFixedOnLength(" " + this.age + "y ",7) +
                FunctionsText.leftFrameFixedOnLengthColored(this.sex,11,sexColor);

        return txt;
    }

    private String getColorBySex(String sex){
        if (sex.equals("female")) return Colors.RED;
        if (sex.equals("male")) return Colors.CYAN;
        return Colors.BLUE; //for unknown
    }

    private String fixLengthIn(String str, int length)
    {
        if (str.length()==4) str = " " + str; // just for time > right alingment
        String result = " | "+str;
        while(result.length()<length) {
            result += " ";
        }
        return result;
    }

}
