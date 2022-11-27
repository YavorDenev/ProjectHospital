import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppointmentTest {

    @AfterEach
    public void cleanLists(){
        DBase.appointments = new ArrayList<>();
        DBase.patients = new ArrayList<>();
    }

    @Test
    public void testGetPatientNames() {
        Read.getAppointmentsFromFile("appointments.txt");
        Read.getPatientsFromFile("patients.txt");
        Appointment app = DBase.appointments.get(0);
        String expected = "anna-maria alexandrova";
        String actual = app.getPatientNames();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPatientNamesWhenPatientIdIdWrong() {
        Read.getPatientsFromFile("patients.txt");
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
    public void testSortApptsByCriteriaPatientIdDown() {
        Read.getAppointmentsFromFile("appointments.txt");

        Appointment.sortApptsByCriteria(DBase.appointments, "DoWn", SortCriteria.PATIENT_ID);

        boolean flag = true;
        for (int i = 0; i < DBase.appointments.size()-2; i++) {
            if (DBase.appointments.get(i).getPatientID() < DBase.appointments.get(i+1).getPatientID()) {
                flag = false;
                break;
            }
        }

        assertTrue(flag);
    }

    @Test
    public void testSortApptsByCriteriaDateTimeUp() {
        Read.getAppointmentsFromFile("appointments.txt");

        Appointment.sortApptsByCriteria(DBase.appointments, "UP", SortCriteria.DATE_TIME);

        boolean flag = true;
        for (int i = 0; i < DBase.appointments.size()-2; i++) {
            if (DBase.appointments.get(i).getDateTimeComparingKey() > DBase.appointments.get(i+1).getDateTimeComparingKey()) {
                flag = false;
                break;
            }
        }

        assertTrue(flag);
    }

    @Test
    public void testSortApptsByCriteriaPatientNamesDown() {
        Read.getAppointmentsFromFile("appointments.txt");

        Appointment.sortApptsByCriteria(DBase.appointments, "Down", SortCriteria.PATIENT_NAMES);

        boolean flag = true;
        for (int i = 0; i < DBase.appointments.size()-2; i++) {
            String names1 = DBase.appointments.get(i).getPatientNames();
            String names2 = DBase.appointments.get(i+1).getPatientNames();
            if (names1.compareTo(names2)<0) {
                flag = false;
                break;
            }
        }

        assertTrue(flag);
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
    }

    @Test
    public void testGenerateAppIdWhenAppointmentsIsEmpty() {
        DBase.appointments = new ArrayList<>();
        Appointment app = new Appointment(0,0,"x","x", 0);
        int expected = 1;
        int actual = app.getId();
        assertEquals(expected, actual);
    }

}
