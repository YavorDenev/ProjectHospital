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

    public static void showPatientsByDocNames(String firstName, String lastName) {
        boolean f = true;
        for (Doctor doc: DBase.doctors) {
            if (doc.firstName.toLowerCase().equals(firstName.toLowerCase()) &&
                    doc.lastName.toLowerCase().equals(lastName.toLowerCase())) {
                System.out.println("--------  doctor's " + firstName + " " + lastName + " ID: " + doc.id + " patient sheet  --------");
                doc.showMyPatients();
                f = false;
                break;
            }
        }
        if (f) System.out.println("There is no doctor with such names.");
    }

    public static void showPatientsBySpeciality(String speciality) {
        boolean f = true;
        for (Speciality s: DBase.specialities) {
            if (s.name.toLowerCase().equals(speciality.toLowerCase())) {
                System.out.println("------------  list of patients by speciality " + speciality + "  --------------");
                s.showPatients();
                f = false;
                break;
            }
        }
        if (f) System.out.println("There is no speciality with such name.");
    }

    public static void showPatientsByDate(String date) {
        System.out.println("------------  list of patients by date " + date + "  --------------");
        boolean f = true;
        for (Patient p : DBase.patients) {
            for (Appointment app: DBase.appointments) {
                if (app.date.equals(date) && app.patientID==p.id) {
                    System.out.println(p);
                    f = false;
                    break;
                }
            }
        }
        if (f) System.out.println("There are no patients for this date.");


    }


}
