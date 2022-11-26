public abstract class Hospital {

    public static void showDoctors() {
        System.out.println("");
        System.out.println(Colors.BLUE+"======================  list of doctors  ===================="+Colors.RESET);
        for (Doctor d: DBase.doctors) {System.out.println(d.formattedToListOfDoctors());}
    }

    public static void showSpecialities() {
        System.out.println("");
        System.out.println(Colors.BLUE+"===================  list of specialities  =================="+Colors.RESET);
        for (Speciality s: DBase.specialities) {System.out.println(s.formattedToListOfSpecialities());}
    }

    public static void showPatients() {
        System.out.println("");
        System.out.println(Colors.BLUE+"===================  list of patients  ======================"+Colors.RESET);
        for (Patient p: DBase.patients) {System.out.println(p.formattedToListOfPatients());}
    }

    public static void showPatientsByDocNames(String firstName, String lastName) {
        System.out.println("");
        boolean f = true;
        for (Doctor doc: DBase.doctors) {
            if (doc.firstName.equalsIgnoreCase(firstName) &&
                    doc.lastName.equalsIgnoreCase(lastName)) {
                Doctor.showDocHeader(doc.id," (patients list)");
                doc.showMyPatients();
                f = false;
                break;
            }
        }
        if (f) System.out.println(Colors.RED+"There is no doctor with such names."+Colors.RESET);
    }

    public static void showPatientsBySpeciality(String speciality) {
        System.out.println("");
        boolean f = true;
        for (Speciality s: DBase.specialities) {
            if (s.name.equalsIgnoreCase(speciality)) {
                System.out.println(Colors.BLUE +
                        "==================  list of patients by speciality " + s.name + "  =============="
                        + Colors.RESET);
                s.showPatients();
                f = false;
                break;
            }
        }
        if (f) System.out.println(Colors.RED+"There is no speciality with such name."+Colors.RESET);
    }

    public static void showPatientsByDate(String date) {
        System.out.println("");
        System.out.println(Colors.BLUE+"==============  list of patients by date "
                +Colors.RED + date + Colors.RESET + "  ================");
        boolean f = true;
        for (Patient p : DBase.patients) {
            for (Appointment app: DBase.appointments) {
                if (app.date.equals(date) && app.patientID==p.id) {
                    System.out.println(p + " " + Colors.BLUE +
                            DBase.doctorsMap.get(app.getDoctorID()).toString()+Colors.RESET); //add doctor name
                    f = false;
                    break;
                }
            }
        }
        if (f) System.out.println(Colors.RED+"There are no patients for this date."+Colors.RESET);
    }
}
