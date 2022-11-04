public abstract class ReadWrite {


    public static void readDoctors() {
        //  ------------------------------------- TODO
    }

    public static void readPatients() {
        //  ------------------------------------- TODO
    }

    public static void readAppointments() {

        //  ---------------------------------------------------- TODO     - add appointments to list Hospital.appointments

        for (Doctor doc: Hospital.doctors) { doc.readAppointments(); }      // add appointments to list Doctor.doctorApts
        for (Patient pat: Hospital.patients) { pat.readAppointments(); }    // add appointments to list Patient.patientApts
    }

    public static void writeDoctors() {
        //  ---------------------------------------------------- TODO
    }

    public static void writePatients() {
        //  ---------------------------------------------------- TODO
    }

    public static void writeAppointments() {
        //  ---------------------------------------------------- TODO
    }


}
