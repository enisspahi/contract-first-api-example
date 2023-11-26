---
title: "Use Cases"
date: 2023-04-26T16:53:17+02:00
---

### Use Case 1: Get all recipes
{{< mermaid >}}
    sequenceDiagram
        Consumer->>RecipesAPI: get /recipes
        RecipesAPI-->>Consumer: List of all recipes
{{< /mermaid >}}

### Use Case 2: Get recipes by title
{{< mermaid >}}
    sequenceDiagram
        Consumer->>RecipesAPI: get /recipes?title=Pumpkin
        RecipesAPI-->>Consumer: Recipes matching queried title
{{< /mermaid >}}

### Use Case 3: Get recipes by nutrition facts
{{< mermaid >}}
    sequenceDiagram
        Consumer->>RecipesAPI: get /recipes?nutritionFacts=LOW_CALORIE&nutritionFacts=HIGH_PROTEIN
        RecipesAPI-->>Consumer: Recipes matching queried nutrition
{{< /mermaid >}}
