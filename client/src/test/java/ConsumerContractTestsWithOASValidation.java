import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTest;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.pact.PactRequest;
import com.atlassian.oai.validator.pact.PactResponse;
import com.atlassian.oai.validator.pact.ValidatedPactProviderRule;
import org.apache.hc.client5.http.fluent.Request;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

@PactConsumerTest
@PactTestFor(providerName = ConsumerContractTestsWithOASValidation.RECIPES_PROVIDER, pactVersion = PactSpecVersion.V3)
public class ConsumerContractTestsWithOASValidation {

    static final String RECIPES_PROVIDER = "RecipesAPI";
    static final String RECIPES_CONSUMER = "RecipesClient";

    private final OpenApiInteractionValidator openApiInteractionValidator = OpenApiInteractionValidator
            .createForSpecificationUrl("https://raw.githubusercontent.com/enisspahi/contract-first-api-example/main/api/src/main/resources/recipes-api.yaml")
            .build();

    @Pact(provider = RECIPES_PROVIDER, consumer = RECIPES_CONSUMER)
    public RequestResponsePact getAllRecipesPact(PactDslWithProvider builder) {
        var getAllRecipesPact = builder
                .given("Recipes API has recipes")
                .uponReceiving("GET all recipes")
                .path("/recipes")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(recipesResponseStructure(Optional.empty()))
                .toPact();
        validate(getAllRecipesPact);
        return getAllRecipesPact;
    }

    @Pact(provider = RECIPES_PROVIDER, consumer = RECIPES_CONSUMER)
    public RequestResponsePact getRecipesByNutritionPact(PactDslWithProvider builder) {
        var getRecipesByNutritionPact = builder
                .given("Recipes API has recipes")
                .uponReceiving("GET LOW_CALORIE and HIGH_PROTEIN recipes")
                .path("/recipes")
                .query("nutritionFacts=LOW_CALORIE&nutritionFacts=HIGH_PROTEIN")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(recipesResponseStructure(Optional.of(Set.of("LOW_CALORIE", "HIGH_PROTEIN"))))
                .toPact();
        validate(getRecipesByNutritionPact);
        return getRecipesByNutritionPact;
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

    private void validate(RequestResponsePact pact) {
        pact.getInteractions().forEach(interaction -> {
            var result = openApiInteractionValidator.validate(PactRequest.of(interaction.asSynchronousRequestResponse().getRequest()), PactResponse.of(interaction.asSynchronousRequestResponse().getResponse()));
            if (result.hasErrors()) {
                throw new ValidatedPactProviderRule.PactValidationError(result);
            }
        });
    }
}
