package example;

import com.enisspahi.example.api.RecipesApi;
import com.enisspahi.example.api.invoker.ApiException;
import com.enisspahi.example.api.model.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    private final RecipesApi recipesApi = new RecipesApi();

    @GetMapping("/")
    public String defaultPage() {
        return "search";
    }

    @PostMapping("/searchRecipe")
    public String handleSearch(@RequestParam Optional<String> title,
                               @RequestParam Optional<List<String>> ingredients,
                               @RequestParam Optional<List<String>> nutritionFacts,
                               Model model) {
        var recipes = getRecipes(title, ingredients, nutritionFacts);
        model.addAttribute("recipes", recipes);
        return "result";
    }

    private List<Recipe> getRecipes(Optional<String> title, Optional<List<String>> ingredients, Optional<List<String>> nutritionFacts) {
        try {
            return recipesApi.findRecipes(
                    title.orElse(null),
                    ingredients.orElse(null),
                    nutritionFacts.orElse(null)
            );
        } catch (ApiException exception) {
            throw new RuntimeException("Something went wrong");
        }
    }
}
