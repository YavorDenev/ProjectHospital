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
