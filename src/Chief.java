import java.util.ArrayList;

public class Chief extends User implements Authorizable {

    static ArrayList<Integer> rights = new ArrayList<>();
    public static String keyWordForClassRights = "Boss"; //First word in arrayListUserRights.csv file

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // ------------------------------------- TODO


    public void addDoctor() { }     // ------------- TODO

    public void removeDoctor() { }     // ------------- TODO

    public void addSpecialty() { }     // ------------- TODO

    public void removeSpecialty() { }     // ------------- TODO


    @Override
    public void authorize(String password) {

    }
}
