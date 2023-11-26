---
title: "Use Cases"
date: 2023-04-26T16:53:17+02:00
---

### Use Case 1: Get all recipes
{{< mermaid >}}
    sequenceDiagram
        Consumer->>RecipesAPI: GET /recipes
        RecipesAPI-->>Consumer: All recipes
{{< /mermaid >}}

### Use Case 2: Get recipes by title
{{< mermaid >}}
    sequenceDiagram
        Consumer->>RecipesAPI: GET /recipes?title=Pumpkin
        RecipesAPI-->>Consumer: Matching recipes
{{< /mermaid >}}

### Use Case 3: Get recipes by nutrition facts
{{< mermaid >}}
    sequenceDiagram
        Consumer->>RecipesAPI: GET /recipes?nutritionFacts=LOW_CALORIE
        RecipesAPI-->>Consumer: Matching recipes
{{< /mermaid >}}
