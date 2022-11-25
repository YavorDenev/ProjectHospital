public enum SortCriteria {
    DATE_TIME ("date and time"),
    PATIENT_NAMES ("patient name"),
    PATIENT_ID ("patient ID");

    public  final  String text;

    SortCriteria(String text) {
        this.text = text;
    }
}

