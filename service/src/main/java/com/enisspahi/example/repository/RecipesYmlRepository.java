package com.enisspahi.example.repository;

import com.enisspahi.example.api.model.Ingredient;
import com.enisspahi.example.api.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Slf4j
class RecipesYmlRepository implements RecipesRepository {

    private final List<Recipe> recipes = YamlSourceReader.readFromYaml();

    @Override
    public Recipe save(Recipe recipe) {
        recipes.add(recipe);
        return recipe;
    }

    @Override
    public List<Recipe> findBy(Optional<String> title, List<String> ingredients, List<String> nutritionFacts) {
        return recipes.stream()
                .filter(filterByTitle(title))
                .filter(filterByIngredients(ingredients))
                .filter(filterByNutritionFacts(nutritionFacts))
                .collect(Collectors.toList());
    }


    private static Predicate<Recipe> filterByTitle(Optional<String> title) {
        return recipe -> title.map(t -> recipe.getTitle().toLowerCase().contains(t.toLowerCase())).orElse(true);
    }

    private static Predicate<Recipe> filterByIngredients(List<String> ingredients) {
        return recipe -> ingredients.stream()
                .map(String::toLowerCase)
                .allMatch(searchedIngredient ->
                        recipe.getIngredients().stream()
                                .map(Ingredient::getName)
                                .map(String::toLowerCase)
                                .anyMatch(ingredientOfRecipe -> ingredientOfRecipe.contains(searchedIngredient))
                );
    }

    private static Predicate<Recipe> filterByNutritionFacts(List<String> nutritionFacts) {
        return recipe -> nutritionFacts.stream()
                .allMatch(searchedNutritionFact ->
                        recipe.getNutritionFacts().stream()
                                .map(Recipe.NutritionFactsEnum::getValue)
                                .anyMatch(nutritionFactOfRecipe -> nutritionFactOfRecipe.equalsIgnoreCase(searchedNutritionFact))
                );
    }

}
