public abstract class TestYavor {

    public static void doTests() {

        DBase.patients.get(0).showAppointments();
        DBase.patients.get(0).changeAppointmentsDateTime(1, "15-06-2021", 1100);
        DBase.patients.get(0).showAppointments();
        DBase.patients.get(0).removeAppointment();
        DBase.patients.get(0).showAppointments();
        DBase.patients.get(0).AddAppointment(999, "xxxx", "dddd", 0000);

        DBase.patients.get(0).showAppointments();

    }
}
