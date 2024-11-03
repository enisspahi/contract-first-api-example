package com.enisspahi.example.repository;

import com.enisspahi.example.api.model.Ingredient;
import com.enisspahi.example.api.model.Recipe;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

final class YamlSourceReader {

    static List<Recipe> readFromYaml() {
        var yaml = new Yaml();
        InputStream inputStream = RecipesYmlRepository.class.getResourceAsStream("/recipes/recipes.yml");
        RecipesDTOWrapper recipesDTOList = yaml.loadAs(inputStream, RecipesDTOWrapper.class);
        return recipesDTOList.recipes.stream()
                .map(YamlSourceReader::toAPIModel)
                .collect(Collectors.toList());
    }

    private static Recipe toAPIModel(RecipesDTOWrapper.RecipeDTO dto) {
        return new Recipe()
                .title(dto.title)
                .ingredients(dto.ingredients.stream()
                        .map(YamlSourceReader::toAPIModel)
                        .collect(Collectors.toList()))
                .preparationTime(dto.preparationTime)
                .cookingTime(dto.cookingTime)
                .servings(dto.servings)
                .instructions(dto.instructions)
                .nutritionFacts(dto.nutritionFacts.stream()
                        .map(Recipe.NutritionFactsEnum::valueOf)
                        .collect(Collectors.toList()));
    }

    private static Ingredient toAPIModel(RecipesDTOWrapper.RecipeDTO.IngredientDTO ingredientDTO) {
        return new Ingredient()
                .name(ingredientDTO.name)
                .quantity(ingredientDTO.quantity)
                .unit(ingredientDTO.unit);
    }

    @Data
    public static class RecipesDTOWrapper {
        private List<RecipeDTO> recipes;

        @Data
        public static class RecipeDTO {
            private String title;
            private List<IngredientDTO> ingredients;
            private Integer preparationTime;
            private Integer cookingTime;
            private Integer servings;
            private List<String> instructions;
            private List<String> nutritionFacts;

            @Data
            public static class IngredientDTO {
                private String name;
                private Double quantity;
                private String unit;
            }

        }

    }


}
