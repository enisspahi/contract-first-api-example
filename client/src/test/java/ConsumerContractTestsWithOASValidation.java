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
    public RequestResponsePact pact(PactDslWithProvider builder) {
        var pact = builder
                .given("Recipes API is implemented")
                .uponReceiving("GET /recipes interaction")
                .path("/recipes")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(recipesResponseStructure())
                .toPact();
        validate(pact);
        return pact;
    }

    @Test
    void test(MockServer mockServer) throws IOException {
        var httpResponse = Request.get(mockServer.getUrl() + "/recipes").execute().returnResponse();
        assertEquals(200, httpResponse.getCode());
    }

    public DslPart recipesResponseStructure() {
        return newJsonArray((array) -> {
            array.object((recipe) -> {
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
