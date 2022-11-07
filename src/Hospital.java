public abstract class Hospital {


// ---------------------common-------------------------------------------------

    public static void showDoctors() {//------------- allowedActions[4]
        System.out.println("----------------  list of doctors  --------------------");
        for (Doctor d: DBase.doctors) {System.out.println(d);}
    }

    public static void showPatients() {                          //------------- allowedActions[5]
        System.out.println("----------------  list of patients  --------------------");
        for (Patient p: DBase.patients) {System.out.println(p);}
    }

    public static void showAppointmentsByPatientId(int patientId) {
        for (Appointment app: DBase.appointments) {
            if (app.patientID==patientId) System.out.println(app);
        }
    }

    public static void showPatientsByDoctor(int docId) {
        String firstName = "";
        String lastName = "";
        for (Doctor d: DBase.doctors) {
            if (d.id==docId) {
                firstName = d.firstName;
                lastName = d.lastName;
                break;
            }
        }
        System.out.println("------------  doctor's " + firstName + " " + lastName + " patient sheet  -----------");
        //for (Appointment app: DBase.appointments) {
            //if (app.doctorID==docId) {
                // ---------------------------------------------------- TODO
            //}
        //}
    }

    public static void showPatientsBySpecialty(String specialty) {
        System.out.println("------------  list of patients by specialty " + specialty + "  --------------");
        //for (Doctor doc: DBase.doctors) {
            //if (doc.speciality.equals(specialty)) {
                //for (Patient p: DBase.patients) {
                    // ---------------------------------------------------- TODO
                //}
            //}
        //}
    }

    public static void showPatientsByDate(String date) {
        System.out.println("------------  list of patients by date " + date + "  --------------");

        // ---------------------------------------------------- TODO
    }


}
