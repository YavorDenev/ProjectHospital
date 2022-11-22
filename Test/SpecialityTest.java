import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialityTest {

    @AfterEach
    public void cleanAppointments(){
        DBase.appointments = new ArrayList<>();
    }

    @Test
    public void testGenerateSpecialityId() {
        DBase.specialities = new ArrayList<>();
        DBase.specialities.add(new Speciality());
        int id = 111;
        DBase.specialities.get(0).id = id;
        Speciality sp = new Speciality("aaaa");
        int expected = id + 1;
        int actual = sp.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateSpecialityIdWhenSpecialitiesIsEmpty() {
        DBase.specialities = new ArrayList<>();
        Speciality sp = new Speciality("xxx");
        int expected = 1;
        int actual = sp.getId();
        assertEquals(expected, actual);
    }

}
