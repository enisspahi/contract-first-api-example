---
title: Quickstart
weight: 2
---


### API Connection
* Clone API from [github project](https://github.com/enisspahi/contract-first-api-example) 
* Start API
````
./gradlew :service:bootRun
```` 
* Verify interactions 
````
curl --location 'http://localhost:8080/recipes'
curl --location 'http://localhost:8080/recipes?title=Pumpkin'
curl --location 'http://localhost:8080/recipes?nutritionFacts=LOW_CALORIE&nutritionFacts=HIGH_PROTEIN'
````


### Client Code Generation


#### OpenAPI Generator Java example with Gradle 
* Add plugin
````
plugins {
    id "org.openapi.generator" version "7.1.0"
}
````
* Configure plugin. Ensure [recipes-api.yaml](https://raw.githubusercontent.com/enisspahi/contract-first-api-example/main/api/src/main/resources/recipes-api.yaml) added to your project. 
````
openApiGenerate {
    generatorName = "java"
    inputSpec = "$rootDir/src/main/resources/recipes-api.yaml"
    outputDir = "$buildDir/generated"
    apiPackage = "preferred package"
    invokerPackage = "preferred package"
    modelPackage = "preferred package"
    configOptions = [
            library: "native",
            dateLibrary: "java8",
            useJakartaEe: "true",
            openApiNullable: "false",
    ]
}
````
* Run `./gradlew openApiGenerate`
* Consume API on your code
````
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
````