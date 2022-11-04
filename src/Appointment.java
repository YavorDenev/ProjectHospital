public class Appointment {
    private int id;
    private int patientId;
    private String examination;
    private String date;
    private String time;
    private int doctorId;

    public int getId() {
        return id;
    }
    public int getPatientId() {
        return patientId;
    }
    public String getExamination() {
        return examination;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public int getDoctorId() {
        return doctorId;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public Appointment(int id, int patientId, String examination, String date, String time, int doctorId) {
        id = id;
        this.patientId = patientId;
        this.examination = examination;
        this.date = date;
        this.time = time;
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "Id=" + id +
                ", patientId=" + patientId +
                ", examination='" + examination + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", doctorId=" + doctorId +
                '}';
    }
}
