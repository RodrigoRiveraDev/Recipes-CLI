package recipesCLI.CustomExceptions;

public class CustomConnectionException extends Exception {
    public CustomConnectionException() {
        super("There was a problem with the connection");
    }
}
