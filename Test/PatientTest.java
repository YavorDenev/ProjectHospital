import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PatientTest {

    @AfterEach
    public void cleanLists(){
        DBase.appointments = new ArrayList<>();
    }

    Patient p = new Patient();

    @Test
    public void testRemoveMyAppointmentWhenAppIdExistsAndBelongsToThisPatient() {
        Read.getAppointmentsFromFile("appointments.txt");
        p.setId(DBase.appointments.get(0).getPatientID());

        int idBefore = DBase.appointments.get(0).getId();
        int sizeBefore = DBase.appointments.size();
        p.removeMyAppointment(idBefore);
        int idAfter = DBase.appointments.get(0).getId();
        int sizeAfter = DBase.appointments.size();

        assertNotEquals(idBefore, idAfter);
        assertEquals(1, sizeBefore - sizeAfter);
    }

    @Test
    public void testRemoveMyAppointmentWhenAppIdExistsButBelongsToAnotherPatient() {
        Read.getAppointmentsFromFile("appointments.txt");
        p.setId(DBase.appointments.get(0).getPatientID() + 1);

        int idBefore = DBase.appointments.get(0).getId();
        int sizeBefore = DBase.appointments.size();
        p.removeMyAppointment(idBefore);
        int idAfter = DBase.appointments.get(0).getId();
        int sizeAfter = DBase.appointments.size();

        assertEquals(idBefore, idAfter);
        assertEquals(0, sizeBefore - sizeAfter);
    }

    @Test
    public void testRemoveMyAppointmentWhenAppIdNotExists() {
        Read.getAppointmentsFromFile("appointments.txt");

        int maxId = DBase.appointments.get(0).getId();;
        for (Appointment a: DBase.appointments) {
            if (maxId < a.getId()) maxId = a.getId();
        }
        int idToRemove = maxId + 1;
        int before = DBase.appointments.size();
        p.removeMyAppointment(idToRemove);
        int after = DBase.appointments.size();

        assertEquals(before, after);
    }

    @Test
    public void testAddAppointment() {
        Read.getAppointmentsFromFile("appointments.txt");

        int maxAppIdBefore = DBase.appointments.get(0).getId();;
        for (Appointment a: DBase.appointments) {
            if (maxAppIdBefore < a.getId()) maxAppIdBefore = a.getId();
        }

        int sizeBefore = DBase.appointments.size();
        p.addAppointment(11, "xxx", "dddd", 1000);
        int sizeAfter = DBase.appointments.size();

        assertEquals(1, sizeAfter - sizeBefore);
        assertEquals(p.getId(), DBase.appointments.get(sizeAfter-1).getPatientID());
        assertEquals((maxAppIdBefore + 1), DBase.appointments.get(sizeAfter-1).getId());
    }

    @Test
    public void testChangeAppointmentsDateTimeWhenAppIdExistsAndBelongsToThisPatient() {
        Read.getAppointmentsFromFile("appointments.txt");
        p.setId(DBase.appointments.get(0).getPatientID());

        int appIdToChange = DBase.appointments.get(0).getId();
        String date = "date";
        int time = 9999;
        p.changeAppointmentsDateTime(appIdToChange, date, time);

        assertEquals(date, DBase.appointments.get(0).date);
        assertEquals(time, DBase.appointments.get(0).time);
    }

    @Test
    public void testChangeAppointmentsDateTimeWhenAppIdExistsButBelongsToAnotherPatient() {
        Read.getAppointmentsFromFile("appointments.txt");
        p.setId(DBase.appointments.get(0).getPatientID()+1);

        int appIdToChange = DBase.appointments.get(0).getId();
        String date = "date";
        int time = 9999;
        p.changeAppointmentsDateTime(appIdToChange, date, time);

        assertNotEquals(date, DBase.appointments.get(0).date);
        assertNotEquals(time, DBase.appointments.get(0).time);
    }

    @Test
    public void testChangeAppointmentsDateTimeWhenAppIdNotExists() {
        Read.getAppointmentsFromFile("appointments.txt");

        int maxId = DBase.appointments.get(0).getId();;
        for (Appointment a: DBase.appointments) {
            if (maxId < a.getId()) maxId = a.getId();
        }

        int appIdToChange = maxId + 1;
        String date = "date";
        int time = 9999;
        p.changeAppointmentsDateTime(appIdToChange, date, time);

        assertNotEquals(date, DBase.appointments.get(0).date);
        assertNotEquals(time, DBase.appointments.get(0).time);
    }

    @Test
    public void testChangeAppointmentsDateTimeWhenPatientHasAnotherAppointmentAtTheSameDateTime() {
        int patientId = 88;
        String date = "date";
        int time = 9999;

        p.setId(patientId);
        DBase.appointments.add(new Appointment(patientId, 1, "xxx", date, time));
        DBase.appointments.add(new Appointment(patientId, 1, "xxx", "yy", 0));

        p.changeAppointmentsDateTime(DBase.appointments.get(1).getId(), date, time);

        assertNotEquals(date, DBase.appointments.get(1).date);
        assertNotEquals(time, DBase.appointments.get(1).time);
    }

}
