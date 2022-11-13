import java.awt.*;
import java.sql.Array;
import java.util.Arrays;

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

    public String getTypeOfExamination() {
        return typeOfExamination;
    }

    public String getDate() {
        return date;
    }

    public int getTime() {
        return time;
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
                return p.lastName + ", " + p.firstName;
            }
        }
        return "";
    }

    public long getDateTimeComparingKey() {
        String[] d = this.date.split("-");
        return this.time + 10000 * Long.parseLong(d[2] + d[1] + d[0]);
    }

    @Override
    public String toString() {
        String leadingZero = (time%100 < 10) ? "0" : "";
        return fixLengthIn("appID:" + id,12) +
                fixLengthIn(DBase.patientsMap.get(patientID),40) + //mojem da napravim po pregledna spravkata
                fixLengthIn(typeOfExamination,15) +
                fixLengthIn(date,14) +
                fixLengthIn(time/100 + ":" + leadingZero + time%100,8)+
                fixLengthIn("doctor id:" + doctorID,16) ;
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
    public String toStringOld() {
        String leadingZero = (time%100 < 10) ? "0" : "";
        return "id:" + id +
                ", patient id:" + patientID +
                ", type: " + typeOfExamination +
                ", date: " + date +
                ", time: " + time/100 + ":" + leadingZero + time%100 +
                ", doctor id:" + doctorID ;
    }



}
