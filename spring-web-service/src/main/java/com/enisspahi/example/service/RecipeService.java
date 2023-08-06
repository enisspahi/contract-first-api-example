package com.enisspahi.example.service;

import com.enisspahi.example.api.model.Recipe;
import com.enisspahi.example.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> search(Optional<String> title, List<String> ingredients, List<Recipe.NutritionFactsEnum> nutritionFacts) {
        return recipeRepository.findBy(title, ingredients, nutritionFacts);
    }

    public Recipe store(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

}
