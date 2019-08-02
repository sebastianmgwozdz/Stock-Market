package stocks;

public class CompanyDoesNotExistException extends Exception {
    public CompanyDoesNotExistException(String message) {
        super(message);
    }
}
