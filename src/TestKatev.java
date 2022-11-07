import java.util.ArrayList;

public abstract class TestKatev {

    public static void doTests(){

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
        Hospital.showDoctors();
        /*
        for (int i=0;i<Db.doctors.length;i++) {
            System.out.println(Db.doctors[i].id + " " + Db.doctors[i].firstName + " " + Db.doctors[i].lastName
                    + " " + Db.doctors[i].speciality + " " +Db.doctors[i].sex + " " + Db.doctors[i].age
                    + " " + Db.doctors[i].isHidden);
        }
         */

        //check patients
        System.out.println("\n ======== PATIENTS =======");
        Hospital.showPatients();
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
        System.out.println("Права на ANONYMOUS ========================= \n" + showArrayList(Anonymous.allowedActions));
        System.out.println("Права на PATIENTS ========================= \n" + showArrayList(Patient.allowedActions));
        System.out.println("Права на DOCTORS ========================= \n" + showArrayList(Doctor.allowedActions));
        System.out.println("Права на BOSS ========================= \n" + showArrayList(Boss.allowedActions));

        //Check currentUser data
        try {
            DBase.currentUser = DBase.doctors.get(0);
            System.out.println(DBase.currentUser.getClass().getDeclaredField("keyWordForClassAllowedActions").get(1) + " "
                    + DBase.currentUser.firstName + " " + DBase.currentUser.lastName + " allowed actions:"
                    + DBase.currentUser.getClass().getDeclaredField("allowedActions").get(1));
            DBase.currentUser = DBase.patients.get(7);
            System.out.println(DBase.currentUser.getClass().getDeclaredField("keyWordForClassAllowedActions").get(1) + " "
                    + DBase.currentUser.firstName + " " + DBase.currentUser.lastName + " allowed actions:"
                    + DBase.currentUser.getClass().getDeclaredField("allowedActions").get(1));
        }
        catch (Exception e){
            //getDeclared i get(1) хвърлят ексепшъни //todo katev
        }

    }

    private static String showArrayList(ArrayList<Integer> al){
        String sum = "";
        for (int x: al){
            sum += DBase.allowedActions[x] + "\n";
        }
        return sum;
    }

}
