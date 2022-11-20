public abstract class Hospital {

    public static void showDoctors() {
        System.out.println("----------------  list of doctors  --------------------");
        for (Doctor d: DBase.doctors) {System.out.println(d);}
    }

    public static void showSpecialities() {
        System.out.println("----------------  list of specialities  --------------------");
        for (Speciality s: DBase.specialities) {System.out.println(s);}
    }

    public static void showPatients() {
        System.out.println("----------------  list of patients  --------------------");
        for (Patient p: DBase.patients) {System.out.println(p);}
    }

    public static void showPatientsByDocNames(String firstName, String lastName) {
        boolean f = true;
        for (Doctor doc: DBase.doctors) {
            if (doc.firstName.equalsIgnoreCase(firstName) &&
                    doc.lastName.equalsIgnoreCase(lastName)) {
                Doctor.showDocHeader(doc.id," (patients list)");
                //System.out.println("--------  doctor's ID: " + doc.id + " " + doc.firstName + " " + doc.lastName + " patient list  --------");
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
            if (s.name.equalsIgnoreCase(speciality)) {
                System.out.println("------------  list of patients by speciality " + s.name + "  --------------");
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
