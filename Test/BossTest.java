import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BossTest {

    @AfterEach
    public void cleanLists(){
        DBase.appointments = new ArrayList<>();
        DBase.patients = new ArrayList<>();
        DBase.doctors = new ArrayList<>();
        DBase.specialities = new ArrayList<>();
    }

    Boss boss = new Boss("ffff", "llll", 50, "f", "abcd");

    @Test
    public void testAddSpecialityWhenIsNew() {
        Read.getSpecialitiesFromFile("specialities.txt");
        String expected = "new speciality";
        int before = DBase.specialities.size();
        Boss.addSpeciality(expected);
        String actual = DBase.specialities.get(DBase.specialities.size()-1).name;
        int after = DBase.specialities.size();
        assertEquals(expected, actual);
        assertNotEquals(before, after);
    }

    @Test
    public void testAddSpecialityWhenAlreadyExists() {
        Read.getSpecialitiesFromFile("specialities.txt");
        int before = DBase.specialities.size();
        Boss.addSpeciality("DerMaTology");
        int after = DBase.specialities.size();
        assertEquals(before, after);
    }

    @Test
    public void testAddDoctorWhenIsNew() {
        Read.getDoctorsFromFile("doctors.txt");

        String firstName = "aaa";
        String lastName = "bbb";
        int age = 33;
        String sex = "m";
        String speciality = "xxxxx";

        int before = DBase.doctors.size();
        boss.addDoctor(firstName, lastName, age, sex, speciality);
        int after = DBase.doctors.size();

        assertNotEquals(before, after);
        assertEquals(DBase.doctors.get(after-1).firstName, firstName);
        assertEquals(DBase.doctors.get(after-1).lastName, lastName);
        assertEquals(DBase.doctors.get(after-1).age, age);
        assertEquals(DBase.doctors.get(after-1).sex, sex);
        assertEquals(DBase.doctors.get(after-1).speciality, speciality);
    }

    @Test
    public void testAddDoctorWhenAlreadyExists() {
        Read.getDoctorsFromFile("doctors.txt");

        String firstName = DBase.doctors.get(0).firstName;
        String lastName = DBase.doctors.get(0).lastName;
        int age = DBase.doctors.get(0).age;
        String sex = DBase.doctors.get(0).sex;
        String speciality = DBase.doctors.get(0).speciality;

        int before = DBase.doctors.size();
        boss.addDoctor(firstName, lastName, age, sex, speciality);
        int after = DBase.doctors.size();

        assertEquals(before, after);
    }

    @Test
    public void testRemoveAppointmentWhenIdExists() {
        Read.getAppointmentsFromFile("appointments.txt");

        int sizeBefore = DBase.appointments.size();
        int idBefore = DBase.appointments.get(0).getId();
        boss.removeAppointment(idBefore);
        int sizeAfter = DBase.appointments.size();
        int idAfter = DBase.appointments.get(0).getId();

        assertNotEquals(idBefore,idAfter);
        assertEquals(1, sizeBefore - sizeAfter);
    }

    @Test
    public void testRemoveAppointmentWhenIdNotExists() {
        Read.getAppointmentsFromFile("appointments.txt");

        int maxId = DBase.appointments.get(0).id;;
        for (Appointment a: DBase.appointments) {
            if (maxId < a.id) maxId = a.id;
        }
        int idToRemove = maxId + 1;
        int before = DBase.appointments.size();
        boss.removeAppointment(idToRemove);
        int after = DBase.appointments.size();

        assertEquals(before, after);
    }

    @Test
    public void testChangeSpecVisibility() {
        Speciality s1 = new Speciality();
        s1.id = 99;
        s1.isHidden = true;

        Speciality s2 = new Speciality();
        s2.id = 88;
        s2.isHidden = false;

        DBase.specialities = List.of(s1,s2);
        boss.changeSpecVisibility(99);
        boss.changeSpecVisibility(88);

        assertFalse(DBase.specialities.get(0).isHidden);
        assertTrue(DBase.specialities.get(1).isHidden);
    }

}
