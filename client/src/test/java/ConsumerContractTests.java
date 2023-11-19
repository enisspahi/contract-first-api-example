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

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

@PactConsumerTest
@PactTestFor(providerName = ConsumerContractTests.RECIPES_PROVIDER, pactVersion = PactSpecVersion.V3)
public class ConsumerContractTests {

    static final String RECIPES_PROVIDER = "RecipesAPI";
    static final String RECIPES_CONSUMER = "RecipesClient";

    @Pact(provider= RECIPES_PROVIDER, consumer= RECIPES_CONSUMER)
    public RequestResponsePact pact(PactDslWithProvider builder) {
        return builder
                .given("test state")
                .uponReceiving("ExampleJavaConsumerPactTest test interaction")
                .path("/recipes")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(recipesResponseStructure())
                .toPact();
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


}
