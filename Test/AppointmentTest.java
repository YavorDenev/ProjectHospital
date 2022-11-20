import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentTest {

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
    public void testGetPatientNames() {
        ReadWrite.getAppointmentsFromFile("real_appointments.txt");
        ReadWrite.getPatientsFromFile("real_patients.txt");
        Appointment app = DBase.appointments.get(0);
        String expected = "Ivan, Alexiev";
        String actual = app.getPatientNames();
        assertEquals(expected, actual);
    }


}
