import java.util.ArrayList;

public abstract class TestKatev {

    public static void doTests(){

        DBase.patients.get(0).showAppointments();

        //check reading specialityes

        System.out.println("\n ======= SPECIALITYES ======");
        for (Specialities s : DBase.specialities) {System.out.println(s);}
        /*
        for (int i=0;i<Db.specialities.length;i++) {
            System.out.println(Db.specialities[i].id+" "+Db.specialities[i].name+" "+Db.specialities[i].isHidden);
        }
         */

        //check doctors
        System.out.println("\n ========= DOCTORS ========");
        for (Doctor d : DBase.doctors) {System.out.println(d);}
        /*
        for (int i=0;i<Db.doctors.length;i++) {
            System.out.println(Db.doctors[i].id + " " + Db.doctors[i].firstName + " " + Db.doctors[i].lastName
                    + " " + Db.doctors[i].speciality + " " +Db.doctors[i].sex + " " + Db.doctors[i].age
                    + " " + Db.doctors[i].isHidden);
        }
         */

        //check patients
        System.out.println("\n ======== PATIENTS =======");
        for (Patient p : DBase.patients) {System.out.println(p);}
        /*
        for (int i=0;i<Db.patients.length;i++) {
            System.out.println(Db.patients[i].id + " " + Db.patients[i].firstName + " " + Db.patients[i].lastName
                    + " " + Db.patients[i].age + " " +Db.patients[i].sex);
        }
         */

        //check appointments
        System.out.println("\n ====== APPOINTMENTS ======");
        for (Appointment a : DBase.appointments) {System.out.println(a);}
        /*
        for (int i=0;i<Db.appointments.length;i++) {
            System.out.println(Db.appointments[i].id + " patID:" + Db.appointments[i].patientID + " type:" + Db.appointments[i].typeOfExamination
                    + " date:" + Db.appointments[i].date + " time:" +Db.appointments[i].time + " docID:"+ Db.appointments[i].doctorID);
        }
         */

        //Check rights
        System.out.println("\n====== USER ALLOWED ACTIONS (Beta) =====\n");
        System.out.println("����� �� ANONYMOUS ========================= \n" + showArrayList(Anonymous.allowedActions));
        System.out.println("����� �� PATIENTS ========================= \n" + showArrayList(Patient.allowedActions));
        System.out.println("����� �� DOCTORS ========================= \n" + showArrayList(Doctor.allowedActions));
        System.out.println("����� �� BOSS ========================= \n" + showArrayList(Boss.allowedActions));

        //Check currentUser data
        try {
            Db.currentUser = Db.doctors[0];
            System.out.println(Db.currentUser.getClass().getDeclaredField("keyWordForClassAllowedActions").get(1) + " "
                    + Db.currentUser.firstName + " " + Db.currentUser.lastName + " allowed actions:"
                    + Db.currentUser.getClass().getDeclaredField("allowedActions").get(1));
            Db.currentUser = Db.patients[7];
            System.out.println(Db.currentUser.getClass().getDeclaredField("keyWordForClassAllowedActions").get(1) + " "
                    + Db.currentUser.firstName + " " + Db.currentUser.lastName + " allowed actions:"
                    + Db.currentUser.getClass().getDeclaredField("allowedActions").get(1));
        }
        catch (Exception e){
            //getDeclared i get(1) ������� ��������� //todo katev
        }

    }

    private static String showArrayList(ArrayList<Integer> al){
        String sum = "";
        for (int x: al){
            sum += Db.allowedActions[x] + "\n";
        }
        return sum;
    }

}
