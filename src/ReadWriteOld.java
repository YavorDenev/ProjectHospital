import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ReadWriteOld {

    public static void getSpecialityesFromFile(String filename){
        String line = "";
        String splitBy = ",";

        ArrayList<Specialities> specialityes = new ArrayList<>();
        int reckords = 0;

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] words = line.split(splitBy);  // use comma as separator
                ArrayIndexOutOfBoundsException eArrayOB = new ArrayIndexOutOfBoundsException();
                if (words.length!=3) throw eArrayOB;

                if (reckords > 0)
                {
                    //Read current Object
                    Specialities tmpSpec = new Specialities();
                    tmpSpec.id = Integer.parseInt(words[0]); //todo catch Exception
                    tmpSpec.name = words[1];
                    tmpSpec.isHidden = !words[2].equals("0");

                    //Put Object in ArrayList
                    specialityes.add(tmpSpec); //first row is header

                }
                reckords++;
            }
        }
        catch (IOException e1 ) {
            System.out.println("File "+ filename +" not found!"); System.exit(0);
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException e2){
            System.out.println("Incorrect data in file " +filename+ " in row: " + (reckords+1));
            System.exit(0);
        }
        Db.specialities = specialityes.toArray(new Specialities[0]);
    }

    public static void getDoctorsFromFile(String filename) {
        String line = "";
        String splitBy = ",";

        ArrayList<Doctor> doctors = new ArrayList<>();
        int reckords = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] field = line.split(splitBy);
                //  System.out.println("ID=" + field[0] + ", FirstName=" + field[1] + ", Family=" + field[2]
                //          + ", Spec=" + field[3] + " Age:" + field[4] + " sex:" + field[5] + " isHidden:" + field[6]);

                ArrayIndexOutOfBoundsException eArrayOB = new ArrayIndexOutOfBoundsException();
                if (field.length != 7) throw eArrayOB;

                if (reckords > 0) {
                    //Read current Object
                    Doctor tmpDoctor = new Doctor();
                    tmpDoctor.id = Integer.parseInt(field[0]); //todo catch Exception
                    tmpDoctor.firstName = field[1];
                    tmpDoctor.lastName = field[2];
                    tmpDoctor.speciality = field[3];
                    tmpDoctor.age = Integer.parseInt(field[4]);

                    String sx = getSexAndConvertToInteger(field[5]);
                    if (sx.equals("unknown")) {
                        System.out.println("Wrong sex field in " + filename + ". Sex must be marked as f or m");
                    }
                    tmpDoctor.sex = sx;
                    tmpDoctor.isHidden = !field[6].equals("0");

                    //Put Object in ArrayList
                    doctors.add(tmpDoctor); //first row is header

                }
                reckords++;
            }
        } catch (IOException e1) {
            System.out.println("File " + filename + " not found!");
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e2) {
            System.out.println("Incorrect data in file " + filename +" in row: " + (reckords + 1));
            System.exit(0);
        }
        Db.doctors = doctors.toArray(new Doctor[0]);
    }

    private static String getSexAndConvertToInteger(String sex){
        if (sex.toUpperCase().equals("F")) return "female";
        if (sex.toUpperCase().equals("M")) return "male";
        return "unknown"; //Can't recognize sex
    }

    public static void getPatientsFromFile(String filename) {
        String line = "";
        String splitBy = ",";

        ArrayList<Patient> patients = new ArrayList<>();
        int reckords = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] field = line.split(splitBy);  // use comma as separator
                //System.out.println("ID=" + field[0] + ", FirstName=" + field[1] + ", Family=" + field[2]
                //        + " Age:" + field[3] + " sex:" + field[4]);

                ArrayIndexOutOfBoundsException eArrayOB = new ArrayIndexOutOfBoundsException();
                if (field.length != 5) throw eArrayOB;

                if (reckords > 0) {
                    //Read current Object
                    Patient tmpPatient = new Patient();
                    tmpPatient.id = Integer.parseInt(field[0]); //todo catch Exception
                    tmpPatient.firstName = field[1];
                    tmpPatient.lastName = field[2];
                    tmpPatient.age = Integer.parseInt(field[3]);

                    String sx = getSexAndConvertToInteger(field[4]);
                    if (sx.equals("unknown")) {
                        System.out.println("Wrong sex field in " + filename + ". Sex must be marked as f or m");
                    }
                    tmpPatient.sex = sx;

                    //Put Object in ArrayList
                    patients.add(tmpPatient); //first row is header

                }
                reckords++;
            }
        } catch (IOException e1) {
            System.out.println("File " + filename + " not found!");
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e2) {
            System.out.println("Incorrect data in file " + filename + " in row: " + (reckords + 1));
            System.exit(0);
        }

        Db.patients = patients.toArray(new Patient[0]);
    }

    public static void getAppointmentsFromFile(String filename) {
        String line = "";
        String splitBy = ",";

        ArrayList<Appointment> appointments = new ArrayList<>();
        int reckords = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] field = line.split(splitBy);  // use comma as separator
                //System.out.println("appID=" + field[0] + ", patID=" + field[1] + ", typeApp=" + field[2]
                //        + " Date:" + field[3] + " Time:" + field[4])+ " docID:" + field[5]);

                ArrayIndexOutOfBoundsException eArrayOB = new ArrayIndexOutOfBoundsException();
                if (field.length != 6) throw eArrayOB;

                if (reckords > 0) {
                    //Read current Object
                    Appointment tmpAppointment = new Appointment();
                    tmpAppointment.id = Integer.parseInt(field[0]);
                    tmpAppointment.patientID = Integer.parseInt(field[1]); //todo catch Exception
                    tmpAppointment.typeOfExamination = field[2];
                    tmpAppointment.date = field[3];  //todo obrabotka
                    tmpAppointment.time = Integer.parseInt(field[4]); //todo obrabotka
                    tmpAppointment.doctorID = Integer.parseInt(field[5]);

                    //Put Object in ArrayList
                    appointments.add(tmpAppointment); //first row is header
                }
                reckords++;
            }
        } catch (IOException e1) {
            System.out.println("File " + filename + " not found!");
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e2) {
            System.out.println("Incorrect data in file " + filename + " in row: " + (reckords + 1));
            System.exit(0);
        }

        Db.appointments = appointments.toArray(new Appointment[0]);
    }

    public static void getClassAllowedActionsFromFile(String filename){
        String line = "";
        String splitBy = ",";

        int records = 0;

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null)
            {
                ArrayList<Integer> list = new ArrayList<>();
                String[] field = line.split(splitBy);  // use comma as separator
                String kw = field[0];

                if (records>0) {

                    for (int x = 1; x < field.length; x++) {
                        list.add(Integer.parseInt(field[x]));
                    }

                    switch (kw) {
                        case "Anonymous" -> Anonymous.allowedActions = copyArrayList(list);
                        case "Patient" -> Patient.allowedActions = copyArrayList(list);
                        case "Doctor" -> Doctor.allowedActions = copyArrayList(list);
                        case "Boss" -> Boss.allowedActions = copyArrayList(list);
                        default -> System.out.println("Wrong class description key in " +filename);
                    }
                }
                records++;
            }
        }
        catch (IOException e1 ) {
            System.out.println("File "+filename+" not found!"); System.exit(0);
        }
        catch (NumberFormatException e2){
            System.out.println("Incorrect number of right in "+filename+" file ");
            System.exit(0);
        }
    }

    //Break reference. Every user class need from own arraylist
    private static ArrayList<Integer> copyArrayList(ArrayList<Integer> arrL1){
        ArrayList<Integer> newAL = new ArrayList<Integer>(arrL1);
        return newAL;
    }

}