import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public abstract class Write {

    public static void writeAppointmentsData(){
        String[] appArray = new String[DBase.appointments.size()+1];
        appArray[0] = "appointment_id, patient_id, type_of_examination, date, time, doctor_id+\n";

        String nl = "\n";
        for (int i=0;i<DBase.appointments.size();i++){
            Appointment ap = DBase.appointments.get(i);

            appArray[i+1] = ap.id + "," + ap.patientID + "," + ap.typeOfExamination + ","
                    + ap.date + "," + ap.time + "," + ap.doctorID;
            if (i+1 < DBase.appointments.size()) appArray[i+1] += nl; //last line must be without enter

        }

        writeInFile(appArray,"appointments.txt");
    }


    public static void writeInFile(String[] array, String fileName) {

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
