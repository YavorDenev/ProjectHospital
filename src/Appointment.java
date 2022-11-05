public class Appointment {
    public int id;
    public int patientID;
    public int doctorID;
    public String typeOfExamination;
    public String date;
    public int time;

    @Override
    public String toString() {
        return "Appointment{" +
                "Id=" + id +
                ", patientId=" + patientID +
                ", examination='" + typeOfExamination + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", doctorId=" + doctorID +
                '}';
    }
}
