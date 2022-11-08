public class Specialities {

    public int id;
    public String name;
    public boolean isHidden;

    @Override
    public String toString() {
        return "" + id + " " + name + " " + isHidden;
    }

    public void showPatients() {
        for (Patient p : DBase.patients) {
            for (Appointment app: DBase.appointments) {

                if (  app.patientID==p.id) {
                    System.out.println(p);
                    break;
                }
            }
        }
        /*
        for (Patient p : DBase.patients) {
            for (Doctor doc: DBase.doctors) {
                for (Appointment app: DBase.appointments) {
                    if (doc.speciality.equals(this.name) && app.doctorID==doc.id && app.patientID==p.id) {
                    System.out.println(p);
                    break;
                    }
                }
            }
        }
         */
    }

}