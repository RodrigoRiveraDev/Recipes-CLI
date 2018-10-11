package recipesCLI.Utilitaries;

public class FieldValidator {
    /**
     * @param number The entered option as string
     * @return It will return the entered option as an integer with default value 0
     */
    public static int mainOption(String number) {
        int option;
        try {
            option = Integer.parseInt(number);
        } catch (Exception ex){
            option = 0;
        }
        if(option <= 0 || option > 9) {
            System.out.println("Invalid value, try again");
        }
        return option;
    }
}
