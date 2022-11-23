import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DoctorTest {

    @AfterEach
    public void cleanLists(){
        DBase.appointments = new ArrayList<>();
        DBase.doctors = new ArrayList<>();
    }

    @Test
    public void testRemoveDocAppointmentWhenAppIdExistsAndBelongsToThisDoctor() {
        Read.getAppointmentsFromFile("appointments.txt");
        Doctor doctor = new Doctor();
        doctor.setId(DBase.appointments.get(0).getDoctorID());

        int idBefore = DBase.appointments.get(0).getId();
        int sizeBefore = DBase.appointments.size();
        doctor.removeDocAppointment(idBefore);
        int idAfter = DBase.appointments.get(0).getId();
        int sizeAfter = DBase.appointments.size();

        assertNotEquals(idBefore, idAfter);
        assertEquals(1, sizeBefore - sizeAfter);
    }

    @Test
    public void testRemoveDocAppointmentWhenAppIdExistsButBelongsToAnotherDoctor() {
        Read.getAppointmentsFromFile("appointments.txt");
        Doctor doctor = new Doctor();
        doctor.setId(DBase.appointments.get(0).getDoctorID() + 1);

        int idBefore = DBase.appointments.get(0).getId();
        int sizeBefore = DBase.appointments.size();
        doctor.removeDocAppointment(idBefore);
        int idAfter = DBase.appointments.get(0).getId();
        int sizeAfter = DBase.appointments.size();

        assertEquals(idBefore, idAfter);
        assertEquals(0, sizeBefore - sizeAfter);
    }

    @Test
    public void testRemoveDocAppointmentWhenAppIdNotExists() {
        Read.getAppointmentsFromFile("appointments.txt");
        Doctor doctor = new Doctor();

        int maxId = DBase.appointments.get(0).getId();;
        for (Appointment a: DBase.appointments) {
            if (maxId < a.getId()) maxId = a.getId();
        }
        int idToRemove = maxId + 1;
        int before = DBase.appointments.size();
        doctor.removeDocAppointment(idToRemove);
        int after = DBase.appointments.size();

        assertEquals(before, after);
    }

    @Test
    public void testGenerateSDoctorId() {
        DBase.doctors = new ArrayList<>();
        DBase.doctors.add(new Doctor("zzz", "vvv", 55, "m", "nnn"));
        int id = 555;
        DBase.doctors.get(0).setId(id);
        Doctor doctor = new Doctor("aaa", "bbb", 44, "m", "mmm");
        int expected = id + 1;
        int actual = doctor.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateDoctorIdWhenDoctorsIsEmpty() {
        DBase.doctors = new ArrayList<>();
        Doctor doctor = new Doctor("aaa", "bbb", 99, "f", "xxxx");
        int expected = 1;
        int actual = doctor.getId();
        assertEquals(expected, actual);
    }

}
