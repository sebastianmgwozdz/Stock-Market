package application;

import java.io.*;
import java.util.TreeSet;

// Models a given company from the Stock Market. Tracks the ticker and every recorded day.
public class Company {
    private String ticker;
    private TreeSet<Day> days;

    public Company(String ticker) throws IOException, CompanyDoesNotExistException {
        this.ticker = ticker;
        this.days = setDays();
    }

    public TreeSet<Day> getDays() {
        return days;
    }

    private TreeSet<Day> setDays() throws IOException, CompanyDoesNotExistException {
        TreeSet<Day> days = new TreeSet<>();

        FileReader fr = new FileReader(App.FILE);
        BufferedReader br = new BufferedReader(fr);
        DayReader dr = new DayReader(br);

        scanFile(days, dr);

        fr.close();
        br.close();

        return days;
    }

    // Parses the data file and gets all records for this company
    private void scanFile(TreeSet<Day> days, DayReader dr) throws CompanyDoesNotExistException {
        boolean done = false;
        do {
            try {
                Day row = dr.readDay();
                // Check for end of file
                if (row == null) {
                    done = true;
                }
                // Check if all the company's records have been added
                else if (row.getName().charAt(0) > ticker.charAt(0)) {
                    done = true;
                } else if (row.getName().equals(ticker)) {
                    days.add(row);
                }
            } catch (InvalidFormatException | IOException ex) {
                System.out.println(ex.getMessage());
            }
        } while (!done);

        if (days.size() == 0) {
            throw new CompanyDoesNotExistException("Company doesn't exist");
        }
    }
}
