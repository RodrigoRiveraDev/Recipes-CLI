package recipesCLI.CustomExceptions;

public class BadResponseException extends Exception {
    public BadResponseException(String text) {
        super(text);
    }
}
