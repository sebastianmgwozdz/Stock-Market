import java.io.*;
import java.util.LinkedHashSet;

public class Company {
    private String ticker;
    private LinkedHashSet<Day> days;
    private File file;

    public Company(String ticker, File file) {
        this.ticker = ticker;
        this.file = file;
        this.days = setDays();
    }

    private LinkedHashSet<Day> setDays() {
        LinkedHashSet<Day> days = new LinkedHashSet<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            DayReader dr = new DayReader(br);

            Day firstLine = dr.readDay();

            Day row = dr.readDay();
            while (row != null && row.getName().charAt(0) <= ticker.charAt(0)) {
                System.out.println(row.getName());
                if (row.getName().equals(ticker)) {
                    days.add(row);
                }

                row = dr.readDay();
            }
            fr.close();
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return days;
    }

    public String getTicker() {
        return ticker;
    }

    public LinkedHashSet<Day> getDays() {
        return days;
    }
}
