package stocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

public class DayReader {
    private BufferedReader br;
    private final String[] categories = {"date", "open", "high", "low", "close", "volume", "Name"};

    public DayReader(BufferedReader br) throws IOException {
        this.br = br;
        br.readLine();
    }

    public TreeSet<String> getAllCompanyNames() {
        TreeSet<String> names = new TreeSet<>();

        boolean done = false;
        do {
            try {
                Day row = readDay();
                if (row == null) {
                    done = true;
                }
                else if (!names.contains(row.getName())) {
                    names.add(row.getName());
                }
            } catch (InvalidFormatException | IOException ex) {
            }
        } while (!done);

        return names;
    }

    public Day readDay() throws IOException, InvalidFormatException {
        String day = br.readLine();
        if (day == null) {
            return null;
        }

        String[] values = getValues(day);

        Date date = setDate(values);
        TreeMap<String, Double> data = setData(values);
        String name = setName(values);

        return new Day(date, data, name);
    }

    private String[] getValues(String line) throws InvalidFormatException {
        String[] values = new String[7];

        int startIndex = 0;
        int endIndex = line.indexOf(",", startIndex);

        for (int i = 0; i < 7; i++) {
            // TODO: WRITE A METHOD FOR THIS
            if (startIndex == endIndex || startIndex == -1) {
                throw new InvalidFormatException("Line entered incorrectly: " + line);
            }

            String value = line.substring(startIndex, endIndex);
            values[i] = value;

            startIndex = endIndex + 1;

            endIndex = line.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = line.length();
            }
        }

        return values;
    }

    private Date setDate(String[] values) {
        String date = values[0];

        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);

        return new Date(year, month, day);
    }

    private TreeMap<String, Double> setData(String[] values) {
        TreeMap<String, Double> data = new TreeMap<>();

        for (int categoryIndex = 1; categoryIndex < 6; categoryIndex++) {
            String value = values[categoryIndex];
            data.put(categories[categoryIndex], Double.parseDouble(value));
        }

        return data;
    }

    private String setName(String[] values) {
        return values[6];
    }

}
