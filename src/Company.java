import java.io.*;
import java.util.LinkedHashSet;

public class Company {
    private String ticker;
    private LinkedHashSet<Day> days;
    private File file;

    public Company(String ticker, File file) throws IOException {
        this.ticker = ticker;
        this.file = file;
        this.days = setDays();
    }

    private LinkedHashSet<Day> setDays() throws IOException {
        LinkedHashSet<Day> days = new LinkedHashSet<>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        DayReader dr = new DayReader(br);

        scanFile(days, dr);

        fr.close();
        br.close();

        return days;
    }

    private void scanFile(LinkedHashSet<Day> days, DayReader dr) {
        boolean done = false;
        do {
            try {
                Day row = dr.readDay();
                if (row == null) {
                    done = true;
                } else if (row.getName().charAt(0) > ticker.charAt(0)) {
                    done = true;
                } else if (row.getName().equals(ticker)) {
                    days.add(row);
                }
            } catch (InvalidFormatException | IOException ex) {
                System.out.println(ex.getMessage());
            }
        } while (!done);
    }

    public String getTicker() {
        return ticker;
    }

    public LinkedHashSet<Day> getDays() {
        return days;
    }
}
