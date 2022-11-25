public abstract class FunctionsText {


    public static String fixLengthIn(String str, int length)
    {
        if (str.length()==4) str = " " + str; // just for time > right alingment
        String result = " | "+str;
        while(result.length()<length) {
            result += " ";
        }
        return result;
    }

    public static String fixLengthIn(String str, int length, String color)
    {
        String red = "\033[1;31m";
        String blue = "\033[1;36m";
        String green = "\033[1;32m";
        String reset = "\033[0m";

        String finColor ="";
        switch (color){
            case "red" -> finColor = red;
            case "blue" -> finColor = blue;
            case "green" -> finColor = green;
            default -> finColor = reset;
        }

        String result = " | "+str;
        while(result.length()<length) {
            result += " ";
        }
        return finColor + result + reset;
    }

    public static String fixLengthInWithoutFrame(String str, int length)
    {
        String result = str;
        while(result.length()<length) {
            result += " ";
        }
        return result;
    }

    public static void newColoredLine(char c, String color, int width){
        String red = "\033[1;31m";
        String blue = "\033[1;36m";
        String green = "\033[1;32m";
        String reset = "\033[0m";

        String finColor ="";
        switch (color){
            case "red" -> finColor = red;
            case "blue" -> finColor = blue;
            case "green" -> finColor = green;
            default -> finColor = reset;
        }

        String result = "";

        while(result.length()<width) {
            result += c;
        }
        System.out.println(finColor + result + reset);
    }

}
