package com.enisspahi.example;

import com.enisspahi.example.api.controller.RecipesApi;
import com.enisspahi.example.api.model.Recipe;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;


//@Path("")
public class RecipesApiImpl implements RecipesApi {

//    @POST
//    @Consumes({ "application/json" })
//    @Produces({ "application/json" })

    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipe;
    }

//    @GET
//    @Produces({ "application/json" })
    @Override
    public List<Recipe> findRecipes(@QueryParam("title")   String title, @QueryParam("ingredients") List<String> ingredients, @QueryParam("nutritionFacts")   List<String> nutritionFacts) {
        return Collections.emptyList();
    }
}
