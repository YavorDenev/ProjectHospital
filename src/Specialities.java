public class Specialities {

    public int id;
    public String name;
    public boolean isHidden;

    @Override
    public String toString() {
        return "" + id + " " + name + " " + isHidden;
    }
}