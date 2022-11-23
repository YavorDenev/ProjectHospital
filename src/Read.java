import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Read {

    public static void getSpecialitiesFromFile(String filename){
        String line = "";
        String splitBy = ",";

        int records = 0;

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] words = line.split(splitBy);  // use comma as separator
                ArrayIndexOutOfBoundsException eArrayOB = new ArrayIndexOutOfBoundsException();
                if (words.length!=3) throw eArrayOB;

                if (records > 0)
                {
                    //Read current Object
                    Speciality tmpSpec = new Speciality();
                    tmpSpec.id = Integer.parseInt(words[0]); //todo catch Exception
                    tmpSpec.name = words[1];
                    tmpSpec.isHidden = !words[2].equals("0");

                    //Put Object in ArrayList
                    DBase.specialities.add(tmpSpec); //first row is header
                }
                records++;
            }
        }
        catch (IOException e1 ) {
            System.out.println("File "+ filename +" not found!"); System.exit(0);
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException e2){
            System.out.println("Incorrect data in file " +filename+ " in row: " + (records+1));
            System.exit(0);
        }

    }

    public static void getDoctorsFromFile(String filename) {
        String line = "";
        String splitBy = ",";

        int records = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] field = line.split(splitBy);

                ArrayIndexOutOfBoundsException eArrayOB = new ArrayIndexOutOfBoundsException();
                if (field.length != 6) throw eArrayOB; //csv must contain 7 fields

                if (records > 0) { //first row is header

                    //Read current Object
                    Doctor tmpDoctor = new Doctor();
                    tmpDoctor.id = Integer.parseInt(field[0]); //todo catch Exception
                    tmpDoctor.firstName = field[1];
                    tmpDoctor.lastName = field[2];
                    tmpDoctor.speciality = field[3];
                    tmpDoctor.age = Integer.parseInt(field[4]);

                    String sx = getSex(field[5]);
                    if (sx.equals("unknown")) {
                        System.out.println("Wrong sex field in " + filename + ". Sex must be marked as f or m");
                    }
                    tmpDoctor.sex = sx;
                    // tmpDoctor.isHidden = !field[6].equals("0");

                    DBase.doctors.add(tmpDoctor);  //Put this doctor in ArrayList
                    DBase.doctorsMap.put(tmpDoctor.id,tmpDoctor); //Put this doctor in Map

                    if (tmpDoctor.id>DBase.maxDoctorID) DBase.maxDoctorID = tmpDoctor.id;
                }
                records++;
            }
        } catch (IOException e1) {
            System.out.println("File " + filename + " not found!");
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e2) {
            System.out.println("Incorrect data in file " + filename +" in row: " + (records + 1));
            System.exit(0);
        }
    }

    public static void getPatientsFromFile(String filename) {
        String line = "";
        String splitBy = ",";
        
        int records = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] field = line.split(splitBy);  // use comma as separator

                ArrayIndexOutOfBoundsException eArrayOB = new ArrayIndexOutOfBoundsException();
                if (field.length != 5) throw eArrayOB;

                if (records > 0) {
                    //Read current Object
                    Patient tmpPatient = new Patient();
                    tmpPatient.id = Integer.parseInt(field[0]); //todo catch Exception
                    tmpPatient.firstName = field[1];
                    tmpPatient.lastName = field[2];
                    tmpPatient.age = Integer.parseInt(field[3]);

                    String sx = getSex(field[4]);
                    if (sx.equals("unknown")) {
                        System.out.println("Wrong sex field in " + filename + ". Sex must be marked as f or m");
                    }
                    tmpPatient.sex = sx;

                    //Put Object in ArrayList
                    DBase.patients.add(tmpPatient); //first row is header
                    DBase.patientsMap.put(tmpPatient.id,tmpPatient.firstName + " "
                            + tmpPatient.lastName + " " + tmpPatient.age + "y "
                            + tmpPatient.sex + " (id:"+tmpPatient.id+")" );
                }
                records++;
            }
        } catch (IOException e1) {
            System.out.println("File " + filename + " not found!");
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e2) {
            System.out.println("Incorrect data in file " + filename + " in row: " + (records + 1));
            System.exit(0);
        }
    }

    public static void getAppointmentsFromFile(String filename) {
        String line = "";
        String splitBy = ",";

        int reckords = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                 //if (line.equals("null")) break;

                 String[] field = line.split(splitBy);  // use comma as separator

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
                    DBase.appointments.add(tmpAppointment); //first row is header
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

        DBase.setActiveDays(); //set DBase.activeDays from appointments records
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

    private static String getSex(String sex){
        if (sex.toUpperCase().equals("F")) return "female";
        if (sex.toUpperCase().equals("M")) return "male";
        return "unknown"; //Can't recognize sex
    }

}
