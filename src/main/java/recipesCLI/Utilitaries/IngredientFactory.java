package recipesCLI.Utilitaries;

import recipesCLI.DTO.IngredientDTO;

public class IngredientFactory {
    /**
     * @param name The ingredient name
     * @param quantityString The quantity as String
     * @param unit The unit as string
     * @return It will return an ingredientDTO object with all the provided information.
     */
    public static IngredientDTO createIngredient(String name, String quantityString, String unit) {
        double quantity = toDouble(quantityString);
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setName(name);
        ingredientDTO.setQuantity(quantity);
        ingredientDTO.setUnit(unit);
        return ingredientDTO;
    }

    /**
     * @param quantity The string value to convert to double
     * @return It will return the string as a double value with default value 0.0
     */
    private static double toDouble (String quantity) {
        try {
            return Double.parseDouble(quantity);
        } catch (Exception ex) {
            return 0.0;
        }
    }
}
