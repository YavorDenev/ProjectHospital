public abstract class AppointmentActions {
/*
    //Prosto da se zapazqt kato kod
    //mislq da gi napravq direktno prez MENU, kogato patient si zapisva 4as

    public void showMyAppointments() {
        for (Appointment app: Hospital.appointments) {
            if (app.id==this.id) {
                System.out.println(app);
            }
        }
    }



    public void removeAppointment() {
        System.out.println("Enter appointment Id to remove ...");
        int idToRemove = Inputs.inputPositiveInteger();
        int index = getMyAppointmentIndex(idToRemove);
        if (index >= 0) {
            Hospital.appointments.remove(index);
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private int getMyAppointmentIndex(int id) {
        for (Appointment app: Hospital.appointments) {
            if (app.getId()==id && app.getPatientId()==this.id) {
                return Hospital.appointments.indexOf(app);
            }
        }
        return -1;
    }

    public void changeAppointmentsDateTime() {
        System.out.println("Enter appointment Id to change ...");
        int idToChange = Inputs.inputPositiveInteger();
        int index = getMyAppointmentIndex(idToChange);
        if (index >= 0) {
            int doctorId = Hospital.appointments.get(index).getDoctorId();
            String date = chooseNewDate(doctorId);
            String time = chooseNewTime(doctorId, date);
            Hospital.appointments.get(index).setDate(date);
            Hospital.appointments.get(index).setTime(time);
        } else System.out.println("You do not have an appointment with such an ID");
    }

    private String chooseNewDate(int doctorId) { return "dddd"; }      // ------------- TODO

    private String chooseNewTime(int doctorId, String date) { return "tttt"; }      // ------------- TODO

    public void AddAppointment() { }     // ------------- TODO

    public void showDocAppointments(int docId, String date) { }     // ------------- TODO

    private boolean checkIfDateTimeIsFreeByDoc(String date, String time) { return false; }      // ------------- TODO

    private boolean checkIfDateTimeIsFreeByMe(String date, String time) { return false; }      // ------------- TODO

    public void readAppointments() {
        for (Appointment ap: Hospital.appointments) {
            if (ap.getPatientId()==this.id) patientApts.add(ap);
        }
    }
    */
}
