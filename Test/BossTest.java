import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BossTest {

    Boss boss = new Boss("ffff", "llll", 50, "f", "abcd");

    @Test
    public void testAddSpecialityWhenIsNew() {
        ReadWrite.getSpecialitiesFromFile("specialities.txt");
        String expected = "new speciality";
        int before = DBase.specialities.size();
        boss.addSpeciality(expected);
        String actual = DBase.specialities.get(DBase.specialities.size()-1).name;
        int after = DBase.specialities.size();
        assertEquals(expected, actual);
        assertNotEquals(before, after);
    }

    @Test
    public void testAddSpecialityWhenAlreadyExists() {
        ReadWrite.getSpecialitiesFromFile("specialities.txt");
        int before = DBase.specialities.size();
        boss.addSpeciality("DerMaTology");
        int after = DBase.specialities.size();
        assertEquals(before, after);
    }

    @Test
    public void testAddDoctorWhenIsNew() {
        ReadWrite.getDoctorsFromFile("doctors.txt");

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
        ReadWrite.getDoctorsFromFile("doctors.txt");

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



}
