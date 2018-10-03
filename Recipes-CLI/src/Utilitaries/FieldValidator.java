package Utilitaries;

public class FieldValidator {
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
