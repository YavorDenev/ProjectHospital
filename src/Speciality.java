public class Speciality {

    public int id;
    public String name;
    public boolean isHidden;

    @Override
    public String toString() {
        return "" + id + " " + name + " " + isHidden;
    }

    public void showPatients() {
        boolean f = true;
        for (Patient p : DBase.patients) {
            for (Appointment app: DBase.appointments) {
                String sp = getDocSpeciality(app.doctorID).toLowerCase();
                if (sp.equals(this.name.toLowerCase()) && app.patientID==p.id) {
                    System.out.println(p);
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

}