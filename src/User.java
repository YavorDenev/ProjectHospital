public abstract class User {
    protected String firstName;
    protected String lastName;
    protected int age;
    public String sex; //male, female, unknown

    public User() {}

    public User(String firstName, String lastName, int age, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
    }
}
