import java.io.FileOutputStream;
import java.io.PrintStream;

public abstract class Write {

    public static void writeAppointmentsData(String filename){
        String[] appArray = new String[DBase.appointments.size()+1];
        appArray[0] = "appointment_id, patient_id, type_of_examination, date, time, doctor_id+\n";
        String nl = "\n";

        for (int i=0;i<DBase.appointments.size();i++){
            Appointment ap = DBase.appointments.get(i);
            appArray[i+1] = ap.id + "," + ap.patientID + "," + ap.typeOfExamination + ","
                    + ap.date + "," + ap.time + "," + ap.doctorID;
            if (i+1 < DBase.appointments.size()) appArray[i+1] += nl; //last line must be without enter
        }

        writeInFile(appArray,filename);
    }

    public static void writeDoctorsData(String filename){
        String[] docArray = new String[DBase.doctors.size()+1];
        docArray[0] = "doctor_id,first_name,last_name,speciality,age,sex\n";
        String nl = "\n";

        for (int i=0;i<DBase.doctors.size();i++){
            Doctor doc = DBase.doctors.get(i);
            docArray[i+1] = doc.id + "," + doc.firstName + "," + doc.lastName + ","
                    + doc.speciality + "," + doc.age + "," + doc.sex;
            if (i+1 < DBase.doctors.size()) docArray[i+1] += nl; //last line must be without enter
        }

        writeInFile(docArray,filename);
    }

    public static void writePatientsData(String filename){
        String[] patArray = new String[DBase.patients.size()+1];
        patArray[0] = "patient_id,first_name,last_name,age,sex\n";
        String nl = "\n";

        for (int i=0;i<DBase.patients.size();i++){
            Patient p = DBase.patients.get(i);
            patArray[i+1] = p.id + "," + p.firstName + "," + p.lastName + ","
                    + p.age + "," + p.sex;
            if (i+1 < DBase.patients.size()) patArray[i+1] += nl; //last line must be without enter
        }

        writeInFile(patArray,filename);
    }

    /*
    public static void writeSpecialitiesData(String filename){
        String[] spArray = new String[DBase.specialities.size()+1];
        spArray[0] = "speciality_id,name,is_hidden\n";
        String nl = "\n";

        for (int i=0;i<DBase.specialities.size();i++){
            Speciality sp = DBase.specialities.get(i);
            String isHidd = (sp.isHidden) ? "1" : "0";
            spArray[i+1] = sp.id + "," + sp.name + "," + isHidd;
            if (i+1 < DBase.specialities.size()) spArray[i+1] += nl; //last line must be without enter
        }
        writeInFile(spArray,filename);
    }
     */

    public static void writeClassAllowedActionsData(String filename){

        // ---------------------------------------------------------------TODO

    }

    private static void writeInFile(String[] array, String fileName) {
        try{
            PrintStream ps1 = new PrintStream(new FileOutputStream(fileName, false));
            for (int i=0;i<array.length; i++) {
                ps1.print(array[i]);
            }
        }
        catch(Exception exc) {
            System.out.println(exc.getMessage());  //показва от какво е станало
        }
    }

}
