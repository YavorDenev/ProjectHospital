public enum Colors {
    RED ("\033[1;31m"),
    BLUE ("\033[1;32m"),
    GREEN ("\033[1;32m"),
    RESET ("\033[0m");

    public final String name;

    Colors(String name) {
        this.name = name;
    }
}
