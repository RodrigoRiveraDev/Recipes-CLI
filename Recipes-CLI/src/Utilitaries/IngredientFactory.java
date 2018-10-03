package Utilitaries;

import DTO.IngredientDTO;

public class IngredientFactory {
    public static IngredientDTO createIngredient(String name, String quantityString, String unit) {
        double quantity = toDouble(quantityString);
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setName(name);
        ingredientDTO.setQuantity(quantity);
        ingredientDTO.setUnit(unit);
        return ingredientDTO;
    }

    private static double toDouble (String quantity) {
        try {
            return Double.parseDouble(quantity);
        } catch (Exception ex) {
            return 0.0;
        }
    }
}
