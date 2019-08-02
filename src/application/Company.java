package application;

import java.io.*;
import java.util.TreeSet;

public class Company {
    private String ticker;
    private TreeSet<Day> days;
    private File file;

    public Company(String ticker, File file) throws IOException, CompanyDoesNotExistException {
        this.ticker = ticker;
        this.file = file;
        this.days = setDays();
    }

    private TreeSet<Day> setDays() throws IOException, CompanyDoesNotExistException {
        TreeSet<Day> days = new TreeSet<>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        DayReader dr = new DayReader(br);

        scanFile(days, dr);

        fr.close();
        br.close();

        return days;
    }

    private void scanFile(TreeSet<Day> days, DayReader dr) throws CompanyDoesNotExistException {
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

        if (days.size() == 0) {
            throw new CompanyDoesNotExistException("Company doesn't exist");
        }
    }

    public String getTicker() {
        return ticker;
    }

    public TreeSet<Day> getDays() {
        return days;
    }
}
