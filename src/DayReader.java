import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DayReader {
    private BufferedReader br;
    private final ArrayList<String> categories = new ArrayList<>(Arrays.asList("open", "high", "low", "close",
                                                                            "volume"));

    public DayReader(BufferedReader br) {
        this.br = br;
    }

    public Day readDay() throws IOException {
        String day = br.readLine();
        if (day == null || day.charAt(0) == 'd') {
            return null;
        }
        if (getData(day) == null) {
            day = br.readLine();
        }
        Date date = getDate(day);
        HashMap<String, Double> data = getData(day);
        String name = getName(day);

        return ((data == null) ? null : new Day(date, data, name));
    }

    private Date getDate(String line) {
        String year = line.substring(0, 4);
        String month = line.substring(5, 7);
        String day = line.substring(8, 10);
        return new Date(year, month, day);
    }

    private HashMap<String, Double> getData(String line) {
        HashMap<String, Double> data = new HashMap<>();

        int categoryIndex = 0;
        int startIndex = 11;
        int endIndex = line.indexOf(",", startIndex);

        while (endIndex != -1) {
            // TODO: WRITE A METHOD FOR THIS
            if (startIndex == endIndex) {
                return null;
            }
            String value = line.substring(startIndex, endIndex);
            data.put(categories.get(categoryIndex), Double.parseDouble(value));
            startIndex = endIndex + 1;
            endIndex = line.indexOf(",", startIndex);
            categoryIndex++;
        }

        return data;
    }

    private String getName(String line) {
        int startIndex = 0;
        while (line.indexOf(",", startIndex) != -1) {
            startIndex = line.indexOf(",", startIndex) + 1;
        }
        return line.substring(startIndex, line.length());
    }

}
