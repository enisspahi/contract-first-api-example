import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTest;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.apache.hc.client5.http.fluent.Request;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

@PactConsumerTest
@PactTestFor(providerName = ConsumerContractTests.RECIPES_PROVIDER, pactVersion = PactSpecVersion.V3)
public class ConsumerContractTests {

    static final String RECIPES_PROVIDER = "RecipesAPI";
    static final String RECIPES_CONSUMER = "RecipesClient";

    @Pact(provider = RECIPES_PROVIDER, consumer = RECIPES_CONSUMER)
    public RequestResponsePact getAllRecipesPact(PactDslWithProvider builder) {
        return builder
                .given("Recipes API has recipes")
                .uponReceiving("GET all recipes")
                    .path("/recipes")
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .body(recipesResponseStructure(Optional.empty()))
                .toPact();
    }

    @Pact(provider = RECIPES_PROVIDER, consumer = RECIPES_CONSUMER)
    public RequestResponsePact getRecipesByNutritionPact(PactDslWithProvider builder) {
        return builder
                .given("Recipes API has recipes")
                    .uponReceiving("GET LOW_CALORIE and HIGH_PROTEIN recipes")
                    .path("/recipes")
                    .query("nutritionFacts=LOW_CALORIE&nutritionFacts=HIGH_PROTEIN")
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .body(recipesResponseStructure(Optional.of(Set.of("LOW_CALORIE", "HIGH_PROTEIN"))))
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getAllRecipesPact")
    void getsAllRecipes(MockServer mockServer) throws IOException {
        var httpResponse = Request.get(mockServer.getUrl() + "/recipes")
                .execute()
                .returnResponse();
        assertEquals(200, httpResponse.getCode());
    }

    @Test
    @PactTestFor(pactMethod = "getRecipesByNutritionPact")
    void getsRecipesWithPreferredNutritionInput(MockServer mockServer) throws IOException {
        var httpResponse = Request.get(mockServer.getUrl() + "/recipes?nutritionFacts=LOW_CALORIE&nutritionFacts=HIGH_PROTEIN")
                .execute()
                .returnResponse();
        assertEquals(200, httpResponse.getCode());
    }

    public DslPart recipesResponseStructure(Optional<Set<String>> expectedNutritionValues) {
        return newJsonArray(array -> {
            array.object(recipe -> {
                recipe.stringType("title", "Chilli sin Carne");
                recipe.array("ingredients", ingredientsArray -> {
                    ingredientsArray.object(ingredient -> {
                        ingredient.stringType("name", "Kidney beans");
                        ingredient.numberType("quantity", 250);
                        ingredient.stringType("unit", "grams");
                    });
                });
                recipe.numberType("preparationTime", 30);
                recipe.numberType("cookingTime", 15);
                recipe.numberType("servings", 4);
                recipe.array("instructions", instructions -> instructions.stringType("string"));

                recipe.array("nutritionFacts", nutritionFacts -> nutritionFacts.stringType("LOW_CALORIE"));

                recipe.array("nutritionFacts", nutritionFacts ->
                        expectedNutritionValues.ifPresentOrElse(values ->
                                        values.forEach(expectedNutritionValue -> nutritionFacts.stringValue(expectedNutritionValue)),
                                () -> nutritionFacts.stringType("LOW_CALORIE"))
                );

            });
        }).build();
    }


}
