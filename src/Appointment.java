import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Appointment {
    int id;
    int patientID;
    int doctorID;
    String typeOfExamination;
    String date;
    int time;

    public int getId() {
        return id;
    }

    public int getPatientID() {
        return patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    void setDate(String date) {
        this.date = date;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Appointment() {
    }

    public Appointment(int patientID, int doctorID, String typeOfExamination, String date, int time) {
        this.id = generateAppId();
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.typeOfExamination = typeOfExamination;
        this.date = date;
        this.time = time;
    }

    public Appointment(int patientID, int doctorID, String typeOfExamination, String date, String time) {
        this.id = generateAppId();
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.typeOfExamination = typeOfExamination;
        this.date = date;
        String[] resTime = time.split(":");
        this.time = 100*Integer.parseInt(resTime[0]) +Integer.parseInt(resTime[1]) ;
    }

    private int generateAppId() {
        int id = 0;
        for (Appointment app: DBase.appointments) {
            if (id < app.id) id = app.id;
        }
        return id + 1;
    }

    public String getPatientNames() {
        for (Patient p: DBase.patients) {
            if (this.patientID==p.id) {
                return p.firstName + ", " + p.lastName;
            }
        }
        return "";
    }

    public long getDateTimeComparingKey() {
        String[] d = this.date.split("-");
        return this.time + 10000 * Long.parseLong(d[2] + d[1] + d[0]);
    }

    public static void sortApptsByCriteria(List<Appointment> appointments, String upDown, SortCriteria criterion){
        switch (criterion) {
            case DATE_TIME -> appointments.sort(Comparator.comparing(Appointment::getDateTimeComparingKey));
            case PATIENT_NAMES -> appointments.sort(Comparator.comparing(Appointment::getPatientNames));
            case PATIENT_ID -> appointments.sort(Comparator.comparing(Appointment::getPatientID));
        }
        if (upDown.equalsIgnoreCase("down")) Collections.reverse(appointments);
    }

    @Override
    public String toString() {
        String leadingZero = (time%100 < 10) ? "0" : "";
        return FunctionsText.leftFrameFixedLengthIn("appID:" + id,12) +
                getColorBySex(DBase.patientsMap.get(patientID)) + //set color
                FunctionsText.leftFrameFixedLengthIn(DBase.patientsMap.get(patientID),44) +
                Colors.RESET +
                FunctionsText.leftFrameFixedLengthIn(typeOfExamination,15) +
                FunctionsText.leftFrameFixedLengthIn(date,14) +
                FunctionsText.leftFrameFixedLengthIn(time/100 + ":" + leadingZero + time%100,8)+
                FunctionsText.leftFrameFixedLengthIn("doctor id:" + doctorID,16) ;
    }

    private String getColorBySex(String sex){
        String[] ms = sex.split(" ");
        try {
            if (ms[3].equals("female")) return Colors.RED;
            if (ms[3].equals("male")) return Colors.CYAN;
            if (ms[3].equals("unknown")) return Colors.BLUE;
        }
        catch (Exception exc) {

        }
        return "";
    }
}
