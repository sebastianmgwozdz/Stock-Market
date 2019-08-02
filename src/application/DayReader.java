package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

// Used to parse individual lines in the data file
public class DayReader {
    private BufferedReader br;
    private final String[] categories = {"date", "open", "high", "low", "close", "volume", "Name"};

    public DayReader(BufferedReader br) throws IOException {
        this.br = br;
        br.readLine();
    }

    // Get a list of all unique company names in the file
    public TreeSet<String> getAllCompanyNames() {
        // TreeSet contains no repeats
        TreeSet<String> names = new TreeSet<String>();

        boolean done = false;
        do {
            try {
                Day row = readDay();
                if (row == null) {
                    done = true;
                }
                else {
                    names.add(row.getName());
                }
            } catch (InvalidFormatException | IOException ex) {
            }
        } while (!done);

        return names;
    }

    // Reads in a line and converts it into a Day object
    public Day readDay() throws IOException, InvalidFormatException {
        String day = br.readLine();
        if (day == null) {
            return null;
        }

        String[] values = getValues(day);

        Date date = getDate(values);
        TreeMap<String, Double> data = getData(values);
        String name = getName(values);

        return new Day(date, data, name);
    }

    // Fills an array with all values separated by commas in the row
    private String[] getValues(String line) throws InvalidFormatException {
        String[] values = new String[7];

        // Indices of commas surrounding the value
        int startIndex = 0;
        int endIndex = line.indexOf(",", startIndex);

        for (int i = 0; i < 7; i++) {
            if (startIndex == endIndex || startIndex == -1) {
                throw new InvalidFormatException("Line entered incorrectly: " + line);
            }

            String value = line.substring(startIndex, endIndex);
            values[i] = value;

            startIndex = endIndex + 1;

            endIndex = line.indexOf(",", startIndex);
            // Check if at the last value
            if (endIndex == -1) {
                endIndex = line.length();
            }
        }

        return values;
    }

    private Date getDate(String[] values) {
        String date = values[0];
        return new Date(date);
    }

    private TreeMap<String, Double> getData(String[] values) {
        TreeMap<String, Double> data = new TreeMap<>();

        // Only inserts numeric values/categories
        for (int categoryIndex = 1; categoryIndex < 6; categoryIndex++) {
            String value = values[categoryIndex];
            data.put(categories[categoryIndex], Double.parseDouble(value));
        }

        return data;
    }

    private String getName(String[] values) {
        return values[6];
    }

}
