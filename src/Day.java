import java.util.HashMap;

public class Day {
    private Date date;
    private HashMap<String, Double> data;
    private String name;

    public Day(Date date, HashMap<String, Double> data, String name) {
        this.date = date;
        this.data = data;
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public HashMap<String, Double> getData() {
        return data;
    }

    public String getName() {
        return name;
    }

}
