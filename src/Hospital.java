public abstract class Hospital {


// ---------------------common-------------------------------------------------

    public static void showDoctors() {                              //------------- allowedActions[4]
        System.out.println("----------------  list of doctors  --------------------");
        for (Doctor d: DBase.doctors) {System.out.println(d);}
    }

    public static void showPatients() {                             //------------- allowedActions[5]
        System.out.println("----------------  list of patients  --------------------");
        for (Patient p: DBase.patients) {System.out.println(p);}
    }

    public static void showAppointmentsByPatientId(int patientId) {
        for (Appointment app: DBase.appointments) {
            if (app.patientID==patientId) System.out.println(app);
        }
    }

    public static void showPatientsByDocNames(String firstName, String lastName) {
        boolean f = true;
        for (Doctor doc: DBase.doctors) {
            if (doc.firstName.equals(firstName) && doc.lastName.equals(lastName)) {
                System.out.println("--------  doctor's " + firstName + " " + lastName + " ID: " + doc.id + " patient sheet  --------");
                doc.showMyPatients();
                f = false;
                break;
            }
        }
        if (f) System.out.println("There is no doctor with such names.");
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
