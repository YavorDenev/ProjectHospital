public class Main {
    public static void main(String[] args) {
        ReadWrite.readDoctors();
        ReadWrite.readPatients();
        ReadWrite.readAppointments();
        System.out.println("==========  WELCOME TO OUR HOSPITAL  ==========");
        Menus.showMainMenu();
    }
}