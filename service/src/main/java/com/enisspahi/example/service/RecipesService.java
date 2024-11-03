package com.enisspahi.example.service;

import com.enisspahi.example.api.model.Recipe;
import com.enisspahi.example.repository.RecipesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipesService {

    private final RecipesRepository recipesRepository;

    public RecipesService(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public List<Recipe> search(Optional<String> title, List<String> ingredients, List<String> nutritionFacts) {
        return recipesRepository.findBy(title, ingredients, nutritionFacts);
    }

    public Recipe store(Recipe recipe) {
        return recipesRepository.save(recipe);
    }

}
