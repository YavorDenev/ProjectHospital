public class Appointment {
    public int id;
    public int patientID;
    public int doctorID;
    public String typeOfExamination;
    public String date;
    public int time;

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "" + id +
                " patientId=" + patientID +
                " type=" + typeOfExamination +
                " date=" + date +
                " time=" + time +
                " doctorId=" + doctorID ;
    }
}
