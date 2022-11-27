public class FunctionsText {
    private FunctionsText(){}

    public static String rightAlignmentColoredText(String txt, int len, String color) {
        String result = txt;
        while (result.length()<len){
            result = " " + result;
        }
        return color+result+Colors.RESET;
    }

    public static void printRightAlignmentColoredText(String txt, int len, String color) {
        String result = txt;
        while (result.length()<len){
            result = " " + result;
        }
        System.out.print(color+result+Colors.RESET);
    }

    public static String getRightAlignmentColoredText(String txt, int len, String color) {
        String result = txt;
        while (result.length()<len){
            result = " " + result;
        }
         return color+result+Colors.RESET;
    }


    public static String leftFrameFixedOnLength(String str, int length)
    {
        if (str.length()==4) str = " " + str; // just for time > right alingment
        String result = " | "+str;
        while(result.length()<length) {
            result += " ";
        }
        return result;
    }

    public static String leftFrameFixedOnLengthColored(String str, int length, String color)
    {
        String result = " | "+str;
        while(result.length()<length) {
            result += " ";
        }
        return color + result + Colors.RESET;
    }

    public static void newColoredLine(char c, String color, int width){
        String result = "";
        while(result.length()<width) {
            result += c;
        }
        System.out.println(color + result + Colors.RESET);
    }

}
