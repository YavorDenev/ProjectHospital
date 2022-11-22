import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentTest {

    @Test
    public void testGetPatientNames() {
        ReadWrite.getAppointmentsFromFile("real_appointments.txt");
        ReadWrite.getPatientsFromFile("real_patients.txt");
        Appointment app = DBase.appointments.get(0);
        String expected = "Anna-Maria, Alexandrova";
        String actual = app.getPatientNames();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPatientNamesWhenPatientIdIdWrong() {
        ReadWrite.getPatientsFromFile("real_patients.txt");
        Appointment app = new Appointment();
        app.patientID = -1;
        String expected = "";
        String actual = app.getPatientNames();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDateTimeComparingKey() {
        Appointment app = new Appointment();
        app.setDate("12-12-2022");
        app.setTime(1030);
        long expected = 202212121030L;
        long actual = app.getDateTimeComparingKey();
        assertEquals(expected, actual);
    }




    @Test
    public void testGenerateAppId() {
        DBase.appointments = new ArrayList<>();
        DBase.appointments.add(new Appointment());
        DBase.appointments.get(0).id = 1000;
        Appointment app = new Appointment(0,0,"x","x", 0);
        int expected = 1001;
        int actual = app.getId();
        assertEquals(expected, actual);

        DBase.appointments = new ArrayList<>();
    }


    @Test
    public void testGenerateAppIdWhenAppointmentsIsEmpty() {
        DBase.appointments = new ArrayList<>();
        Appointment app = new Appointment(0,0,"x","x", 0);
        int expected = 1;
        int actual = app.getId();
        assertEquals(expected, actual);

        DBase.appointments = new ArrayList<>();
    }

}
