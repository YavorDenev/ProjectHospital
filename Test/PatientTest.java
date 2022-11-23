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

    @Test
    public void testRemoveMyAppointmentWhenAppIdExistsAndBelongsToThisPatient() {
        Read.getAppointmentsFromFile("appointments.txt");
        Patient p = new Patient();
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
        Patient p = new Patient();
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
        Patient p = new Patient();

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
        Patient p = new Patient();

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





}
