package com.enisspahi.example.controller;

import com.enisspahi.example.api.controller.RecipesApiDelegate;
import com.enisspahi.example.api.model.Recipe;
import com.enisspahi.example.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeController implements RecipesApiDelegate {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public ResponseEntity<Recipe> createRecipe(Recipe recipe) {
        var responseBody = recipeService.store(recipe);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Recipe>> findRecipes(Optional<String> title, Optional<List<String>> ingredients, Optional<List<String>> nutritionFacts) {
        var responseBody = recipeService.search(title, ingredients.orElse(Collections.EMPTY_LIST), nutritionFacts.orElse(Collections.EMPTY_LIST));
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
