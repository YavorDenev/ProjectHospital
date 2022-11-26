public class Speciality {
    int id;
    String name;
    boolean isHidden;

    public int getId() {
        return id;
    }

    public Speciality() {}

    public Speciality(String name) {
        this.id = generateSpecialityId();
        this.name = name;
        this.isHidden = false;
    }

    private int generateSpecialityId() {
        int id = 0;
        for (Speciality s: DBase.specialities) {
            if (id < s.id) id = s.id;
        }
        return id + 1;
    }

    public void showPatients() {
        String blueColor = "\033[1;32m";
        String resetColor = "\033[0m";

        boolean f = true;
        for (Patient p : DBase.patients) {
            for (Appointment app: DBase.appointments) {
                String sp = getDocSpeciality(app.getDoctorID()).toLowerCase();
                if (sp.equals(this.name.toLowerCase()) && app.getPatientID()==p.id) {
                    System.out.println(p + " " + blueColor +
                            DBase.doctorsMap.get(app.getDoctorID()).toString()+resetColor); //add doctor name
                    f = false;
                    break;
                }
            }
        }
        if (f) System.out.println("There are no patients in this specialty.");
    }

    private String getDocSpeciality(int doctorID) {
        for (Doctor d: DBase.doctors) {
            if (d.id==doctorID) return d.speciality;
        }
        return "";
    }

    @Override
    public String toString() {
        return "id:" +  id + " " + name;
    }

    public String formattedToListOfSpecialities(){
        String txt="";
        txt += FunctionsText.leftFrameFixedOnLength("id:" + id,7);
        txt += FunctionsText.leftFrameFixedOnLengthColored(name, 25,Colors.CYAN);
        return txt;
    }
}