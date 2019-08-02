package application;

public class Date {
    private String year;
    private String month;
    private String day;

    public Date(String date) {
        this.year = date.substring(0, 4);;
        this.month = date.substring(5, 7);
        this.day = date.substring(8, 10);
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String toString() {
        return year + "-" + month + "-" + day;
    }
}
