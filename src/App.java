import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        File f = new File("all_stocks_5yr.csv");
        Company apple = new Company("MSFT", f);
        for (Day d : apple.getDays()) {
            System.out.println(d.getName());
        }
    }
}
