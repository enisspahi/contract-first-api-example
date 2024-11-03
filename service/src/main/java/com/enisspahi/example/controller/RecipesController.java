package com.enisspahi.example.controller;

import com.enisspahi.example.api.controller.RecipesApiDelegate;
import com.enisspahi.example.api.model.Recipe;
import com.enisspahi.example.service.RecipesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecipesController implements RecipesApiDelegate {

    private final RecipesService recipesService;

    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @Override
    public ResponseEntity<Recipe> createRecipe(Recipe recipe) {
        var responseBody = recipesService.store(recipe);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Recipe>> findRecipes(Optional<String> title, Optional<List<String>> ingredients, Optional<List<String>> nutritionFacts) {
        var responseBody = recipesService.search(title, ingredients.orElse(Collections.emptyList()), nutritionFacts.orElse(Collections.emptyList()));
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
