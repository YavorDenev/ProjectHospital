import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class TestYavor {

    public static void doTests() {

        //DBase.patients.get(0).showMyAppointments();
        //DBase.patients.get(0).changeAppointmentsDateTime(1, "15-06-2021", 1100);
        //DBase.patients.get(0).showMyAppointments();
        //DBase.patients.get(0).removeMyAppointment();
        //DBase.patients.get(0).showMyAppointments();
        //DBase.patients.get(0).AddAppointment(999, "xxxx", "dddd", 0000);
        //DBase.patients.get(0).showMyAppointments();

        //DBase.doctors.get(1).showDocAppointments();
        //DBase.doctors.get(1).removeDocAppointment();
        //DBase.doctors.get(1).showDocAppointments();
        //for (Appointment a : DBase.appointments) {System.out.println(a);}
        //Boss.removeAppointment();
        //for (Appointment a : DBase.appointments) {System.out.println(a);}
        //Doctor.showDocAppointments(2);
        //DBase.doctors.get(1).showMyPatients();
        //Hospital.showPatientsByDocNames("martin", "Katev");
        //Hospital.showPatientsByDocNames("Yavor", "Denev");
        //Hospital.showPatientsBySpeciality("neurology");
        //Hospital.showPatientsByDate("21-06-2021");
        //Doctor.showDocApptsByPatientId(2, "up");
        //DBase.doctors.get(1).showDocApptsByPatientId("up");
        //Doctor.showDocApptsByPatientNames(2, "up");

        //Doctor.showDocApptsByDateTime(2, "down");
        //DBase.appointments.stream().filter(appointment -> appointment.doctorID==2).sorted(Comparator.comparing(Appointment::getDateTimeComparingKey).reversed()).peek(System.out::println).toList();

    }
}
