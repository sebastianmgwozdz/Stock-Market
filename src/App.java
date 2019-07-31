import java.io.File;

public class App {
    public static void main(String[] args) {
        File f = new File("all_stocks_5yr.csv");
        Company apple = new Company("ZTS", f);
        for (Day d : apple.getDays()) {
            System.out.println(d.getName());
        }
    }
}
